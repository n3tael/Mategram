package com.xxcactussell.domain

data class ForumTopics(
    val totalCount: Int,
    val topics: List<ForumTopic>,
    val nextOffsetDate: Int,
    val nextOffsetMessageId: Long,
    val nextOffsetMessageThreadId: Long
) : Object
