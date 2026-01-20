package com.xxcactussell.domain

data class SendCustomRequest(
    val method: String,
    val parameters: String
) : Function
