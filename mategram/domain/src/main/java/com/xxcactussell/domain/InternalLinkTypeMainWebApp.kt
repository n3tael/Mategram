package com.xxcactussell.domain

data class InternalLinkTypeMainWebApp(
    val botUsername: String,
    val startParameter: String,
    val mode: WebAppOpenMode
) : InternalLinkType
