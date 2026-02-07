package com.xxcactussell.presentation.chats.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.dp
import com.xxcactussell.presentation.chats.model.ChatItemUiState
import com.xxcactussell.presentation.chats.model.ChatListUiState

@Composable
fun FolderScreen(
    folderId: Int,
    state: ChatListUiState,
    paddingValues: PaddingValues = PaddingValues(0.dp),
    onChatClicked: (Long, Long?, Long?) -> Unit
) {
    val haptic = LocalHapticFeedback.current
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = paddingValues.calculateTopPadding())
            .clip(RoundedCornerShape(24.dp, 24.dp, 0.dp, 0.dp))
            .background(MaterialTheme.colorScheme.surfaceContainer)
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(paddingValues.calculateStartPadding(LocalLayoutDirection.current), 4.dp, paddingValues.calculateEndPadding(LocalLayoutDirection.current), paddingValues.calculateBottomPadding() + 4.dp)
        ) {
            itemsIndexed(
                items = state.sortedChats[folderId] ?: emptyList(),
                contentType = { _, _ -> "chat_item" },
                key = { _, item -> item.chat.id }
            ) { index, item : ChatItemUiState ->
                ChatItem(
                    index = index,
                    onChatClicked = {
                        haptic.performHapticFeedback(HapticFeedbackType.Confirm)
                        val idToJump = when {
                            item.chat.lastMessage?.isOutgoing == true -> null
                            else -> item.chat.lastReadInboxMessageId
                        }
                        onChatClicked(item.chat.id, null, idToJump)
                                    },
                    uiState = item
                )
            }
        }
    }
}