package com.xxcactussell.presentation.chats.model

sealed interface ChatEffect {
    data object LaunchMediaPicker : ChatEffect
    data object LaunchDocumentPicker : ChatEffect
    data object LaunchContactPicker : ChatEffect
    data object LaunchMusicPicker: ChatEffect
}