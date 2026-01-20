package com.xxcactussell.domain

data class UpdateUserStatus(
    val userId: Long,
    val status: UserStatus
) : Update
