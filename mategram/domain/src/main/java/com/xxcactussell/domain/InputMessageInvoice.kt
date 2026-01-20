package com.xxcactussell.domain

data class InputMessageInvoice(
    val invoice: Invoice,
    val title: String,
    val description: String,
    val photoUrl: String,
    val photoSize: Int,
    val photoWidth: Int,
    val photoHeight: Int,
    val payload: ByteArray,
    val providerToken: String,
    val providerData: String,
    val startParameter: String,
    val paidMedia: InputPaidMedia,
    val paidMediaCaption: FormattedText
) : InputMessageContent
