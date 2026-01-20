package com.xxcactussell.domain

data class InternalLinkTypeMessageDraft(
    val text: FormattedText,
    val containsLink: Boolean
) : InternalLinkType
