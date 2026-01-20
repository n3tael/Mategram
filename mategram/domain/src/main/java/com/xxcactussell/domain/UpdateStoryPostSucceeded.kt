package com.xxcactussell.domain

data class UpdateStoryPostSucceeded(
    val story: Story,
    val oldStoryId: Int
) : Update
