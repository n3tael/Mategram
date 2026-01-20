package com.xxcactussell.domain

data class PassportElementsWithErrors(
    val elements: List<PassportElement>,
    val errors: List<PassportElementError>
) : Object
