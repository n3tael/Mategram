package com.xxcactussell.domain

data class SetLogTagVerbosityLevel(
    val tag: String,
    val newVerbosityLevel: Int
) : Function
