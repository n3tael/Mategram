package com.xxcactussell.domain

data class Venue(
    val location: Location,
    val title: String,
    val address: String,
    val provider: String,
    val id: String,
    val type: String
) : Object
