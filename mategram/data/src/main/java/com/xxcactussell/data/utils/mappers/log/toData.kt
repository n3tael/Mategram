package com.xxcactussell.data.utils.mappers.log

import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: Domain -> TdApi

fun LogStream.toData(): TdApi.LogStream = when(this) {
    is LogStreamDefault -> this.toData()
    is LogStreamFile -> this.toData()
    is LogStreamEmpty -> this.toData()
}

fun LogStreamDefault.toData(): TdApi.LogStreamDefault = TdApi.LogStreamDefault(
)

fun LogStreamEmpty.toData(): TdApi.LogStreamEmpty = TdApi.LogStreamEmpty(
)

fun LogStreamFile.toData(): TdApi.LogStreamFile = TdApi.LogStreamFile(
    this.path,
    this.maxFileSize,
    this.redirectStderr
)

fun LogTags.toData(): TdApi.LogTags = TdApi.LogTags(
    this.tags.toTypedArray()
)

fun LogVerbosityLevel.toData(): TdApi.LogVerbosityLevel = TdApi.LogVerbosityLevel(
    this.verbosityLevel
)

