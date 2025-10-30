package com.xxcactussell.presentation.chats.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Badge
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.xxcactussell.presentation.chats.model.ChatItemUiState
import com.xxcactussell.presentation.localization.localizedString
import com.xxcactussell.presentation.messages.screen.MessagePreview
import com.xxcactussell.presentation.tools.formatTimestampToDate


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatItem(
    uiState: ChatItemUiState,
    onChatClicked: (Long) -> Unit
) {
    val containerColor = when {
        uiState.isSelected -> MaterialTheme.colorScheme.inversePrimary
        uiState.isUnread -> MaterialTheme.colorScheme.secondaryContainer
        else -> Color.Transparent
    }

    ListItem(
        modifier = Modifier
            .padding(horizontal = 8.dp, vertical = 4.dp)
            .clip(RoundedCornerShape(24.dp))
            .clickable { onChatClicked(uiState.chat.id) },
        headlineContent = {
            Text(
                text = uiState.chat.title,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1
            )
        },
        supportingContent = {
            if (uiState.chat.lastMessage != null) {
                MessagePreview(uiState.chat.lastMessage!!)
            } else {
                Text(localizedString("EventLogOriginalCaptionEmpty"))
            }
        },
        leadingContent = {
            ChatAvatar(
                modifier = Modifier.size(48.dp),
                state = uiState.photo,
                isPinned = uiState.isPinned,
                isOnline = uiState.isOnline
            )
        },
        trailingContent = {
            uiState.chat.lastMessage?.let { lastMessage ->
                Column(
                    modifier = Modifier.fillMaxHeight(),
                    horizontalAlignment = Alignment.End,
                    verticalArrangement = Arrangement.Top
                ) {
                    Text(
                        text = formatTimestampToDate(
                            lastMessage.date
                        ),
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        overflow = TextOverflow.StartEllipsis,
                        maxLines = 1
                    )
                    if (uiState.isUnread) {
                        Spacer(modifier = Modifier.height(4.dp))
                        Badge(
                            containerColor = MaterialTheme.colorScheme.error
                        ) {
                            Text(
                                text = uiState.chat.unreadCount.toString(),
                                color = MaterialTheme.colorScheme.onError,
                                style = MaterialTheme.typography.labelSmall
                            )
                        }
                    }
                }
            }
        },
        colors = ListItemDefaults.colors(
            containerColor = containerColor
        )
    )
}