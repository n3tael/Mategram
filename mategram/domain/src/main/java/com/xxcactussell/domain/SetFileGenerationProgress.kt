package com.xxcactussell.domain

data class SetFileGenerationProgress(
    val generationId: Long,
    val expectedSize: Long,
    val localPrefixSize: Long
) : Function
