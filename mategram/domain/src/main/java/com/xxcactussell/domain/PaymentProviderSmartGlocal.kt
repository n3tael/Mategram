package com.xxcactussell.domain

data class PaymentProviderSmartGlocal(
    val publicToken: String,
    val tokenizeUrl: String
) : PaymentProvider
