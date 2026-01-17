package com.xxcactussell.presentation.messages.screen

import android.Manifest
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Badge
import androidx.compose.material3.CircularWavyProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.FilledIconToggleButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.xxcactussell.domain.messages.model.MessageContent
import com.xxcactussell.domain.messages.model.MessageDocument
import com.xxcactussell.mategram.presentation.R
import com.xxcactussell.presentation.LocalRootViewModel
import com.xxcactussell.presentation.messages.model.MessageUiItem
import com.xxcactussell.presentation.messages.model.MessagesEvent
import com.xxcactussell.presentation.tools.ColumnWidthOf
import com.xxcactussell.utils.formatFileSize
import com.xxcactussell.utils.getFileExtension

@Composable
fun DocumentMessage(
    message: MessageUiItem.MessageItem,
    textColor: Color,
    isSending: Boolean,
    isFailed: Boolean,
    onEvent: (Any) -> Unit
) {
    val content = message.message.content as MessageDocument
    ColumnWidthOf(
        modifier =
            if(content.caption.text.isEmpty())
                Modifier
            else
                Modifier.padding(bottom = 8.dp),
        rulerId = "doc",
        horizontalSpacers = 8.dp
    )
    {
        Box(Modifier.layoutId("doc")) {
            DocumentMessageContent(content, onEvent)
        }
        if (content.caption.text.isNotEmpty()) {
            Caption(content.caption, textColor)
        }
    }
}

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun DocumentMessageContent(content: MessageDocument, onEvent: (Any) -> Unit) {
    val document = content.document.document

    val rootViewModel = LocalRootViewModel.current
    val fileUpdates = rootViewModel.files.collectAsState()

    val file = fileUpdates.value[document.id] ?: document

    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = { isGranted ->
            if (isGranted) {
                onEvent(MessagesEvent.DownloadFile(content.document.document.id, content.document.fileName))
            } else {
                onEvent(MessagesEvent.DownloadFile(content.document.document.id, content.document.fileName))
            }
        }
    )
    val context = LocalContext.current

    Row(
        modifier = Modifier.padding(16.dp).width(288.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        FilledIconToggleButton(
            checked = file.local.isDownloadingActive,
            onCheckedChange = {
                if (!file.local.isDownloadingActive && !file.local.isDownloadingCompleted) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                        permissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
                    } else {
                        onEvent(
                            MessagesEvent.DownloadFile(
                                content.document.document.id,
                                content.document.fileName
                            )
                        )
                    }
                } else if (file.local.isDownloadingCompleted) {
                    onEvent(MessagesEvent.OpenFile(context, content.document.fileName))
                } else {
                    onEvent(MessagesEvent.CancelDownloadFile(content.document.document.id, content.document.fileName))
                }
            }
        ) {
            if (file.local.isDownloadingActive) {
                Box(contentAlignment = Alignment.Center) {
                    CircularWavyProgressIndicator(
                        progress = {
                            file.local.downloadedSize.toFloat() / file.expectedSize.toFloat()
                        }
                    )
                    Icon(painterResource(R.drawable.close_24px), "")
                }
            }else if (file.local.isDownloadingCompleted) {
                Icon(painterResource(R.drawable.file_present_24px), "")
            } else {
                Icon(painterResource(R.drawable.download_24px), "")
            }
        }
        Column(
            Modifier.weight(1f)
        ) {
            Text(content.document.fileName, style = MaterialTheme.typography.titleSmallEmphasized, maxLines = 1, overflow = TextOverflow.Ellipsis)
            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                Badge(
                    containerColor = MaterialTheme.colorScheme.secondary,
                    contentColor = MaterialTheme.colorScheme.onSecondary
                ) { Text(content.document.fileName.getFileExtension()) }
                Text(content.document.document.size.formatFileSize(), style = MaterialTheme.typography.labelMediumEmphasized)
            }
        }
        Box(Modifier.fillMaxHeight(), contentAlignment = Alignment.TopEnd) {
            IconButton(
                { }
            ) {
                Icon(painterResource(R.drawable.more_vert_24px),"")
            }
        }
    }
}