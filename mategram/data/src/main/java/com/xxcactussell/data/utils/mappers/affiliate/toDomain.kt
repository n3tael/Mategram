package com.xxcactussell.data.utils.mappers.affiliate

import com.xxcactussell.data.utils.mappers.star.toDomain
import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: TdApi -> Domain

fun TdApi.AffiliateInfo.toDomain(): AffiliateInfo = AffiliateInfo(
    commissionPerMille = this.commissionPerMille,
    affiliateChatId = this.affiliateChatId,
    starAmount = this.starAmount.toDomain()
)

fun TdApi.AffiliateProgramInfo.toDomain(): AffiliateProgramInfo = AffiliateProgramInfo(
    parameters = this.parameters.toDomain(),
    endDate = this.endDate,
    dailyRevenuePerUserAmount = this.dailyRevenuePerUserAmount.toDomain()
)

fun TdApi.AffiliateProgramParameters.toDomain(): AffiliateProgramParameters = AffiliateProgramParameters(
    commissionPerMille = this.commissionPerMille,
    monthCount = this.monthCount
)

fun TdApi.AffiliateProgramSortOrder.toDomain(): AffiliateProgramSortOrder = when(this) {
    is TdApi.AffiliateProgramSortOrderProfitability -> this.toDomain()
    is TdApi.AffiliateProgramSortOrderCreationDate -> this.toDomain()
    is TdApi.AffiliateProgramSortOrderRevenue -> this.toDomain()
    else -> throw IllegalArgumentException("Unknown type: ${this.javaClass}")
}

fun TdApi.AffiliateProgramSortOrderCreationDate.toDomain(): AffiliateProgramSortOrderCreationDate = AffiliateProgramSortOrderCreationDate

fun TdApi.AffiliateProgramSortOrderProfitability.toDomain(): AffiliateProgramSortOrderProfitability = AffiliateProgramSortOrderProfitability

fun TdApi.AffiliateProgramSortOrderRevenue.toDomain(): AffiliateProgramSortOrderRevenue = AffiliateProgramSortOrderRevenue

fun TdApi.AffiliateType.toDomain(): AffiliateType = when(this) {
    is TdApi.AffiliateTypeCurrentUser -> this.toDomain()
    is TdApi.AffiliateTypeBot -> this.toDomain()
    is TdApi.AffiliateTypeChannel -> this.toDomain()
    else -> throw IllegalArgumentException("Unknown type: ${this.javaClass}")
}

fun TdApi.AffiliateTypeBot.toDomain(): AffiliateTypeBot = AffiliateTypeBot(
    userId = this.userId
)

fun TdApi.AffiliateTypeChannel.toDomain(): AffiliateTypeChannel = AffiliateTypeChannel(
    chatId = this.chatId
)

fun TdApi.AffiliateTypeCurrentUser.toDomain(): AffiliateTypeCurrentUser = AffiliateTypeCurrentUser

