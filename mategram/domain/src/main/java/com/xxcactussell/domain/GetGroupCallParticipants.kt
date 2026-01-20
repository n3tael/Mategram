package com.xxcactussell.domain

data class GetGroupCallParticipants(
    val inputGroupCall: InputGroupCall,
    val limit: Int
) : Function
