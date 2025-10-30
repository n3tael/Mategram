package com.xxcactussell.presentation.auth.screen.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
data object RouteWelcome : NavKey

@Serializable
data object RouteAuthPhone : NavKey

@Serializable
data object RouteAuthCode : NavKey

@Serializable
data object RouteAuthPassword : NavKey

@Serializable
data object RouteLoading : NavKey