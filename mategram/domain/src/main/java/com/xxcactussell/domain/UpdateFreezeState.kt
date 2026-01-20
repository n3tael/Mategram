package com.xxcactussell.domain

data class UpdateFreezeState(
    val isFrozen: Boolean,
    val freezingDate: Int,
    val deletionDate: Int,
    val appealLink: String
) : Update
