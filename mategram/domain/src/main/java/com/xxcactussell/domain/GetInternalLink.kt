package com.xxcactussell.domain

data class GetInternalLink(
    val type: InternalLinkType,
    val isHttp: Boolean
) : Function
