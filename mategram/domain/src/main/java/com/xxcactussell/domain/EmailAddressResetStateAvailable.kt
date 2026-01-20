package com.xxcactussell.domain

data class EmailAddressResetStateAvailable(
    val waitPeriod: Int
) : EmailAddressResetState
