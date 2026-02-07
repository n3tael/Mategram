package com.xxcactussell.presentation.messages.screen

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.xxcactussell.presentation.chats.model.ChatEffect
import com.xxcactussell.presentation.messages.model.InputEvent
import com.xxcactussell.presentation.messages.model.MessagesEvent
import com.xxcactussell.presentation.messages.viewmodel.MessagesViewModel
import com.xxcactussell.presentation.messages.viewmodel.MessagesViewModelFactory

@Composable
fun MessagesScreen(
    chatId: Long,
    startMessageId: Long? = null,
    lastReadInboxMessageId: Long? = null,
    onProfileClicked: (Long) -> Unit,
    onCameraClicked: () -> Unit,
    onBackHandled: () -> Unit,
    onLinkClicked: (Long, Long?) -> Unit,
    onMediaClicked: (Long) -> Unit,
) {
    val viewModel: MessagesViewModel = hiltViewModel(
        creationCallback = { factory: MessagesViewModelFactory ->
            factory.create(chatId, startMessageId, lastReadInboxMessageId)
        },
        key = "messages_screen_$chatId"
    )

    LaunchedEffect(chatId, startMessageId, lastReadInboxMessageId) {
        viewModel.onEvent(MessagesEvent.Initialize(startMessageId, lastReadInboxMessageId))
    }

    val state by viewModel.uiState.collectAsState()
    val playerState by viewModel.playerState.collectAsState()
    val effectsFlow = viewModel.effects

    val mediaPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickMultipleVisualMedia(maxItems = 10),
        onResult = { uris ->
            if (uris.isNotEmpty()) {
                viewModel.onEvent(InputEvent.MediaSelected(uris))
            }
        }
    )

    val documentPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.OpenMultipleDocuments(),
        onResult = { uris ->
            if (uris.isNotEmpty()) {
                viewModel.onEvent(InputEvent.DocumentsSelected(uris))
            }
        }
    )

    val audioPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.OpenMultipleDocuments(),
        onResult = { uris ->
            if (uris.isNotEmpty()) {
                viewModel.onEvent(InputEvent.MediaSelected(uris))
            }
        }
    )

    LaunchedEffect(effectsFlow) {
        effectsFlow.collect { effect ->
            when (effect) {
                ChatEffect.LaunchMediaPicker -> {
                    mediaPickerLauncher.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageAndVideo))
                }
                ChatEffect.LaunchDocumentPicker -> {
                    documentPickerLauncher.launch(arrayOf("*/*"))
                }
                ChatEffect.LaunchMusicPicker -> {
                    audioPickerLauncher.launch(arrayOf("audio/*"))
                }
                ChatEffect.LaunchContactPicker -> {
                    /* TODO */
                }
            }
        }
    }

    MessagesContent(
        state = state,
        playerState = playerState,
        onProfileClicked = onProfileClicked,
        onEvent = viewModel::onEvent,
        onCameraClicked = onCameraClicked,
        onBackHandle = onBackHandled,
        onLinkClicked = onLinkClicked,
        onMediaClicked = onMediaClicked
    )
}