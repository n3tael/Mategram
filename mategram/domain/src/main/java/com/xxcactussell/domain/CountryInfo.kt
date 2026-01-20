package com.xxcactussell.domain

data class CountryInfo(
    val countryCode: String,
    val name: String,
    val englishName: String,
    val isHidden: Boolean,
    val callingCodes: List<String>
) : Object
