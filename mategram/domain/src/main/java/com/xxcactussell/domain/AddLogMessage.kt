package com.xxcactussell.domain

data class AddLogMessage(
    val verbosityLevel: Int,
    val text: String
) : Function
