package com.xxcactussell.domain

data class WebAppOpenParameters(
    val theme: ThemeParameters,
    val applicationName: String,
    val mode: WebAppOpenMode
) : Object
