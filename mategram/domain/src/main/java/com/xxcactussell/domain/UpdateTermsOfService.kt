package com.xxcactussell.domain

data class UpdateTermsOfService(
    val termsOfServiceId: String,
    val termsOfService: TermsOfService
) : Update
