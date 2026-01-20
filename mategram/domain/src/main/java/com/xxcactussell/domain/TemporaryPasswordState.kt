package com.xxcactussell.domain

data class TemporaryPasswordState(
    val hasPassword: Boolean,
    val validFor: Int
) : Object
