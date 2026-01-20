package com.xxcactussell.domain

data class GetPaymentForm(
    val inputInvoice: InputInvoice,
    val theme: ThemeParameters
) : Function
