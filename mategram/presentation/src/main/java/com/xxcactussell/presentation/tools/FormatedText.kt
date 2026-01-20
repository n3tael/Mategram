package com.xxcactussell.presentation.tools

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.text.InlineTextContent
import androidx.compose.foundation.text.LocalBackgroundTextMeasurementExecutor
import androidx.compose.foundation.text.appendInlineContent
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.Placeholder
import androidx.compose.ui.text.PlaceholderVerticalAlign
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextIndent
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.isSpecified
import androidx.compose.ui.unit.sp
import com.xxcactussell.domain.File
import com.xxcactussell.domain.FormattedText
import com.xxcactussell.domain.Sticker
import com.xxcactussell.domain.StickerFormat
import com.xxcactussell.domain.TextEntity
import com.xxcactussell.domain.TextEntityType
import com.xxcactussell.domain.TextEntityTypeBlockQuote
import com.xxcactussell.domain.TextEntityTypeBold
import com.xxcactussell.domain.TextEntityTypeCode
import com.xxcactussell.domain.TextEntityTypeCustomEmoji
import com.xxcactussell.domain.TextEntityTypeExpandableBlockQuote
import com.xxcactussell.domain.TextEntityTypeItalic
import com.xxcactussell.domain.TextEntityTypeMentionName
import com.xxcactussell.domain.TextEntityTypePre
import com.xxcactussell.domain.TextEntityTypePreCode
import com.xxcactussell.domain.TextEntityTypeSpoiler
import com.xxcactussell.domain.TextEntityTypeStrikethrough
import com.xxcactussell.domain.TextEntityTypeTextUrl
import com.xxcactussell.domain.TextEntityTypeUnderline
import com.xxcactussell.presentation.LocalRootViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.asExecutor
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import kotlin.math.min

data class StaticEmojiData(
    val fileId: Int,
    val stickerFormat: StickerFormat,
    val customEmojiId: Long
)

data class FormattedTextUiState(
    val annotatedString: AnnotatedString,
    val inlineContent: Map<String, StaticEmojiData>,
    val spoilerEntities: List<TextEntity>
)

private val TEXT_MEASUREMENT_EXECUTOR = Dispatchers.Default.asExecutor()

