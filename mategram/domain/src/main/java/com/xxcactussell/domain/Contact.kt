package com.xxcactussell.domain

data class Contact(
    val phoneNumber: String,
    val firstName: String,
    val lastName: String,
    val vcard: String,
    val userId: Long
) : Object
