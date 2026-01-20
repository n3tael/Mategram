package com.xxcactussell.domain

data class CallStateDiscarded(
    val reason: CallDiscardReason,
    val needRating: Boolean,
    val needDebugInformation: Boolean,
    val needLog: Boolean
) : CallState
