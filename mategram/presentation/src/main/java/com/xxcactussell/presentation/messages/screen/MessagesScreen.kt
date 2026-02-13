package com.xxcactussell.presentation.messages.screen

import android.content.Intent
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.ViewModelStoreOwner
import com.xxcactussell.presentation.chats.model.ChatEffect
import com.xxcactussell.presentation.messages.model.InputEvent
import com.xxcactussell.presentation.messages.model.MessagesEvent
import com.xxcactussell.presentation.messages.viewmodel.MessagesViewModel
import com.xxcactussell.presentation.messages.viewmodel.MessagesViewModelFactory
import com.xxcactussell.presentation.tools.FileUtils
import kotlinx.coroutines.launch

@Composable
fun MessagesScreen(
    chatId: Long,
    startMessageId: Long? = null,
    owner: ViewModelStoreOwner,
    lastReadInboxMessageId: Long? = null,
    onProfileClicked: (Long) -> Unit,
    onCameraClicked: () -> Unit,
    onBackHandled: () -> Unit,
    onLinkClicked: (Long, Long?) -> Unit,
    onMediaClicked: (Long) -> Unit,
) {
    val viewModel: MessagesViewModel = hiltViewModel(
        viewModelStoreOwner = owner,
        creationCallback = { factory: MessagesViewModelFactory ->
            factory.create(chatId, startMessageId, lastReadInboxMessageId)
        },
        key = "messages_screen_$chatId"
    )

    LaunchedEffect(chatId, startMessageId, lastReadInboxMessageId) {
        viewModel.onEvent(MessagesEvent.Initialize(startMessageId, lastReadInboxMessageId))
    }

    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    val state by viewModel.uiState.collectAsState()
    val playerState by viewModel.playerState.collectAsState()
    val effectsFlow = viewModel.effects

    fun processSelectedUris(uris: List<Uri>, isDocument: Boolean = false) {
        scope.launch {
            if (uris.isNotEmpty()) {
                when (isDocument) {
                    true -> viewModel.onEvent(InputEvent.DocumentsSelected(uris))
                    false -> viewModel.onEvent(InputEvent.MediaSelected(uris))
                }
            }
        }
    }

    val mediaPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickMultipleVisualMedia(maxItems = 10),
        onResult = { uris ->
            val flag = Intent.FLAG_GRANT_READ_URI_PERMISSION
            uris.forEach {
                context.contentResolver.takePersistableUriPermission(it, flag)
            }
            if (uris.isNotEmpty()) {
                processSelectedUris(uris)
            }
        }
    )

    val documentPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.OpenMultipleDocuments(),
        onResult = { uris ->
            val flag = Intent.FLAG_GRANT_READ_URI_PERMISSION
            uris.forEach {
                context.contentResolver.takePersistableUriPermission(it, flag)
            }
            if (uris.isNotEmpty()) {
                processSelectedUris(uris, isDocument = true)
            }
        }
    )

    val audioPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.OpenMultipleDocuments(),
        onResult = { uris ->
            val flag = Intent.FLAG_GRANT_READ_URI_PERMISSION
            uris.forEach {
                context.contentResolver.takePersistableUriPermission(it, flag)
            }
            if (uris.isNotEmpty()) {
                processSelectedUris(uris)
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