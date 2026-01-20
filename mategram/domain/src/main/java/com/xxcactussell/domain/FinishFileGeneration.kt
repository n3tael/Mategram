package com.xxcactussell.domain

data class FinishFileGeneration(
    val generationId: Long,
    val error: Error
) : Function
