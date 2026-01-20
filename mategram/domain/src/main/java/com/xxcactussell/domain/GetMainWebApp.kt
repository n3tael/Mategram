package com.xxcactussell.domain

data class GetMainWebApp(
    val chatId: Long,
    val botUserId: Long,
    val startParameter: String,
    val parameters: WebAppOpenParameters
) : Function
