package com.xxcactussell.domain

data class ReportStoryResultOptionRequired(
    val title: String,
    val options: List<ReportOption>
) : ReportStoryResult
