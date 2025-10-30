package com.xxcactussell.presentation.chats.model

import androidx.compose.ui.graphics.vector.ImageVector
import com.xxcactussell.presentation.messages.model.AttachmentEvent

data class AttachmentEntry(
    val icon: ImageVector,
    val label: String,
    val event: AttachmentEvent
)