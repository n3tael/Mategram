package com.xxcactussell.domain

data class LinkPreview(
    val url: String,
    val displayUrl: String,
    val siteName: String,
    val title: String,
    val description: FormattedText,
    val author: String,
    val type: LinkPreviewType,
    val hasLargeMedia: Boolean,
    val showLargeMedia: Boolean,
    val showMediaAboveDescription: Boolean,
    val skipConfirmation: Boolean,
    val showAboveText: Boolean,
    val instantViewVersion: Int
) : Object
