package com.xxcactussell.domain

data class UpgradedGiftValueInfo(
    val currency: String,
    val value: Long,
    val isValueAverage: Boolean,
    val initialSaleDate: Int,
    val initialSaleStarCount: Long,
    val initialSalePrice: Long,
    val lastSaleDate: Int,
    val lastSalePrice: Long,
    val isLastSaleOnFragment: Boolean,
    val minimumPrice: Long,
    val averageSalePrice: Long,
    val telegramListedGiftCount: Int,
    val fragmentListedGiftCount: Int,
    val fragmentUrl: String
) : Object
