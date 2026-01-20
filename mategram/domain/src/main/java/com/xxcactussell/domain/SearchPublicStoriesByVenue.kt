package com.xxcactussell.domain

data class SearchPublicStoriesByVenue(
    val venueProvider: String,
    val venueId: String,
    val offset: String,
    val limit: Int
) : Function
