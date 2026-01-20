package com.xxcactussell.data.utils.mappers.connected

import com.xxcactussell.data.utils.mappers.affiliate.toDomain
import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: TdApi -> Domain

fun TdApi.ConnectedAffiliateProgram.toDomain(): ConnectedAffiliateProgram = ConnectedAffiliateProgram(
    url = this.url,
    botUserId = this.botUserId,
    parameters = this.parameters.toDomain(),
    connectionDate = this.connectionDate,
    isDisconnected = this.isDisconnected,
    userCount = this.userCount,
    revenueStarCount = this.revenueStarCount
)

fun TdApi.ConnectedAffiliatePrograms.toDomain(): ConnectedAffiliatePrograms = ConnectedAffiliatePrograms(
    totalCount = this.totalCount,
    programs = this.programs.map { it.toDomain() },
    nextOffset = this.nextOffset
)

fun TdApi.ConnectedWebsite.toDomain(): ConnectedWebsite = ConnectedWebsite(
    id = this.id,
    domainName = this.domainName,
    botUserId = this.botUserId,
    browser = this.browser,
    platform = this.platform,
    logInDate = this.logInDate,
    lastActiveDate = this.lastActiveDate,
    ipAddress = this.ipAddress,
    location = this.location
)

fun TdApi.ConnectedWebsites.toDomain(): ConnectedWebsites = ConnectedWebsites(
    websites = this.websites.map { it.toDomain() }
)

