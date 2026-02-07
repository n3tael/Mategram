package com.xxcactussell.presentation.chats.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.xxcactussell.presentation.chats.viewmodel.ChatsViewModel

@Composable
fun ChatsListScreen(
    viewModel: ChatsViewModel = hiltViewModel(),
    onChatClick: (Long, Long?, Long?) -> Unit,
    onFabClicked: () -> Unit,
    onAvatarClicked: () -> Unit
) {
    val state by viewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.loadInitialChats()
    }
    
    ChatListContent(
        state = state,
        onChatClick = onChatClick,
        onEvent = viewModel::onEvent,
        onFabClicked = onFabClicked,
        onAvatarClicked = onAvatarClicked
    )
}