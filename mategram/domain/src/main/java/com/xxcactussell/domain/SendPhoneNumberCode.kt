package com.xxcactussell.domain

data class SendPhoneNumberCode(
    val phoneNumber: String,
    val settings: PhoneNumberAuthenticationSettings,
    val type: PhoneNumberCodeType
) : Function
