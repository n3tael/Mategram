package com.xxcactussell.domain

data class StoryAreaTypeLocation(
    val location: Location,
    val address: LocationAddress? = null
) : StoryAreaType
