package com.xxcactussell.data.utils.mappers.giveaway

import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: Domain -> TdApi

fun GiveawayInfo.toData(): TdApi.GiveawayInfo = when(this) {
    is GiveawayInfoOngoing -> this.toData()
    is GiveawayInfoCompleted -> this.toData()
}

fun GiveawayInfoCompleted.toData(): TdApi.GiveawayInfoCompleted = TdApi.GiveawayInfoCompleted(
    this.creationDate,
    this.actualWinnersSelectionDate,
    this.wasRefunded,
    this.isWinner,
    this.winnerCount,
    this.activationCount,
    this.giftCode,
    this.wonStarCount
)

fun GiveawayInfoOngoing.toData(): TdApi.GiveawayInfoOngoing = TdApi.GiveawayInfoOngoing(
    this.creationDate,
    this.status.toData(),
    this.isEnded
)

fun GiveawayParameters.toData(): TdApi.GiveawayParameters = TdApi.GiveawayParameters(
    this.boostedChatId,
    this.additionalChatIds,
    this.winnersSelectionDate,
    this.onlyNewMembers,
    this.hasPublicWinners,
    this.countryCodes.toTypedArray(),
    this.prizeDescription
)

fun GiveawayParticipantStatus.toData(): TdApi.GiveawayParticipantStatus = when(this) {
    is GiveawayParticipantStatusEligible -> this.toData()
    is GiveawayParticipantStatusParticipating -> this.toData()
    is GiveawayParticipantStatusAlreadyWasMember -> this.toData()
    is GiveawayParticipantStatusAdministrator -> this.toData()
    is GiveawayParticipantStatusDisallowedCountry -> this.toData()
}

fun GiveawayParticipantStatusAdministrator.toData(): TdApi.GiveawayParticipantStatusAdministrator = TdApi.GiveawayParticipantStatusAdministrator(
    this.chatId
)

fun GiveawayParticipantStatusAlreadyWasMember.toData(): TdApi.GiveawayParticipantStatusAlreadyWasMember = TdApi.GiveawayParticipantStatusAlreadyWasMember(
    this.joinedChatDate
)

fun GiveawayParticipantStatusDisallowedCountry.toData(): TdApi.GiveawayParticipantStatusDisallowedCountry = TdApi.GiveawayParticipantStatusDisallowedCountry(
    this.userCountryCode
)

fun GiveawayParticipantStatusEligible.toData(): TdApi.GiveawayParticipantStatusEligible = TdApi.GiveawayParticipantStatusEligible(
)

fun GiveawayParticipantStatusParticipating.toData(): TdApi.GiveawayParticipantStatusParticipating = TdApi.GiveawayParticipantStatusParticipating(
)

fun GiveawayPrize.toData(): TdApi.GiveawayPrize = when(this) {
    is GiveawayPrizePremium -> this.toData()
    is GiveawayPrizeStars -> this.toData()
}

fun GiveawayPrizePremium.toData(): TdApi.GiveawayPrizePremium = TdApi.GiveawayPrizePremium(
    this.monthCount
)

fun GiveawayPrizeStars.toData(): TdApi.GiveawayPrizeStars = TdApi.GiveawayPrizeStars(
    this.starCount
)