@Composable
fun FormattedTextView(
    modifier: Modifier = Modifier,
    text: FormattedText = FormattedText("", emptyList()),
    style: TextStyle = LocalTextStyle.current,
    maxLines: Int = Int.MAX_VALUE,
    overflow: TextOverflow = TextOverflow.Clip,
    softWrap: Boolean = true,
    clickable: Boolean = true,
    color: Color = Color.Unspecified
) {
    val rootViewModel = LocalRootViewModel.current

    val filesFlow = rootViewModel.files

    val stickers by rootViewModel.stickers.collectAsState()

    var revealedSpoilers by remember(text) { mutableStateOf<Set<Int>>(emptySet()) }
    var textLayoutResult by remember { mutableStateOf<TextLayoutResult?>(null) }

    LaunchedEffect(text) {
        text.entities
            .mapNotNull { (it.type as? TextEntityTypeCustomEmoji)?.customEmojiId }
            .distinct()
            .forEach { rootViewModel.requestStickerInfo(it) }
    }

    val surfaceVariantColor = MaterialTheme.colorScheme.surfaceVariant
    val primaryColor = MaterialTheme.colorScheme.primary

    val uiState by produceState(
        initialValue = FormattedTextUiState(AnnotatedString(text.text), emptyMap(), emptyList()),
        text, stickers, revealedSpoilers, surfaceVariantColor, primaryColor
    ) {
        value = withContext(Dispatchers.Default) {
            mapToUiState(
                text = text,
                stickers = stickers,
                revealedSpoilers = revealedSpoilers,
                surfaceVariantColor = surfaceVariantColor,
                primaryColor = primaryColor
            )
        }
    }

    val fontSize = style.fontSize

    val inlineContent = remember(uiState.inlineContent, fontSize) {
        val size = if (fontSize.isSpecified) fontSize else 16.sp
        val dpSize = size.value.dp + 4.dp
        val placeholder = Placeholder(size, size, PlaceholderVerticalAlign.TextCenter)

        uiState.inlineContent.mapValues { (_, emojiData) ->
            InlineTextContent(placeholder) {
                SmartEmojiBox(
                    fileId = emojiData.fileId,
                    stickerFormat = emojiData.stickerFormat,
                    size = dpSize,
                    style = style,
                    filesFlow = filesFlow,
                    onDownloadRequest = { rootViewModel.downloadFile(it) }
                )
            }
        }
    }

    CompositionLocalProvider(
        LocalBackgroundTextMeasurementExecutor provides TEXT_MEASUREMENT_EXECUTOR
    ) {
        Box {
            Text(
                text = uiState.annotatedString,
                modifier = if (clickable) {
                    modifier.pointerInput(uiState.spoilerEntities) {
                        detectTapGestures { offset ->
                            textLayoutResult?.let { layoutResult ->
                                val charIndex = layoutResult.getOffsetForPosition(offset)
                                uiState.spoilerEntities.find {
                                    charIndex >= it.offset && charIndex < (it.offset + it.length)
                                }?.let { clickedSpoiler ->
                                    revealedSpoilers = revealedSpoilers + clickedSpoiler.offset
                                }
                            }
                        }
                    }
                } else {
                    modifier
                },
                onTextLayout = { textLayoutResult = it },
                inlineContent = inlineContent,
                style = style,
                maxLines = maxLines,
                color = color,
                overflow = overflow,
                softWrap = softWrap,
            )

            if (revealedSpoilers.size < uiState.spoilerEntities.size) {
                val spoilerPaths =
                    remember(textLayoutResult, revealedSpoilers, uiState.spoilerEntities) {
                        val currentTextLength = textLayoutResult?.let { it.getLineEnd(it.lineCount - 1) } ?: 0
                        uiState.spoilerEntities
                            .filter { it.offset !in revealedSpoilers && it.offset < currentTextLength }
                            .mapNotNull { entity ->
                                val endOffset = min(entity.offset + entity.length, currentTextLength)
                                if (textLayoutResult != null && endOffset > entity.offset) {
                                    textLayoutResult!!.getPathForRange(entity.offset, endOffset)
                                } else {
                                    null
                                }
                            }
                    }

                SpoilerEffect(
                    modifier = Modifier.matchParentSize(),
                    textLayoutResult = textLayoutResult,
                    spoilerPaths = spoilerPaths,
                    particleColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
                )
            }
        }
    }
}

@Composable
private fun SmartEmojiBox(
    fileId: Int,
    stickerFormat: StickerFormat,
    size: androidx.compose.ui.unit.Dp,
    style: TextStyle,
    filesFlow: Flow<Map<Int, File>>,
    onDownloadRequest: (Int) -> Unit
) {
    val file by produceState<File?>(initialValue = null, key1 = fileId) {
        filesFlow
            .map { it[fileId] }
            .distinctUntilChanged()
            .collect { value = it }
    }

    val path = file?.local?.path?.takeIf { it.isNotEmpty() && file?.local?.isDownloadingCompleted == true }

    val needsDownload = file == null || (!file!!.local.isDownloadingActive && !file!!.local.isDownloadingCompleted)

    if (path != null) {
        Sticker(path, size)
    } else {
        Text(text = "⏳", style = style)
        if (needsDownload) {
            LaunchedEffect(fileId) {
                onDownloadRequest(fileId)
            }
        }
    }
}

