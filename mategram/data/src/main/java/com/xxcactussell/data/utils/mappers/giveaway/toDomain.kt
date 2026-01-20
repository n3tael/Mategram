package com.xxcactussell.data.utils.mappers.giveaway

import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: TdApi -> Domain

fun TdApi.GiveawayInfo.toDomain(): GiveawayInfo = when(this) {
    is TdApi.GiveawayInfoOngoing -> this.toDomain()
    is TdApi.GiveawayInfoCompleted -> this.toDomain()
    else -> throw IllegalArgumentException("Unknown type: ${this.javaClass}")
}

fun TdApi.GiveawayInfoCompleted.toDomain(): GiveawayInfoCompleted = GiveawayInfoCompleted(
    creationDate = this.creationDate,
    actualWinnersSelectionDate = this.actualWinnersSelectionDate,
    wasRefunded = this.wasRefunded,
    isWinner = this.isWinner,
    winnerCount = this.winnerCount,
    activationCount = this.activationCount,
    giftCode = this.giftCode,
    wonStarCount = this.wonStarCount
)

fun TdApi.GiveawayInfoOngoing.toDomain(): GiveawayInfoOngoing = GiveawayInfoOngoing(
    creationDate = this.creationDate,
    status = this.status.toDomain(),
    isEnded = this.isEnded
)

fun TdApi.GiveawayParameters.toDomain(): GiveawayParameters = GiveawayParameters(
    boostedChatId = this.boostedChatId,
    additionalChatIds = this.additionalChatIds,
    winnersSelectionDate = this.winnersSelectionDate,
    onlyNewMembers = this.onlyNewMembers,
    hasPublicWinners = this.hasPublicWinners,
    countryCodes = this.countryCodes.toList(),
    prizeDescription = this.prizeDescription
)

fun TdApi.GiveawayParticipantStatus.toDomain(): GiveawayParticipantStatus = when(this) {
    is TdApi.GiveawayParticipantStatusEligible -> this.toDomain()
    is TdApi.GiveawayParticipantStatusParticipating -> this.toDomain()
    is TdApi.GiveawayParticipantStatusAlreadyWasMember -> this.toDomain()
    is TdApi.GiveawayParticipantStatusAdministrator -> this.toDomain()
    is TdApi.GiveawayParticipantStatusDisallowedCountry -> this.toDomain()
    else -> throw IllegalArgumentException("Unknown type: ${this.javaClass}")
}

fun TdApi.GiveawayParticipantStatusAdministrator.toDomain(): GiveawayParticipantStatusAdministrator = GiveawayParticipantStatusAdministrator(
    chatId = this.chatId
)

fun TdApi.GiveawayParticipantStatusAlreadyWasMember.toDomain(): GiveawayParticipantStatusAlreadyWasMember = GiveawayParticipantStatusAlreadyWasMember(
    joinedChatDate = this.joinedChatDate
)

fun TdApi.GiveawayParticipantStatusDisallowedCountry.toDomain(): GiveawayParticipantStatusDisallowedCountry = GiveawayParticipantStatusDisallowedCountry(
    userCountryCode = this.userCountryCode
)

fun TdApi.GiveawayParticipantStatusEligible.toDomain(): GiveawayParticipantStatusEligible = GiveawayParticipantStatusEligible

fun TdApi.GiveawayParticipantStatusParticipating.toDomain(): GiveawayParticipantStatusParticipating = GiveawayParticipantStatusParticipating

fun TdApi.GiveawayPrize.toDomain(): GiveawayPrize = when(this) {
    is TdApi.GiveawayPrizePremium -> this.toDomain()
    is TdApi.GiveawayPrizeStars -> this.toDomain()
    else -> throw IllegalArgumentException("Unknown type: ${this.javaClass}")
}

fun TdApi.GiveawayPrizePremium.toDomain(): GiveawayPrizePremium = GiveawayPrizePremium(
    monthCount = this.monthCount
)

fun TdApi.GiveawayPrizeStars.toDomain(): GiveawayPrizeStars = GiveawayPrizeStars(
    starCount = this.starCount
)

