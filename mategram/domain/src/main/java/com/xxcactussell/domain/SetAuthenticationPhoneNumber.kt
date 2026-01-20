package com.xxcactussell.domain

data class SetAuthenticationPhoneNumber(
    val phoneNumber: String,
    val settings: PhoneNumberAuthenticationSettings
) : Function
