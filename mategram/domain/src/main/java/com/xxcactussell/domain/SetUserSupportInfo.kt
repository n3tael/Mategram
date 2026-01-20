package com.xxcactussell.domain

data class SetUserSupportInfo(
    val userId: Long,
    val message: FormattedText
) : Function
