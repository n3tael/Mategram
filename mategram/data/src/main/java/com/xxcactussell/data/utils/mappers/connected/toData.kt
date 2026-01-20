package com.xxcactussell.data.utils.mappers.connected

import com.xxcactussell.data.utils.mappers.affiliate.toData
import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: Domain -> TdApi

fun ConnectedAffiliateProgram.toData(): TdApi.ConnectedAffiliateProgram = TdApi.ConnectedAffiliateProgram(
    this.url,
    this.botUserId,
    this.parameters.toData(),
    this.connectionDate,
    this.isDisconnected,
    this.userCount,
    this.revenueStarCount
)

fun ConnectedAffiliatePrograms.toData(): TdApi.ConnectedAffiliatePrograms = TdApi.ConnectedAffiliatePrograms(
    this.totalCount,
    this.programs.map { it.toData() }.toTypedArray(),
    this.nextOffset
)

fun ConnectedWebsite.toData(): TdApi.ConnectedWebsite = TdApi.ConnectedWebsite(
    this.id,
    this.domainName,
    this.botUserId,
    this.browser,
    this.platform,
    this.logInDate,
    this.lastActiveDate,
    this.ipAddress,
    this.location
)

fun ConnectedWebsites.toData(): TdApi.ConnectedWebsites = TdApi.ConnectedWebsites(
    this.websites.map { it.toData() }.toTypedArray()
)

