package com.xxcactussell.domain

data class InternalLinkTypePublicChat(
    val chatUsername: String,
    val draftText: String,
    val openProfile: Boolean
) : InternalLinkType
