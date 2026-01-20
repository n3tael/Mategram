package com.xxcactussell.domain

data class Address(
    val countryCode: String,
    val state: String,
    val city: String,
    val streetLine1: String,
    val streetLine2: String,
    val postalCode: String
) : Object
