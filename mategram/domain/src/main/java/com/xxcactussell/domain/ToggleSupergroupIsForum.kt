package com.xxcactussell.domain

data class ToggleSupergroupIsForum(
    val supergroupId: Long,
    val isForum: Boolean,
    val hasForumTabs: Boolean
) : Function
