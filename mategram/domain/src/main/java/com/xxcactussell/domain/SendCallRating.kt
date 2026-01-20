package com.xxcactussell.domain

data class SendCallRating(
    val callId: Int,
    val rating: Int,
    val comment: String,
    val problems: List<CallProblem>
) : Function
