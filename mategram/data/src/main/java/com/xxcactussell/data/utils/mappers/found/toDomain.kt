package com.xxcactussell.data.utils.mappers.found

import com.xxcactussell.data.utils.mappers.affiliate.toDomain
import com.xxcactussell.data.utils.mappers.bots.toDomain
import com.xxcactussell.data.utils.mappers.chat.toDomain
import com.xxcactussell.data.utils.mappers.downloaded.toDomain
import com.xxcactussell.data.utils.mappers.file.toDomain
import com.xxcactussell.data.utils.mappers.message.toDomain
import com.xxcactussell.data.utils.mappers.public.toDomain
import com.xxcactussell.data.utils.mappers.stories.toDomain
import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: TdApi -> Domain

fun TdApi.FoundAffiliateProgram.toDomain(): FoundAffiliateProgram = FoundAffiliateProgram(
    botUserId = this.botUserId,
    info = this.info.toDomain()
)

fun TdApi.FoundAffiliatePrograms.toDomain(): FoundAffiliatePrograms = FoundAffiliatePrograms(
    totalCount = this.totalCount,
    programs = this.programs.map { it.toDomain() },
    nextOffset = this.nextOffset
)

fun TdApi.FoundChatBoosts.toDomain(): FoundChatBoosts = FoundChatBoosts(
    totalCount = this.totalCount,
    boosts = this.boosts.map { it.toDomain() },
    nextOffset = this.nextOffset
)

fun TdApi.FoundChatMessages.toDomain(): FoundChatMessages = FoundChatMessages(
    totalCount = this.totalCount,
    messages = this.messages.map { it.toDomain() },
    nextFromMessageId = this.nextFromMessageId
)

fun TdApi.FoundFileDownloads.toDomain(): FoundFileDownloads = FoundFileDownloads(
    totalCounts = this.totalCounts.toDomain(),
    files = this.files.map { it.toDomain() },
    nextOffset = this.nextOffset
)

fun TdApi.FoundMessages.toDomain(): FoundMessages = FoundMessages(
    totalCount = this.totalCount,
    messages = this.messages.map { it.toDomain() },
    nextOffset = this.nextOffset
)

fun TdApi.FoundPosition.toDomain(): FoundPosition = FoundPosition(
    position = this.position
)

fun TdApi.FoundPositions.toDomain(): FoundPositions = FoundPositions(
    totalCount = this.totalCount,
    positions = this.positions
)

fun TdApi.FoundPublicPosts.toDomain(): FoundPublicPosts = FoundPublicPosts(
    messages = this.messages.map { it.toDomain() },
    nextOffset = this.nextOffset,
    searchLimits = this.searchLimits?.toDomain(),
    areLimitsExceeded = this.areLimitsExceeded
)

fun TdApi.FoundStories.toDomain(): FoundStories = FoundStories(
    totalCount = this.totalCount,
    stories = this.stories.map { it.toDomain() },
    nextOffset = this.nextOffset
)

fun TdApi.FoundWebApp.toDomain(): FoundWebApp = FoundWebApp(
    webApp = this.webApp.toDomain(),
    requestWriteAccess = this.requestWriteAccess,
    skipConfirmation = this.skipConfirmation
)

