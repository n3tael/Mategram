package com.xxcactussell.domain

data class AccountInfo(
    val registrationMonth: Int,
    val registrationYear: Int,
    val phoneNumberCountryCode: String,
    val lastNameChangeDate: Int,
    val lastPhotoChangeDate: Int
) : Object
