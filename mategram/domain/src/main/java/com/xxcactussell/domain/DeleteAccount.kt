package com.xxcactussell.domain

data class DeleteAccount(
    val reason: String,
    val password: String
) : Function
