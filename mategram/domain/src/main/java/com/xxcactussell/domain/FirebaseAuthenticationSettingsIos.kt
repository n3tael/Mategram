package com.xxcactussell.domain

data class FirebaseAuthenticationSettingsIos(
    val deviceToken: String,
    val isAppSandbox: Boolean
) : FirebaseAuthenticationSettings
