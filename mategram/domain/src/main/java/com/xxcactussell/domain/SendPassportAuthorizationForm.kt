package com.xxcactussell.domain

data class SendPassportAuthorizationForm(
    val authorizationFormId: Int,
    val types: List<PassportElementType>
) : Function
