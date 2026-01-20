package com.xxcactussell.domain

data class Session(
    val id: Long,
    val isCurrent: Boolean,
    val isPasswordPending: Boolean,
    val isUnconfirmed: Boolean,
    val canAcceptSecretChats: Boolean,
    val canAcceptCalls: Boolean,
    val type: SessionType,
    val apiId: Int,
    val applicationName: String,
    val applicationVersion: String,
    val isOfficialApplication: Boolean,
    val deviceModel: String,
    val platform: String,
    val systemVersion: String,
    val logInDate: Int,
    val lastActiveDate: Int,
    val ipAddress: String,
    val location: String
) : Object
