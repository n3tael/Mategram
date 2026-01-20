package com.xxcactussell.domain

data class MessagePassportDataReceived(
    val elements: List<EncryptedPassportElement>,
    val credentials: EncryptedCredentials
) : MessageContent