private fun mapToUiState(
    text: FormattedText,
    stickers: Map<Long, Sticker>,
    revealedSpoilers: Set<Int>,
    surfaceVariantColor: Color,
    primaryColor: Color
): FormattedTextUiState {
    val builder = AnnotatedString.Builder()
    val inlineContentMap = mutableMapOf<String, StaticEmojiData>()

    val boundaryPoints = mutableSetOf(0, text.text.length)
    text.entities.forEach {
        boundaryPoints.add(it.offset)
        boundaryPoints.add(it.offset + it.length)
    }
    val sortedPoints = boundaryPoints.toList().sorted()

    for (i in 0 until sortedPoints.size - 1) {
        val start = sortedPoints[i]
        val end = sortedPoints[i + 1]
        if (start >= end) continue

        val coveringEntities =
            text.entities.filter { it.offset <= start && (it.offset + it.length) >= end }

        val spoilerEntity = coveringEntities.firstOrNull { it.type is TextEntityTypeSpoiler }
        val isSpoilerHidden = spoilerEntity != null && spoilerEntity.offset !in revealedSpoilers

        val combinedSpanStyle = coveringEntities
            .mapNotNull { getSpanStyleForEntity(it.type, surfaceVariantColor, primaryColor) }
            .fold(SpanStyle()) { acc, style -> acc.merge(style) }
            .let { if (isSpoilerHidden) it.copy(color = Color.Transparent) else it }

        val customEmojiEntity =
            coveringEntities.firstOrNull { it.type is TextEntityTypeCustomEmoji }

        val paragraphStyles = coveringEntities.mapNotNull { getParagraphStyleForEntity(it.type) }

        val combinedParagraphStyle = if (paragraphStyles.isNotEmpty()) {
            paragraphStyles.reduce { acc, style -> acc.merge(style) }
        } else {
            null
        }

        val segmentBlock: AnnotatedString.Builder.() -> Unit = {
            withStyle(combinedSpanStyle) {
                if (customEmojiEntity != null && !isSpoilerHidden) {
                    val type = customEmojiEntity.type as TextEntityTypeCustomEmoji
                    val emojiIdKey = "emoji_${type.customEmojiId}_${customEmojiEntity.offset}"
                    val alternateText = text.text.substring(start, end)

                    appendInlineContent(emojiIdKey, alternateText)

                    val sticker = stickers[type.customEmojiId]
                    if (sticker != null) {
                        inlineContentMap[emojiIdKey] = StaticEmojiData(
                            fileId = sticker.sticker.id,
                            stickerFormat = sticker.format,
                            customEmojiId = type.customEmojiId
                        )
                    }
                } else {
                    append(text.text.substring(start, end))
                }
            }
        }
        if (combinedParagraphStyle != null) {
            builder.withStyle(combinedParagraphStyle) { segmentBlock() }
        } else {
            builder.segmentBlock()
        }
    }
    val spoilerEntities = text.entities.filter { it.type is TextEntityTypeSpoiler }

    return FormattedTextUiState(
        annotatedString = builder.toAnnotatedString(),
        inlineContent = inlineContentMap,
        spoilerEntities = spoilerEntities
    )
}

private fun getSpanStyleForEntity(
    type: TextEntityType,
    surfaceVariantColor: Color,
    primaryColor: Color
): SpanStyle? {
    return when (type) {
        is TextEntityTypeBold -> SpanStyle(fontWeight = FontWeight.Bold)
        is TextEntityTypeItalic -> SpanStyle(fontStyle = FontStyle.Italic)
        is TextEntityTypeUnderline -> SpanStyle(textDecoration = TextDecoration.Underline)
        is TextEntityTypeStrikethrough -> SpanStyle(textDecoration = TextDecoration.LineThrough)
        is TextEntityTypeSpoiler -> SpanStyle(background = Color.Transparent)
        is TextEntityTypeCode -> SpanStyle(fontFamily = FontFamily.Monospace)
        is TextEntityTypePreCode, is TextEntityTypePre -> SpanStyle(
            fontFamily = FontFamily.Monospace,
            background = surfaceVariantColor
        )
        is TextEntityTypeTextUrl -> SpanStyle(
            color = primaryColor,
            textDecoration = TextDecoration.Underline
        )
        is TextEntityTypeMentionName -> SpanStyle(
            color = primaryColor,
            fontWeight = FontWeight.Medium
        )
        else -> null
    }
}

private fun getParagraphStyleForEntity(type: TextEntityType): ParagraphStyle? {
    return when (type) {
        is TextEntityTypeBlockQuote, is TextEntityTypeExpandableBlockQuote -> ParagraphStyle(
            textIndent = TextIndent(firstLine = 8.sp, restLine = 8.sp)
        )
        else -> null
    }
}