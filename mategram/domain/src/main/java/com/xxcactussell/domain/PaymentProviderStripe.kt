package com.xxcactussell.domain

data class PaymentProviderStripe(
    val publishableKey: String,
    val needCountry: Boolean,
    val needPostalCode: Boolean,
    val needCardholderName: Boolean
) : PaymentProvider
