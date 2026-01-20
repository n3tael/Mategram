package com.xxcactussell.domain

data class UpdateUserFullInfo(
    val userId: Long,
    val userFullInfo: UserFullInfo
) : Update
