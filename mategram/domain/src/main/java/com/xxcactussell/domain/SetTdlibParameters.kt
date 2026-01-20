package com.xxcactussell.domain

data class SetTdlibParameters(
    val useTestDc: Boolean,
    val databaseDirectory: String,
    val filesDirectory: String,
    val databaseEncryptionKey: ByteArray,
    val useFileDatabase: Boolean,
    val useChatInfoDatabase: Boolean,
    val useMessageDatabase: Boolean,
    val useSecretChats: Boolean,
    val apiId: Int,
    val apiHash: String,
    val systemLanguageCode: String,
    val deviceModel: String,
    val systemVersion: String,
    val applicationVersion: String
) : Function
