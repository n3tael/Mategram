package com.xxcactussell.domain

data class UnconfirmedSession(
    val id: Long,
    val logInDate: Int,
    val deviceModel: String,
    val location: String
) : Object
