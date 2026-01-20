package com.xxcactussell.domain

data class ReportChatResultOptionRequired(
    val title: String,
    val options: List<ReportOption>
) : ReportChatResult
