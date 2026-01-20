package com.xxcactussell.domain

data class GetPhoneNumberInfoSync(
    val languageCode: String,
    val phoneNumberPrefix: String
) : Function
