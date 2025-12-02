package com.xxcactussell.presentation.messages.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.xxcactussell.domain.messages.model.MessageContent
import com.xxcactussell.presentation.messages.model.MessageUiItem
import com.xxcactussell.presentation.messages.model.getReactions
import com.xxcactussell.presentation.messages.model.isOutgoing

@Composable
fun SystemServiceMessage(message: MessageUiItem.MessageItem) {
    val content = message.message.content
    Box(
        modifier = Modifier.Companion
            .fillMaxWidth(),
        contentAlignment = Alignment.Companion.Center
    ) {
        Column (
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Surface(
                color = MaterialTheme.colorScheme.surfaceContainerHigh,
                shape = RoundedCornerShape(16.dp)
            ) {
                Text(
                    text = content.javaClass.toString(),
                    style = MaterialTheme.typography.labelSmall,
                    modifier = Modifier.Companion.padding(horizontal = 8.dp, vertical = 4.dp),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }

            val reactions = message.getReactions()
            if (reactions.isNotEmpty()) {
                Spacer(Modifier.height(4.dp))
                FlowRow(
                    maxItemsInEachRow = 4,
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                    verticalArrangement = Arrangement.spacedBy(4.dp),
                ) {
                    reactions.forEach {
                        ReactionChip(it) { }
                    }
                }
            }
        }
    }
}