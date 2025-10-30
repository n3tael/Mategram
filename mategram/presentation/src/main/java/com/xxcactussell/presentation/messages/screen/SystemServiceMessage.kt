package com.xxcactussell.presentation.messages.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.xxcactussell.domain.messages.model.MessageContent
import com.xxcactussell.presentation.messages.model.MessageUiItem

@Composable
fun SystemServiceMessage(message: MessageUiItem.MessageItem) {
    val content = message.message.content
    Box(
        modifier = Modifier.Companion
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        contentAlignment = Alignment.Companion.Center
    ) {
        Surface(
            color = MaterialTheme.colorScheme.surfaceContainerHigh,
            shape = RoundedCornerShape(16.dp)
        ) {
            Text(
                text = getSystemMessageText(content = content),
                style = MaterialTheme.typography.labelSmall,
                modifier = Modifier.Companion.padding(horizontal = 8.dp, vertical = 4.dp)
            )
        }
    }
}

private fun getSystemMessageText(content: MessageContent): String {
    return when (content) {
        is MessageContent.Expired -> "TODO" // TODO
        is MessageContent.CallEnded -> "TODO" // TODO
        is MessageContent.CallScheduled -> "TODO" // TODO
        is MessageContent.CallStarted -> "TODO" // TODO
        is MessageContent.ChatTitleChanged -> "TODO" // TODO
        is MessageContent.MemberJoined -> "TODO" // TODO
        is MessageContent.MemberLeft -> "TODO" // TODO
        is MessageContent.MessagePinned -> "TODO" // TODO
        is MessageContent.GiftedPremium -> "TODO" // TODO
        is MessageContent.Giveaway -> "TODO" // TODO
        is MessageContent.PaymentSuccessful -> "TODO" // TODO
        is MessageContent.ScreenshotTaken -> "TODO" // TODO
        is MessageContent.Unsupported -> "TODO" // TODO
        else -> "System event: ${content.javaClass.simpleName}"
    }
}