package com.xxcactussell.presentation.tools

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.InlineTextContent
import androidx.compose.foundation.text.appendInlineContent
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.xxcactussell.domain.files.model.File
import com.xxcactussell.domain.messages.model.FormattedText
import com.xxcactussell.domain.messages.model.Sticker
import com.xxcactussell.domain.messages.model.StickerFormat
import com.xxcactussell.domain.messages.model.TextEntity
import com.xxcactussell.domain.messages.model.TextEntityType
import com.xxcactussell.presentation.LocalRootViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

sealed interface InlineContentState {
    data class CustomEmoji(
        val fileId: Int,
        val path: String?,
        val stickerFormat: StickerFormat,
        val isDownloading: Boolean
    ) : InlineContentState
}

data class FormattedTextUiState(
    val annotatedString: AnnotatedString,
    val inlineContent: Map<String, InlineContentState>,
    val spoilerEntities: List<TextEntity>
)

private fun getSpanStyleForEntity(
    type: TextEntityType,
    surfaceVariantColor: Color,
    primaryColor: Color
): SpanStyle? {
    return when (type) {
        is TextEntityType.Bold-> SpanStyle(fontWeight = FontWeight.Bold)
        is TextEntityType.Italic -> SpanStyle(fontStyle = FontStyle.Italic)
        is TextEntityType.Underline -> SpanStyle(textDecoration = TextDecoration.Underline)
        is TextEntityType.Strikethrough -> SpanStyle(textDecoration = TextDecoration.LineThrough)
        is TextEntityType.Spoiler -> SpanStyle(background = Color.Transparent)
        is TextEntityType.Code -> SpanStyle(fontFamily = FontFamily.Monospace)
        is TextEntityType.PreCode, is TextEntityType.Pre -> SpanStyle(
            fontFamily = FontFamily.Monospace,
            background = surfaceVariantColor
        )
        is TextEntityType.TextUrl -> SpanStyle(
            color = primaryColor,
            textDecoration = TextDecoration.Underline
        )
        is TextEntityType.MentionName -> SpanStyle(
            color = primaryColor,
            fontWeight = FontWeight.Medium
        )
        else -> null
    }
}

private fun getParagraphStyleForEntity(type: TextEntityType): ParagraphStyle? {
    return when (type) {
        is TextEntityType.BlockQuote, is TextEntityType.ExpandableBlockQuote -> ParagraphStyle(
            textIndent = TextIndent(firstLine = 8.sp, restLine = 8.sp)
        )
        else -> null
    }
}

