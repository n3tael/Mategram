package com.xxcactussell.domain

data class ReorderSupergroupActiveUsernames(
    val supergroupId: Long,
    val usernames: List<String>
) : Function
