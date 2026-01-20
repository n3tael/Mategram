package com.xxcactussell.domain

data class PhoneNumberInfo(
    val country: CountryInfo? = null,
    val countryCallingCode: String,
    val formattedPhoneNumber: String,
    val isAnonymous: Boolean
) : Object
