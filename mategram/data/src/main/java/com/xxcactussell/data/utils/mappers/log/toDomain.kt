package com.xxcactussell.data.utils.mappers.log

import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: TdApi -> Domain

fun TdApi.LogStream.toDomain(): LogStream = when(this) {
    is TdApi.LogStreamDefault -> this.toDomain()
    is TdApi.LogStreamFile -> this.toDomain()
    is TdApi.LogStreamEmpty -> this.toDomain()
    else -> throw IllegalArgumentException("Unknown type: ${this.javaClass}")
}

fun TdApi.LogStreamDefault.toDomain(): LogStreamDefault = LogStreamDefault

fun TdApi.LogStreamEmpty.toDomain(): LogStreamEmpty = LogStreamEmpty

fun TdApi.LogStreamFile.toDomain(): LogStreamFile = LogStreamFile(
    path = this.path,
    maxFileSize = this.maxFileSize,
    redirectStderr = this.redirectStderr
)

fun TdApi.LogTags.toDomain(): LogTags = LogTags(
    tags = this.tags.toList()
)

fun TdApi.LogVerbosityLevel.toDomain(): LogVerbosityLevel = LogVerbosityLevel(
    verbosityLevel = this.verbosityLevel
)

