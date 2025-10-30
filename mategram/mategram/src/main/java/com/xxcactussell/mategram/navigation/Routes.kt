package com.xxcactussell.mategram.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
data object RouteChatList : NavKey

@Serializable
data class RouteChatDetail(val chatId: Long) : NavKey

@Serializable
data object RouteAuth : NavKey

@Serializable
data object RouteCreateNewChat : NavKey

@Serializable
data class RouteMediaViewer(val mediaId: Long) : NavKey

@Serializable
data object RouteMenu : NavKey

@Serializable
data object RouteSettings : NavKey

@Serializable
data class RouteSettingsEnty(val page: String) : NavKey

@Serializable
data class RouteProfile(val userId: Long) : NavKey