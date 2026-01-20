package com.xxcactussell.domain

data class ShippingOption(
    val id: String,
    val title: String,
    val priceParts: List<LabeledPricePart>
) : Object
