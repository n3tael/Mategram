package com.xxcactussell.domain

data class SendWebAppData(
    val botUserId: Long,
    val buttonText: String,
    val data: String
) : Function
