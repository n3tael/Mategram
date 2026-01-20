package com.xxcactussell.domain

data class CreateSupergroupChat(
    val supergroupId: Long,
    val force: Boolean
) : Function
