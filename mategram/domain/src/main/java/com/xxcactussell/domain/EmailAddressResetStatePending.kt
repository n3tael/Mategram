package com.xxcactussell.domain

data class EmailAddressResetStatePending(
    val resetIn: Int
) : EmailAddressResetState
