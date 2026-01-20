package com.xxcactussell.domain

data class SearchPublicStoriesByLocation(
    val address: LocationAddress,
    val offset: String,
    val limit: Int
) : Function
