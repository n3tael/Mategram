package com.xxcactussell.domain

data class SetBusinessAccountName(
    val businessConnectionId: String,
    val firstName: String,
    val lastName: String
) : Function
