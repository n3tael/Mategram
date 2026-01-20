package com.xxcactussell.domain

data class PersonalDetails(
    val firstName: String,
    val middleName: String,
    val lastName: String,
    val nativeFirstName: String,
    val nativeMiddleName: String,
    val nativeLastName: String,
    val birthdate: Date,
    val gender: String,
    val countryCode: String,
    val residenceCountryCode: String
) : Object
