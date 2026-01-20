package com.xxcactussell.domain

data class CreateTemporaryPassword(
    val password: String,
    val validFor: Int
) : Function
