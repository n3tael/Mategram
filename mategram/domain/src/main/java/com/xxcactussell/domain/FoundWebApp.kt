package com.xxcactussell.domain

data class FoundWebApp(
    val webApp: WebApp,
    val requestWriteAccess: Boolean,
    val skipConfirmation: Boolean
) : Object
