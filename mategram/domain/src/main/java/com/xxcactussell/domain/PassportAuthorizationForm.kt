package com.xxcactussell.domain

data class PassportAuthorizationForm(
    val id: Int,
    val requiredElements: List<PassportRequiredElement>,
    val privacyPolicyUrl: String
) : Object
