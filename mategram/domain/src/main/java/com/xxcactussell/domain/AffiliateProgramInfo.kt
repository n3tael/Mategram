package com.xxcactussell.domain

data class AffiliateProgramInfo(
    val parameters: AffiliateProgramParameters,
    val endDate: Int,
    val dailyRevenuePerUserAmount: StarAmount
) : Object
