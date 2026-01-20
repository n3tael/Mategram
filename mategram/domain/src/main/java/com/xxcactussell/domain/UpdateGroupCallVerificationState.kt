package com.xxcactussell.domain

data class UpdateGroupCallVerificationState(
    val groupCallId: Int,
    val generation: Int,
    val emojis: List<String>
) : Update
