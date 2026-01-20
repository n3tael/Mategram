package com.xxcactussell.domain

data class ReportSponsoredResultOptionRequired(
    val title: String,
    val options: List<ReportOption>
) : ReportSponsoredResult
