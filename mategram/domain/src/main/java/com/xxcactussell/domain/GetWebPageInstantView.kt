package com.xxcactussell.domain

data class GetWebPageInstantView(
    val url: String,
    val onlyLocal: Boolean
) : Function
