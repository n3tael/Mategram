package com.xxcactussell.domain

data class MessageEffect(
    val id: Long,
    val staticIcon: Sticker? = null,
    val emoji: String,
    val isPremium: Boolean,
    val type: MessageEffectType
) : Object
