package com.xxcactussell.domain

data class CreatePrivateChat(
    val userId: Long,
    val force: Boolean
) : Function
