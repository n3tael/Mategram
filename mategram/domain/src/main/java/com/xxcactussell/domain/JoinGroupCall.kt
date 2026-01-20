package com.xxcactussell.domain

data class JoinGroupCall(
    val inputGroupCall: InputGroupCall,
    val joinParameters: GroupCallJoinParameters
) : Function
