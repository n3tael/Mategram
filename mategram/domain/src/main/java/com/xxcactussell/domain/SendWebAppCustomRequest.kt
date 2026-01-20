package com.xxcactussell.domain

data class SendWebAppCustomRequest(
    val botUserId: Long,
    val method: String,
    val parameters: String
) : Function
