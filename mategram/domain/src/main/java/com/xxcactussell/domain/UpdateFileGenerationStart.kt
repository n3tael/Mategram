package com.xxcactussell.domain

data class UpdateFileGenerationStart(
    val generationId: Long,
    val originalPath: String,
    val destinationPath: String,
    val conversion: String
) : Update
