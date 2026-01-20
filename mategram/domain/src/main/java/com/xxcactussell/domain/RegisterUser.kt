package com.xxcactussell.domain

data class RegisterUser(
    val firstName: String,
    val lastName: String,
    val disableNotification: Boolean
) : Function
