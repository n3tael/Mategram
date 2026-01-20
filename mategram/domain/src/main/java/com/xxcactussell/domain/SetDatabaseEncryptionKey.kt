package com.xxcactussell.domain

data class SetDatabaseEncryptionKey(
    val newEncryptionKey: ByteArray
) : Function
