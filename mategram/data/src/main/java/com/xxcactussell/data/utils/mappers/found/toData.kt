package com.xxcactussell.data.utils.mappers.found

import com.xxcactussell.data.utils.mappers.affiliate.toData
import com.xxcactussell.data.utils.mappers.bots.toData
import com.xxcactussell.data.utils.mappers.chat.toData
import com.xxcactussell.data.utils.mappers.downloaded.toData
import com.xxcactussell.data.utils.mappers.file.toData
import com.xxcactussell.data.utils.mappers.message.toData
import com.xxcactussell.data.utils.mappers.public.toData
import com.xxcactussell.data.utils.mappers.stories.toData
import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: Domain -> TdApi

fun FoundAffiliateProgram.toData(): TdApi.FoundAffiliateProgram = TdApi.FoundAffiliateProgram(
    this.botUserId,
    this.info.toData()
)

fun FoundAffiliatePrograms.toData(): TdApi.FoundAffiliatePrograms = TdApi.FoundAffiliatePrograms(
    this.totalCount,
    this.programs.map { it.toData() }.toTypedArray(),
    this.nextOffset
)

fun FoundChatBoosts.toData(): TdApi.FoundChatBoosts = TdApi.FoundChatBoosts(
    this.totalCount,
    this.boosts.map { it.toData() }.toTypedArray(),
    this.nextOffset
)

fun FoundChatMessages.toData(): TdApi.FoundChatMessages = TdApi.FoundChatMessages(
    this.totalCount,
    this.messages.map { it.toData() }.toTypedArray(),
    this.nextFromMessageId
)

fun FoundFileDownloads.toData(): TdApi.FoundFileDownloads = TdApi.FoundFileDownloads(
    this.totalCounts.toData(),
    this.files.map { it.toData() }.toTypedArray(),
    this.nextOffset
)

fun FoundMessages.toData(): TdApi.FoundMessages = TdApi.FoundMessages(
    this.totalCount,
    this.messages.map { it.toData() }.toTypedArray(),
    this.nextOffset
)

fun FoundPosition.toData(): TdApi.FoundPosition = TdApi.FoundPosition(
    this.position
)

fun FoundPositions.toData(): TdApi.FoundPositions = TdApi.FoundPositions(
    this.totalCount,
    this.positions
)

fun FoundPublicPosts.toData(): TdApi.FoundPublicPosts = TdApi.FoundPublicPosts(
    this.messages.map { it.toData() }.toTypedArray(),
    this.nextOffset,
    this.searchLimits?.toData(),
    this.areLimitsExceeded
)

fun FoundStories.toData(): TdApi.FoundStories = TdApi.FoundStories(
    this.totalCount,
    this.stories.map { it.toData() }.toTypedArray(),
    this.nextOffset
)

fun FoundWebApp.toData(): TdApi.FoundWebApp = TdApi.FoundWebApp(
    this.webApp.toData(),
    this.requestWriteAccess,
    this.skipConfirmation
)

