package com.xxcactussell.domain

data class SearchUserByPhoneNumber(
    val phoneNumber: String,
    val onlyLocal: Boolean
) : Function
