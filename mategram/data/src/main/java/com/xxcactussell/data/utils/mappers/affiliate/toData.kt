package com.xxcactussell.data.utils.mappers.affiliate

import com.xxcactussell.data.utils.mappers.star.toData
import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: Domain -> TdApi

fun AffiliateInfo.toData(): TdApi.AffiliateInfo = TdApi.AffiliateInfo(
    this.commissionPerMille,
    this.affiliateChatId,
    this.starAmount.toData()
)

fun AffiliateProgramInfo.toData(): TdApi.AffiliateProgramInfo = TdApi.AffiliateProgramInfo(
    this.parameters.toData(),
    this.endDate,
    this.dailyRevenuePerUserAmount.toData()
)

fun AffiliateProgramParameters.toData(): TdApi.AffiliateProgramParameters = TdApi.AffiliateProgramParameters(
    this.commissionPerMille,
    this.monthCount
)

fun AffiliateProgramSortOrder.toData(): TdApi.AffiliateProgramSortOrder = when(this) {
    is AffiliateProgramSortOrderProfitability -> this.toData()
    is AffiliateProgramSortOrderCreationDate -> this.toData()
    is AffiliateProgramSortOrderRevenue -> this.toData()
}

fun AffiliateProgramSortOrderCreationDate.toData(): TdApi.AffiliateProgramSortOrderCreationDate = TdApi.AffiliateProgramSortOrderCreationDate(
)

fun AffiliateProgramSortOrderProfitability.toData(): TdApi.AffiliateProgramSortOrderProfitability = TdApi.AffiliateProgramSortOrderProfitability(
)

fun AffiliateProgramSortOrderRevenue.toData(): TdApi.AffiliateProgramSortOrderRevenue = TdApi.AffiliateProgramSortOrderRevenue(
)

fun AffiliateType.toData(): TdApi.AffiliateType = when(this) {
    is AffiliateTypeCurrentUser -> this.toData()
    is AffiliateTypeBot -> this.toData()
    is AffiliateTypeChannel -> this.toData()
}

fun AffiliateTypeBot.toData(): TdApi.AffiliateTypeBot = TdApi.AffiliateTypeBot(
    this.userId
)

fun AffiliateTypeChannel.toData(): TdApi.AffiliateTypeChannel = TdApi.AffiliateTypeChannel(
    this.chatId
)

fun AffiliateTypeCurrentUser.toData(): TdApi.AffiliateTypeCurrentUser = TdApi.AffiliateTypeCurrentUser(
)

