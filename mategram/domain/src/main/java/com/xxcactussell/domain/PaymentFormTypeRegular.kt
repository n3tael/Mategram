package com.xxcactussell.domain

data class PaymentFormTypeRegular(
    val invoice: Invoice,
    val paymentProviderUserId: Long,
    val paymentProvider: PaymentProvider,
    val additionalPaymentOptions: List<PaymentOption>,
    val savedOrderInfo: OrderInfo? = null,
    val savedCredentials: List<SavedCredentials>,
    val canSaveCredentials: Boolean,
    val needPassword: Boolean
) : PaymentFormType
