package com.xxcactussell.domain

data class SetChatAffiliateProgram(
    val chatId: Long,
    val parameters: AffiliateProgramParameters
) : Function
