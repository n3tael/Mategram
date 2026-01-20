package com.xxcactussell.domain

data class EncryptedCredentials(
    val data: ByteArray,
    val hash: ByteArray,
    val secret: ByteArray
) : Object
