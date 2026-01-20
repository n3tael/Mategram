package com.xxcactussell.domain

data class InternalLinkTypePhoneNumberConfirmation(
    val hash: String,
    val phoneNumber: String
) : InternalLinkType
