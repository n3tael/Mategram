package com.xxcactussell.domain

data class InternalLinkTypeWebApp(
    val botUsername: String,
    val webAppShortName: String,
    val startParameter: String,
    val mode: WebAppOpenMode
) : InternalLinkType
