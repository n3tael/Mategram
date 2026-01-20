package com.xxcactussell.domain

data class InternalLinkTypeUserPhoneNumber(
    val phoneNumber: String,
    val draftText: String,
    val openProfile: Boolean
) : InternalLinkType
