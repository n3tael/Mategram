package com.xxcactussell.domain

data class GetStarSubscriptions(
    val onlyExpiring: Boolean,
    val offset: String
) : Function
