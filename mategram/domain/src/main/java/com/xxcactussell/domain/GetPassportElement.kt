package com.xxcactussell.domain

data class GetPassportElement(
    val type: PassportElementType,
    val password: String
) : Function
