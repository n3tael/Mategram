package com.xxcactussell.domain

data class UpdateStoryPostFailed(
    val story: Story,
    val error: Error,
    val errorType: CanPostStoryResult? = null
) : Update
