package com.xxcactussell.domain

data class GetExternalLink(
    val link: String,
    val allowWriteAccess: Boolean
) : Function
