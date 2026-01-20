package com.xxcactussell.domain

data class Invoice(
    val currency: String,
    val priceParts: List<LabeledPricePart>,
    val subscriptionPeriod: Int,
    val maxTipAmount: Long,
    val suggestedTipAmounts: LongArray,
    val recurringPaymentTermsOfServiceUrl: String,
    val termsOfServiceUrl: String,
    val isTest: Boolean,
    val needName: Boolean,
    val needPhoneNumber: Boolean,
    val needEmailAddress: Boolean,
    val needShippingAddress: Boolean,
    val sendPhoneNumberToProvider: Boolean,
    val sendEmailAddressToProvider: Boolean,
    val isFlexible: Boolean
) : Object
