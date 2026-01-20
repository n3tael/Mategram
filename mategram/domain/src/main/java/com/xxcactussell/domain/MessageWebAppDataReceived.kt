package com.xxcactussell.domain

data class MessageWebAppDataReceived(
    val buttonText: String,
    val data: String
) : MessageContent
