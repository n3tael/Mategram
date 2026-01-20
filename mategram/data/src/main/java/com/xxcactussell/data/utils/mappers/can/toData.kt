package com.xxcactussell.data.utils.mappers.can

import com.xxcactussell.data.utils.mappers.formatted.toData
import com.xxcactussell.data.utils.mappers.store.toData
import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: Domain -> TdApi

fun CanBotSendMessages.toData(): TdApi.CanBotSendMessages = TdApi.CanBotSendMessages(
    this.botUserId
)

fun CanPostStory.toData(): TdApi.CanPostStory = TdApi.CanPostStory(
    this.chatId
)

fun CanPostStoryResult.toData(): TdApi.CanPostStoryResult = when(this) {
    is CanPostStoryResultOk -> this.toData()
    is CanPostStoryResultPremiumNeeded -> this.toData()
    is CanPostStoryResultBoostNeeded -> this.toData()
    is CanPostStoryResultActiveStoryLimitExceeded -> this.toData()
    is CanPostStoryResultWeeklyLimitExceeded -> this.toData()
    is CanPostStoryResultMonthlyLimitExceeded -> this.toData()
}

fun CanPostStoryResultActiveStoryLimitExceeded.toData(): TdApi.CanPostStoryResultActiveStoryLimitExceeded = TdApi.CanPostStoryResultActiveStoryLimitExceeded(
)

fun CanPostStoryResultBoostNeeded.toData(): TdApi.CanPostStoryResultBoostNeeded = TdApi.CanPostStoryResultBoostNeeded(
)

fun CanPostStoryResultMonthlyLimitExceeded.toData(): TdApi.CanPostStoryResultMonthlyLimitExceeded = TdApi.CanPostStoryResultMonthlyLimitExceeded(
    this.retryAfter
)

fun CanPostStoryResultOk.toData(): TdApi.CanPostStoryResultOk = TdApi.CanPostStoryResultOk(
    this.storyCount
)

fun CanPostStoryResultPremiumNeeded.toData(): TdApi.CanPostStoryResultPremiumNeeded = TdApi.CanPostStoryResultPremiumNeeded(
)

fun CanPostStoryResultWeeklyLimitExceeded.toData(): TdApi.CanPostStoryResultWeeklyLimitExceeded = TdApi.CanPostStoryResultWeeklyLimitExceeded(
    this.retryAfter
)

fun CanPurchaseFromStore.toData(): TdApi.CanPurchaseFromStore = TdApi.CanPurchaseFromStore(
    this.purpose.toData()
)

fun CanSendGift.toData(): TdApi.CanSendGift = TdApi.CanSendGift(
    this.giftId
)

fun CanSendGiftResult.toData(): TdApi.CanSendGiftResult = when(this) {
    is CanSendGiftResultOk -> this.toData()
    is CanSendGiftResultFail -> this.toData()
}

fun CanSendGiftResultFail.toData(): TdApi.CanSendGiftResultFail = TdApi.CanSendGiftResultFail(
    this.reason.toData()
)

fun CanSendGiftResultOk.toData(): TdApi.CanSendGiftResultOk = TdApi.CanSendGiftResultOk(
)

fun CanSendMessageToUser.toData(): TdApi.CanSendMessageToUser = TdApi.CanSendMessageToUser(
    this.userId,
    this.onlyLocal
)

fun CanSendMessageToUserResult.toData(): TdApi.CanSendMessageToUserResult = when(this) {
    is CanSendMessageToUserResultOk -> this.toData()
    is CanSendMessageToUserResultUserHasPaidMessages -> this.toData()
    is CanSendMessageToUserResultUserIsDeleted -> this.toData()
    is CanSendMessageToUserResultUserRestrictsNewChats -> this.toData()
}

fun CanSendMessageToUserResultOk.toData(): TdApi.CanSendMessageToUserResultOk = TdApi.CanSendMessageToUserResultOk(
)

fun CanSendMessageToUserResultUserHasPaidMessages.toData(): TdApi.CanSendMessageToUserResultUserHasPaidMessages = TdApi.CanSendMessageToUserResultUserHasPaidMessages(
    this.outgoingPaidMessageStarCount
)

fun CanSendMessageToUserResultUserIsDeleted.toData(): TdApi.CanSendMessageToUserResultUserIsDeleted = TdApi.CanSendMessageToUserResultUserIsDeleted(
)

fun CanSendMessageToUserResultUserRestrictsNewChats.toData(): TdApi.CanSendMessageToUserResultUserRestrictsNewChats = TdApi.CanSendMessageToUserResultUserRestrictsNewChats(
)

fun CanTransferOwnership.toData(): TdApi.CanTransferOwnership = TdApi.CanTransferOwnership(
)

fun CanTransferOwnershipResult.toData(): TdApi.CanTransferOwnershipResult = when(this) {
    is CanTransferOwnershipResultOk -> this.toData()
    is CanTransferOwnershipResultPasswordNeeded -> this.toData()
    is CanTransferOwnershipResultPasswordTooFresh -> this.toData()
    is CanTransferOwnershipResultSessionTooFresh -> this.toData()
}

fun CanTransferOwnershipResultOk.toData(): TdApi.CanTransferOwnershipResultOk = TdApi.CanTransferOwnershipResultOk(
)

fun CanTransferOwnershipResultPasswordNeeded.toData(): TdApi.CanTransferOwnershipResultPasswordNeeded = TdApi.CanTransferOwnershipResultPasswordNeeded(
)

fun CanTransferOwnershipResultPasswordTooFresh.toData(): TdApi.CanTransferOwnershipResultPasswordTooFresh = TdApi.CanTransferOwnershipResultPasswordTooFresh(
    this.retryAfter
)

fun CanTransferOwnershipResultSessionTooFresh.toData(): TdApi.CanTransferOwnershipResultSessionTooFresh = TdApi.CanTransferOwnershipResultSessionTooFresh(
    this.retryAfter
)

