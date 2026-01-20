package com.xxcactussell.domain

data class ConnectedWebsite(
    val id: Long,
    val domainName: String,
    val botUserId: Long,
    val browser: String,
    val platform: String,
    val logInDate: Int,
    val lastActiveDate: Int,
    val ipAddress: String,
    val location: String
) : Object
