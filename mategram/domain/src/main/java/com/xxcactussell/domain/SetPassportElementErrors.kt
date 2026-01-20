package com.xxcactussell.domain

data class SetPassportElementErrors(
    val userId: Long,
    val errors: List<InputPassportElementError>
) : Function
