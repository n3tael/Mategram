package com.xxcactussell.domain

data class VerificationStatus(
    val isVerified: Boolean,
    val isScam: Boolean,
    val isFake: Boolean,
    val botVerificationIconCustomEmojiId: Long
) : Object