fun mapToUiState(
    text: FormattedText,
    stickers: Map<Long, Sticker>,
    files: Map<Int, File>,
    revealedSpoilers: Set<Int>,
    baseStyle: TextStyle,
    surfaceVariantColor: Color,
    primaryColor: Color
): FormattedTextUiState {
    val builder = AnnotatedString.Builder()
    val inlineContentMap = mutableMapOf<String, InlineContentState>()

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

        val spoilerEntity = coveringEntities.firstOrNull { it.type is TextEntityType.Spoiler }
        val isSpoilerHidden = spoilerEntity != null && spoilerEntity.offset !in revealedSpoilers

        val combinedSpanStyle = coveringEntities
            .mapNotNull { getSpanStyleForEntity(it.type, surfaceVariantColor, primaryColor) }
            .fold(SpanStyle()) { acc, style -> acc.merge(style) }
            .let { if (isSpoilerHidden) it.copy(color = Color.Transparent) else it }

        val customEmojiEntity =
            coveringEntities.firstOrNull { it.type is TextEntityType.CustomEmoji }


        val paragraphStyles = coveringEntities.mapNotNull {
            getParagraphStyleForEntity(
                it.type
            )
        }

        val combinedParagraphStyle = if (paragraphStyles.isNotEmpty()) {
            paragraphStyles.reduce { acc, style -> acc.merge(style) }
        } else {
            null
        }

        val segmentBlock: AnnotatedString.Builder.() -> Unit = {
            withStyle(combinedSpanStyle) {
                if (customEmojiEntity != null && !isSpoilerHidden) {
                    val type = customEmojiEntity.type as TextEntityType.CustomEmoji
                    val emojiId = "emoji_${type.id}_${customEmojiEntity.offset}"
                    val alternateText = text.text.substring(start, end)

                    appendInlineContent(emojiId, alternateText)
                    val sticker = stickers[type.id]
                    if (sticker != null) {
                        val file = files[sticker.sticker.id] ?: sticker.sticker
                        inlineContentMap[emojiId] = InlineContentState.CustomEmoji(
                            fileId = file.id,
                            path = file.local.path.takeIf { it.isNotEmpty() && file.local.isDownloadingComplete },
                            stickerFormat = sticker.format,
                            isDownloading = file.local.isDownloadingActive
                        )
                    }
                } else {
                    append(text.text.substring(start, end))
                }
            }
        }
        if (combinedParagraphStyle != null) {
            builder.withStyle(combinedParagraphStyle) {
                segmentBlock()
            }
        } else {
            builder.segmentBlock()
        }
    }
    val spoilerEntities = text.entities.filter { it.type is TextEntityType.Spoiler }

    return FormattedTextUiState(
        annotatedString = builder.toAnnotatedString(),
        inlineContent = inlineContentMap,
        spoilerEntities = spoilerEntities
    )
}

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

    var revealedSpoilers by remember(text) { mutableStateOf<Set<Int>>(emptySet()) }
    var textLayoutResult by remember { mutableStateOf<TextLayoutResult?>(null) }

    val files by rootViewModel.files.collectAsState()
    val stickers by rootViewModel.stickers.collectAsState()

    LaunchedEffect(text) {
        text.entities
            .mapNotNull { (it.type as? TextEntityType.CustomEmoji)?.id }
            .distinct()
            .forEach(rootViewModel::requestStickerInfo)
    }

    val surfaceVariantColor = MaterialTheme.colorScheme.surfaceVariant
    val primaryColor = MaterialTheme.colorScheme.primary

    val uiState by produceState(
        initialValue = FormattedTextUiState(AnnotatedString(""), emptyMap(), emptyList()),
        text, files, stickers, revealedSpoilers
    ) {
        value = withContext(Dispatchers.Default) {
            mapToUiState(
                text = text,
                files = files,
                stickers = stickers,
                revealedSpoilers = revealedSpoilers,
                baseStyle = style,
                surfaceVariantColor = surfaceVariantColor,
                primaryColor = primaryColor
            )
        }
    }

    val spoilerEntities = remember(text) {
        text.entities.filter { it.type is TextEntityType.Spoiler }
    }

    val inlineContent = remember(uiState.inlineContent, style) {
        val size = if (style.fontSize.isSpecified) style.fontSize else 16.sp
        val dpSize = size.value.dp + 4.dp
        val placeholder = Placeholder(size, size, PlaceholderVerticalAlign.TextCenter)

        uiState.inlineContent.mapValues { (_, state) ->
            InlineTextContent(placeholder) {
                when (state) {
                    is InlineContentState.CustomEmoji -> {
                        if (state.path != null) {
                            when (state.stickerFormat) {
                                StickerFormat.Tgs -> LottieSticker(path = state.path, size = dpSize)
                                StickerFormat.Webm -> StickerWEBMPlayer(videoPath = state.path, modifier = Modifier.size(dpSize))
                                StickerFormat.Webp -> WebPImage(path = state.path, size = dpSize)
                                StickerFormat.Unknown -> { }
                            }
                        } else {
                            Text(text = "⏳", style = style)
                            if (!state.isDownloading) {
                                LaunchedEffect(state.fileId) {
                                    rootViewModel.downloadFile(state.fileId)
                                }
                            }
                        }
                    }
                }
            }
        }
    }

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
            val spoilerPaths = remember(textLayoutResult, revealedSpoilers, uiState.spoilerEntities) {
                val currentTextLength = textLayoutResult?.layoutInput?.text?.length ?: 0
                uiState.spoilerEntities
                    .filter { it.offset !in revealedSpoilers }
                    .mapNotNull { entity ->
                        val endOffset = entity.offset + entity.length
                        if (textLayoutResult != null && endOffset <= currentTextLength) {
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