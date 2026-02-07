package com.xxcactussell.mategram.navigation

import androidx.navigation3.runtime.NavKey
import com.xxcactussell.presentation.messages.model.MessagesEvent
import kotlinx.serialization.Serializable

@Serializable
data object RouteChatList : NavKey

@Serializable
data class RouteChatDetail(val chatId: Long, val messageId: Long? = null, val lastReadInboxMessageId: Long? = null) : NavKey

@Serializable
data object RouteAuth : NavKey

@Serializable
data object RouteCreateNewChat : NavKey

@Serializable
data class RouteMediaViewer(val messageId: Long, val chatId: Long) : NavKey

@Serializable
data object RouteMenu : NavKey

@Serializable
data object RouteSettings : NavKey

@Serializable
data class RouteSettingsEnty(val page: String) : NavKey

@Serializable
data class RouteProfile(val id: Long, val type: String) : NavKey

@Serializable
data class CameraRoute(val mode: Int, val chatId: Long?) : NavKey // 0 - Chat Camera, 1 - Stories Camera, 2 - Avatar Camera