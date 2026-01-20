package com.xxcactussell.domain

data class SuggestedPostInfo(
    val price: SuggestedPostPrice? = null,
    val sendDate: Int,
    val state: SuggestedPostState,
    val canBeApproved: Boolean,
    val canBeDeclined: Boolean
) : Object
