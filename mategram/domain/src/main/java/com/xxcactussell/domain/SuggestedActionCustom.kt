package com.xxcactussell.domain

data class SuggestedActionCustom(
    val name: String,
    val title: FormattedText,
    val description: FormattedText,
    val url: String
) : SuggestedAction
