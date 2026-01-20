package com.xxcactussell.domain

data class Usernames(
    val activeUsernames: List<String>,
    val disabledUsernames: List<String>,
    val editableUsername: String
) : Object
