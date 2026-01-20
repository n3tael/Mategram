package com.xxcactussell.domain

data class KeyboardButtonTypeRequestUsers(
    val id: Int,
    val restrictUserIsBot: Boolean,
    val userIsBot: Boolean,
    val restrictUserIsPremium: Boolean,
    val userIsPremium: Boolean,
    val maxQuantity: Int,
    val requestName: Boolean,
    val requestUsername: Boolean,
    val requestPhoto: Boolean
) : KeyboardButtonType
