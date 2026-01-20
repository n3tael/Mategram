package com.xxcactussell.data.utils.mappers.message

import com.xxcactussell.data.utils.mappers.alternative.toData
import com.xxcactussell.data.utils.mappers.animation.toData
import com.xxcactussell.data.utils.mappers.bot.toData
import com.xxcactussell.data.utils.mappers.bots.toData
import com.xxcactussell.data.utils.mappers.calls.toData
import com.xxcactussell.data.utils.mappers.chat.toData
import com.xxcactussell.data.utils.mappers.checklist.toData
import com.xxcactussell.data.utils.mappers.dice.toData
import com.xxcactussell.data.utils.mappers.encrypted.toData
import com.xxcactussell.data.utils.mappers.error.toData
import com.xxcactussell.data.utils.mappers.fact.toData
import com.xxcactussell.data.utils.mappers.file.toData
import com.xxcactussell.data.utils.mappers.formatted.toData
import com.xxcactussell.data.utils.mappers.forum.toData
import com.xxcactussell.data.utils.mappers.forward.toData
import com.xxcactussell.data.utils.mappers.game.toData
import com.xxcactussell.data.utils.mappers.giveaway.toData
import com.xxcactussell.data.utils.mappers.input.toData
import com.xxcactussell.data.utils.mappers.link.toData
import com.xxcactussell.data.utils.mappers.location.toData
import com.xxcactussell.data.utils.mappers.monetization.toData
import com.xxcactussell.data.utils.mappers.paid.toData
import com.xxcactussell.data.utils.mappers.passport.toData
import com.xxcactussell.data.utils.mappers.photo.toData
import com.xxcactussell.data.utils.mappers.poll.toData
import com.xxcactussell.data.utils.mappers.product.toData
import com.xxcactussell.data.utils.mappers.reaction.toData
import com.xxcactussell.data.utils.mappers.restriction.toData
import com.xxcactussell.data.utils.mappers.search.toData
import com.xxcactussell.data.utils.mappers.shared.toData
import com.xxcactussell.data.utils.mappers.star.toData
import com.xxcactussell.data.utils.mappers.statistical.toData
import com.xxcactussell.data.utils.mappers.sticker.toData
import com.xxcactussell.data.utils.mappers.suggested.toData
import com.xxcactussell.data.utils.mappers.text.toData
import com.xxcactussell.data.utils.mappers.unread.toData
import com.xxcactussell.data.utils.mappers.user.toData
import com.xxcactussell.data.utils.mappers.venue.toData
import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: Domain -> TdApi

fun DeleteChatMessagesByDate.toData(): TdApi.DeleteChatMessagesByDate = TdApi.DeleteChatMessagesByDate(
    this.chatId,
    this.minDate,
    this.maxDate,
    this.revoke
)

fun DeleteMessages.toData(): TdApi.DeleteMessages = TdApi.DeleteMessages(
    this.chatId,
    this.messageIds,
    this.revoke
)

fun DraftMessage.toData(): TdApi.DraftMessage = TdApi.DraftMessage(
    this.replyTo?.toData(),
    this.date,
    this.inputMessageText.toData(),
    this.effectId,
    this.suggestedPostInfo?.toData()
)

fun EditMessageCaption.toData(): TdApi.EditMessageCaption = TdApi.EditMessageCaption(
    this.chatId,
    this.messageId,
    this.replyMarkup.toData(),
    this.caption.toData(),
    this.showCaptionAboveMedia
)

fun EditMessageLiveLocation.toData(): TdApi.EditMessageLiveLocation = TdApi.EditMessageLiveLocation(
    this.chatId,
    this.messageId,
    this.replyMarkup.toData(),
    this.location.toData(),
    this.livePeriod,
    this.heading,
    this.proximityAlertRadius
)

fun EditMessageMedia.toData(): TdApi.EditMessageMedia = TdApi.EditMessageMedia(
    this.chatId,
    this.messageId,
    this.replyMarkup.toData(),
    this.inputMessageContent.toData()
)

fun EditMessageReplyMarkup.toData(): TdApi.EditMessageReplyMarkup = TdApi.EditMessageReplyMarkup(
    this.chatId,
    this.messageId,
    this.replyMarkup.toData()
)

fun EditMessageText.toData(): TdApi.EditMessageText = TdApi.EditMessageText(
    this.chatId,
    this.messageId,
    this.replyMarkup.toData(),
    this.inputMessageContent.toData()
)

fun ForwardMessages.toData(): TdApi.ForwardMessages = TdApi.ForwardMessages(
    this.chatId,
    this.messageThreadId,
    this.fromChatId,
    this.messageIds,
    this.options.toData(),
    this.sendCopy,
    this.removeCaption
)

fun GetChatMessageByDate.toData(): TdApi.GetChatMessageByDate = TdApi.GetChatMessageByDate(
    this.chatId,
    this.date
)

fun GetChatMessagePosition.toData(): TdApi.GetChatMessagePosition = TdApi.GetChatMessagePosition(
    this.chatId,
    this.topicId.toData(),
    this.filter.toData(),
    this.messageId
)

fun GetMessageThread.toData(): TdApi.GetMessageThread = TdApi.GetMessageThread(
    this.chatId,
    this.messageId
)

fun GetMessageThreadHistory.toData(): TdApi.GetMessageThreadHistory = TdApi.GetMessageThreadHistory(
    this.chatId,
    this.messageId,
    this.fromMessageId,
    this.offset,
    this.limit
)

fun GetMessages.toData(): TdApi.GetMessages = TdApi.GetMessages(
    this.chatId,
    this.messageIds
)

fun Message.toData(): TdApi.Message = TdApi.Message(
    this.id,
    this.senderId.toData(),
    this.chatId,
    this.sendingState?.toData(),
    this.schedulingState?.toData(),
    this.isOutgoing,
    this.isPinned,
    this.isFromOffline,
    this.canBeSaved,
    this.hasTimestampedMedia,
    this.isChannelPost,
    this.isPaidStarSuggestedPost,
    this.isPaidTonSuggestedPost,
    this.containsUnreadMention,
    this.date,
    this.editDate,
    this.forwardInfo?.toData(),
    this.importInfo?.toData(),
    this.interactionInfo?.toData(),
    this.unreadReactions.map { it.toData() }.toTypedArray(),
    this.factCheck?.toData(),
    this.suggestedPostInfo?.toData(),
    this.replyTo?.toData(),
    this.messageThreadId,
    this.topicId?.toData(),
    this.selfDestructType?.toData(),
    this.selfDestructIn,
    this.autoDeleteIn,
    this.viaBotUserId,
    this.senderBusinessBotUserId,
    this.senderBoostCount,
    this.paidMessageStarCount,
    this.authorSignature,
    this.mediaAlbumId,
    this.effectId,
    this.restrictionInfo?.toData(),
    this.content.toData(),
    this.replyMarkup?.toData()
)

fun MessageAnimatedEmoji.toData(): TdApi.MessageAnimatedEmoji = TdApi.MessageAnimatedEmoji(
    this.animatedEmoji.toData(),
    this.emoji
)

fun MessageAnimation.toData(): TdApi.MessageAnimation = TdApi.MessageAnimation(
    this.animation.toData(),
    this.caption.toData(),
    this.showCaptionAboveMedia,
    this.hasSpoiler,
    this.isSecret
)

fun MessageAudio.toData(): TdApi.MessageAudio = TdApi.MessageAudio(
    this.audio.toData(),
    this.caption.toData()
)

fun MessageAutoDeleteTime.toData(): TdApi.MessageAutoDeleteTime = TdApi.MessageAutoDeleteTime(
    this.time
)

fun MessageBasicGroupChatCreate.toData(): TdApi.MessageBasicGroupChatCreate = TdApi.MessageBasicGroupChatCreate(
    this.title,
    this.memberUserIds
)

fun MessageBotWriteAccessAllowed.toData(): TdApi.MessageBotWriteAccessAllowed = TdApi.MessageBotWriteAccessAllowed(
    this.reason.toData()
)

fun MessageCalendar.toData(): TdApi.MessageCalendar = TdApi.MessageCalendar(
    this.totalCount,
    this.days.map { it.toData() }.toTypedArray()
)

fun MessageCalendarDay.toData(): TdApi.MessageCalendarDay = TdApi.MessageCalendarDay(
    this.totalCount,
    this.message.toData()
)

fun MessageCall.toData(): TdApi.MessageCall = TdApi.MessageCall(
    this.isVideo,
    this.discardReason.toData(),
    this.duration
)

fun MessageChatAddMembers.toData(): TdApi.MessageChatAddMembers = TdApi.MessageChatAddMembers(
    this.memberUserIds
)

fun MessageChatBoost.toData(): TdApi.MessageChatBoost = TdApi.MessageChatBoost(
    this.boostCount
)

fun MessageChatChangePhoto.toData(): TdApi.MessageChatChangePhoto = TdApi.MessageChatChangePhoto(
    this.photo.toData()
)

fun MessageChatChangeTitle.toData(): TdApi.MessageChatChangeTitle = TdApi.MessageChatChangeTitle(
    this.title
)

fun MessageChatDeleteMember.toData(): TdApi.MessageChatDeleteMember = TdApi.MessageChatDeleteMember(
    this.userId
)

fun MessageChatDeletePhoto.toData(): TdApi.MessageChatDeletePhoto = TdApi.MessageChatDeletePhoto(
)

fun MessageChatJoinByLink.toData(): TdApi.MessageChatJoinByLink = TdApi.MessageChatJoinByLink(
)

fun MessageChatJoinByRequest.toData(): TdApi.MessageChatJoinByRequest = TdApi.MessageChatJoinByRequest(
)

fun MessageChatSetBackground.toData(): TdApi.MessageChatSetBackground = TdApi.MessageChatSetBackground(
    this.oldBackgroundMessageId,
    this.background.toData(),
    this.onlyForSelf
)

fun MessageChatSetMessageAutoDeleteTime.toData(): TdApi.MessageChatSetMessageAutoDeleteTime = TdApi.MessageChatSetMessageAutoDeleteTime(
    this.messageAutoDeleteTime,
    this.fromUserId
)

fun MessageChatSetTheme.toData(): TdApi.MessageChatSetTheme = TdApi.MessageChatSetTheme(
    this.theme?.toData()
)

fun MessageChatShared.toData(): TdApi.MessageChatShared = TdApi.MessageChatShared(
    this.chat.toData(),
    this.buttonId
)

fun MessageChatUpgradeFrom.toData(): TdApi.MessageChatUpgradeFrom = TdApi.MessageChatUpgradeFrom(
    this.title,
    this.basicGroupId
)

fun MessageChatUpgradeTo.toData(): TdApi.MessageChatUpgradeTo = TdApi.MessageChatUpgradeTo(
    this.supergroupId
)

fun MessageChecklist.toData(): TdApi.MessageChecklist = TdApi.MessageChecklist(
    this.list.toData()
)

fun MessageChecklistTasksAdded.toData(): TdApi.MessageChecklistTasksAdded = TdApi.MessageChecklistTasksAdded(
    this.checklistMessageId,
    this.tasks.map { it.toData() }.toTypedArray()
)

fun MessageChecklistTasksDone.toData(): TdApi.MessageChecklistTasksDone = TdApi.MessageChecklistTasksDone(
    this.checklistMessageId,
    this.markedAsDoneTaskIds,
    this.markedAsNotDoneTaskIds
)

fun MessageContact.toData(): TdApi.MessageContact = TdApi.MessageContact(
    this.contact.toData()
)

fun MessageContactRegistered.toData(): TdApi.MessageContactRegistered = TdApi.MessageContactRegistered(
)

fun MessageContent.toData(): TdApi.MessageContent = when(this) {
    is MessageText -> this.toData()
    is MessageAnimation -> this.toData()
    is MessageAudio -> this.toData()
    is MessageDocument -> this.toData()
    is MessagePaidMedia -> this.toData()
    is MessagePhoto -> this.toData()
    is MessageSticker -> this.toData()
    is MessageVideo -> this.toData()
    is MessageVideoNote -> this.toData()
    is MessageVoiceNote -> this.toData()
    is MessageExpiredPhoto -> this.toData()
    is MessageExpiredVideo -> this.toData()
    is MessageExpiredVideoNote -> this.toData()
    is MessageExpiredVoiceNote -> this.toData()
    is MessageLocation -> this.toData()
    is MessageVenue -> this.toData()
    is MessageContact -> this.toData()
    is MessageAnimatedEmoji -> this.toData()
    is MessageDice -> this.toData()
    is MessageGame -> this.toData()
    is MessagePoll -> this.toData()
    is MessageStory -> this.toData()
    is MessageChecklist -> this.toData()
    is MessageInvoice -> this.toData()
    is MessageCall -> this.toData()
    is MessageGroupCall -> this.toData()
    is MessageVideoChatScheduled -> this.toData()
    is MessageVideoChatStarted -> this.toData()
    is MessageVideoChatEnded -> this.toData()
    is MessageInviteVideoChatParticipants -> this.toData()
    is MessageBasicGroupChatCreate -> this.toData()
    is MessageSupergroupChatCreate -> this.toData()
    is MessageChatChangeTitle -> this.toData()
    is MessageChatChangePhoto -> this.toData()
    is MessageChatDeletePhoto -> this.toData()
    is MessageChatAddMembers -> this.toData()
    is MessageChatJoinByLink -> this.toData()
    is MessageChatJoinByRequest -> this.toData()
    is MessageChatDeleteMember -> this.toData()
    is MessageChatUpgradeTo -> this.toData()
    is MessageChatUpgradeFrom -> this.toData()
    is MessagePinMessage -> this.toData()
    is MessageScreenshotTaken -> this.toData()
    is MessageChatSetBackground -> this.toData()
    is MessageChatSetTheme -> this.toData()
    is MessageChatSetMessageAutoDeleteTime -> this.toData()
    is MessageChatBoost -> this.toData()
    is MessageForumTopicCreated -> this.toData()
    is MessageForumTopicEdited -> this.toData()
    is MessageForumTopicIsClosedToggled -> this.toData()
    is MessageForumTopicIsHiddenToggled -> this.toData()
    is MessageSuggestProfilePhoto -> this.toData()
    is MessageCustomServiceAction -> this.toData()
    is MessageGameScore -> this.toData()
    is MessagePaymentSuccessful -> this.toData()
    is MessagePaymentSuccessfulBot -> this.toData()
    is MessagePaymentRefunded -> this.toData()
    is MessageGiftedPremium -> this.toData()
    is MessagePremiumGiftCode -> this.toData()
    is MessageGiveawayCreated -> this.toData()
    is MessageGiveaway -> this.toData()
    is MessageGiveawayCompleted -> this.toData()
    is MessageGiveawayWinners -> this.toData()
    is MessageGiftedStars -> this.toData()
    is MessageGiftedTon -> this.toData()
    is MessageGiveawayPrizeStars -> this.toData()
    is MessageGift -> this.toData()
    is MessageUpgradedGift -> this.toData()
    is MessageRefundedUpgradedGift -> this.toData()
    is MessagePaidMessagesRefunded -> this.toData()
    is MessagePaidMessagePriceChanged -> this.toData()
    is MessageDirectMessagePriceChanged -> this.toData()
    is MessageChecklistTasksDone -> this.toData()
    is MessageChecklistTasksAdded -> this.toData()
    is MessageSuggestedPostApprovalFailed -> this.toData()
    is MessageSuggestedPostApproved -> this.toData()
    is MessageSuggestedPostDeclined -> this.toData()
    is MessageSuggestedPostPaid -> this.toData()
    is MessageSuggestedPostRefunded -> this.toData()
    is MessageContactRegistered -> this.toData()
    is MessageUsersShared -> this.toData()
    is MessageChatShared -> this.toData()
    is MessageBotWriteAccessAllowed -> this.toData()
    is MessageWebAppDataSent -> this.toData()
    is MessageWebAppDataReceived -> this.toData()
    is MessagePassportDataSent -> this.toData()
    is MessagePassportDataReceived -> this.toData()
    is MessageProximityAlertTriggered -> this.toData()
    is MessageUnsupported -> this.toData()
}

fun MessageCopyOptions.toData(): TdApi.MessageCopyOptions = TdApi.MessageCopyOptions(
    this.sendCopy,
    this.replaceCaption,
    this.newCaption.toData(),
    this.newShowCaptionAboveMedia
)

fun MessageCustomServiceAction.toData(): TdApi.MessageCustomServiceAction = TdApi.MessageCustomServiceAction(
    this.text
)

fun MessageDice.toData(): TdApi.MessageDice = TdApi.MessageDice(
    this.initialState?.toData(),
    this.finalState?.toData(),
    this.emoji,
    this.value,
    this.successAnimationFrameNumber
)

fun MessageDirectMessagePriceChanged.toData(): TdApi.MessageDirectMessagePriceChanged = TdApi.MessageDirectMessagePriceChanged(
    this.isEnabled,
    this.paidMessageStarCount
)

fun MessageDocument.toData(): TdApi.MessageDocument = TdApi.MessageDocument(
    this.document.toData(),
    this.caption.toData()
)

fun MessageEffect.toData(): TdApi.MessageEffect = TdApi.MessageEffect(
    this.id,
    this.staticIcon?.toData(),
    this.emoji,
    this.isPremium,
    this.type.toData()
)

fun MessageEffectType.toData(): TdApi.MessageEffectType = when(this) {
    is MessageEffectTypeEmojiReaction -> this.toData()
    is MessageEffectTypePremiumSticker -> this.toData()
}

fun MessageEffectTypeEmojiReaction.toData(): TdApi.MessageEffectTypeEmojiReaction = TdApi.MessageEffectTypeEmojiReaction(
    this.selectAnimation.toData(),
    this.effectAnimation.toData()
)

fun MessageEffectTypePremiumSticker.toData(): TdApi.MessageEffectTypePremiumSticker = TdApi.MessageEffectTypePremiumSticker(
    this.sticker.toData()
)

fun MessageExpiredPhoto.toData(): TdApi.MessageExpiredPhoto = TdApi.MessageExpiredPhoto(
)

fun MessageExpiredVideo.toData(): TdApi.MessageExpiredVideo = TdApi.MessageExpiredVideo(
)

fun MessageExpiredVideoNote.toData(): TdApi.MessageExpiredVideoNote = TdApi.MessageExpiredVideoNote(
)

fun MessageExpiredVoiceNote.toData(): TdApi.MessageExpiredVoiceNote = TdApi.MessageExpiredVoiceNote(
)

fun MessageFileType.toData(): TdApi.MessageFileType = when(this) {
    is MessageFileTypePrivate -> this.toData()
    is MessageFileTypeGroup -> this.toData()
    is MessageFileTypeUnknown -> this.toData()
}

fun MessageFileTypeGroup.toData(): TdApi.MessageFileTypeGroup = TdApi.MessageFileTypeGroup(
    this.title
)

fun MessageFileTypePrivate.toData(): TdApi.MessageFileTypePrivate = TdApi.MessageFileTypePrivate(
    this.name
)

fun MessageFileTypeUnknown.toData(): TdApi.MessageFileTypeUnknown = TdApi.MessageFileTypeUnknown(
)

fun MessageForumTopicCreated.toData(): TdApi.MessageForumTopicCreated = TdApi.MessageForumTopicCreated(
    this.name,
    this.icon.toData()
)

fun MessageForumTopicEdited.toData(): TdApi.MessageForumTopicEdited = TdApi.MessageForumTopicEdited(
    this.name,
    this.editIconCustomEmojiId,
    this.iconCustomEmojiId
)

fun MessageForumTopicIsClosedToggled.toData(): TdApi.MessageForumTopicIsClosedToggled = TdApi.MessageForumTopicIsClosedToggled(
    this.isClosed
)

fun MessageForumTopicIsHiddenToggled.toData(): TdApi.MessageForumTopicIsHiddenToggled = TdApi.MessageForumTopicIsHiddenToggled(
    this.isHidden
)

fun MessageForwardInfo.toData(): TdApi.MessageForwardInfo = TdApi.MessageForwardInfo(
    this.origin.toData(),
    this.date,
    this.source?.toData(),
    this.publicServiceAnnouncementType
)

fun MessageGame.toData(): TdApi.MessageGame = TdApi.MessageGame(
    this.game.toData()
)

fun MessageGameScore.toData(): TdApi.MessageGameScore = TdApi.MessageGameScore(
    this.gameMessageId,
    this.gameId,
    this.score
)

fun MessageGift.toData(): TdApi.MessageGift = TdApi.MessageGift(
    this.gift.toData(),
    this.senderId?.toData(),
    this.receiverId.toData(),
    this.receivedGiftId,
    this.text.toData(),
    this.sellStarCount,
    this.prepaidUpgradeStarCount,
    this.isUpgradeSeparate,
    this.isPrivate,
    this.isSaved,
    this.isPrepaidUpgrade,
    this.canBeUpgraded,
    this.wasConverted,
    this.wasUpgraded,
    this.wasRefunded,
    this.upgradedReceivedGiftId,
    this.prepaidUpgradeHash
)

fun MessageGiftedPremium.toData(): TdApi.MessageGiftedPremium = TdApi.MessageGiftedPremium(
    this.gifterUserId,
    this.receiverUserId,
    this.text.toData(),
    this.currency,
    this.amount,
    this.cryptocurrency,
    this.cryptocurrencyAmount,
    this.monthCount,
    this.sticker?.toData()
)

fun MessageGiftedStars.toData(): TdApi.MessageGiftedStars = TdApi.MessageGiftedStars(
    this.gifterUserId,
    this.receiverUserId,
    this.currency,
    this.amount,
    this.cryptocurrency,
    this.cryptocurrencyAmount,
    this.starCount,
    this.transactionId,
    this.sticker?.toData()
)

fun MessageGiftedTon.toData(): TdApi.MessageGiftedTon = TdApi.MessageGiftedTon(
    this.gifterUserId,
    this.receiverUserId,
    this.tonAmount,
    this.transactionId,
    this.sticker?.toData()
)

fun MessageGiveaway.toData(): TdApi.MessageGiveaway = TdApi.MessageGiveaway(
    this.parameters.toData(),
    this.winnerCount,
    this.prize.toData(),
    this.sticker?.toData()
)

fun MessageGiveawayCompleted.toData(): TdApi.MessageGiveawayCompleted = TdApi.MessageGiveawayCompleted(
    this.giveawayMessageId,
    this.winnerCount,
    this.isStarGiveaway,
    this.unclaimedPrizeCount
)

fun MessageGiveawayCreated.toData(): TdApi.MessageGiveawayCreated = TdApi.MessageGiveawayCreated(
    this.starCount
)

fun MessageGiveawayPrizeStars.toData(): TdApi.MessageGiveawayPrizeStars = TdApi.MessageGiveawayPrizeStars(
    this.starCount,
    this.transactionId,
    this.boostedChatId,
    this.giveawayMessageId,
    this.isUnclaimed,
    this.sticker?.toData()
)

fun MessageGiveawayWinners.toData(): TdApi.MessageGiveawayWinners = TdApi.MessageGiveawayWinners(
    this.boostedChatId,
    this.giveawayMessageId,
    this.additionalChatCount,
    this.actualWinnersSelectionDate,
    this.onlyNewMembers,
    this.wasRefunded,
    this.prize.toData(),
    this.prizeDescription,
    this.winnerCount,
    this.winnerUserIds,
    this.unclaimedPrizeCount
)

fun MessageGroupCall.toData(): TdApi.MessageGroupCall = TdApi.MessageGroupCall(
    this.isActive,
    this.wasMissed,
    this.isVideo,
    this.duration,
    this.otherParticipantIds.map { it.toData() }.toTypedArray()
)

fun MessageImportInfo.toData(): TdApi.MessageImportInfo = TdApi.MessageImportInfo(
    this.senderName,
    this.date
)

fun MessageInteractionInfo.toData(): TdApi.MessageInteractionInfo = TdApi.MessageInteractionInfo(
    this.viewCount,
    this.forwardCount,
    this.replyInfo?.toData(),
    this.reactions?.toData()
)

fun MessageInviteVideoChatParticipants.toData(): TdApi.MessageInviteVideoChatParticipants = TdApi.MessageInviteVideoChatParticipants(
    this.groupCallId,
    this.userIds
)

fun MessageInvoice.toData(): TdApi.MessageInvoice = TdApi.MessageInvoice(
    this.productInfo.toData(),
    this.currency,
    this.totalAmount,
    this.startParameter,
    this.isTest,
    this.needShippingAddress,
    this.receiptMessageId,
    this.paidMedia?.toData(),
    this.paidMediaCaption?.toData()
)

fun MessageLink.toData(): TdApi.MessageLink = TdApi.MessageLink(
    this.link,
    this.isPublic
)

fun MessageLinkInfo.toData(): TdApi.MessageLinkInfo = TdApi.MessageLinkInfo(
    this.isPublic,
    this.chatId,
    this.messageThreadId,
    this.message?.toData(),
    this.mediaTimestamp,
    this.forAlbum
)

fun MessageLocation.toData(): TdApi.MessageLocation = TdApi.MessageLocation(
    this.location.toData(),
    this.livePeriod,
    this.expiresIn,
    this.heading,
    this.proximityAlertRadius
)

fun MessageOrigin.toData(): TdApi.MessageOrigin = when(this) {
    is MessageOriginUser -> this.toData()
    is MessageOriginHiddenUser -> this.toData()
    is MessageOriginChat -> this.toData()
    is MessageOriginChannel -> this.toData()
}

fun MessageOriginChannel.toData(): TdApi.MessageOriginChannel = TdApi.MessageOriginChannel(
    this.chatId,
    this.messageId,
    this.authorSignature
)

fun MessageOriginChat.toData(): TdApi.MessageOriginChat = TdApi.MessageOriginChat(
    this.senderChatId,
    this.authorSignature
)

fun MessageOriginHiddenUser.toData(): TdApi.MessageOriginHiddenUser = TdApi.MessageOriginHiddenUser(
    this.senderName
)

fun MessageOriginUser.toData(): TdApi.MessageOriginUser = TdApi.MessageOriginUser(
    this.senderUserId
)

fun MessagePaidMedia.toData(): TdApi.MessagePaidMedia = TdApi.MessagePaidMedia(
    this.starCount,
    this.media.map { it.toData() }.toTypedArray(),
    this.caption.toData(),
    this.showCaptionAboveMedia
)

fun MessagePaidMessagePriceChanged.toData(): TdApi.MessagePaidMessagePriceChanged = TdApi.MessagePaidMessagePriceChanged(
    this.paidMessageStarCount
)

fun MessagePaidMessagesRefunded.toData(): TdApi.MessagePaidMessagesRefunded = TdApi.MessagePaidMessagesRefunded(
    this.messageCount,
    this.starCount
)

fun MessagePassportDataReceived.toData(): TdApi.MessagePassportDataReceived = TdApi.MessagePassportDataReceived(
    this.elements.map { it.toData() }.toTypedArray(),
    this.credentials.toData()
)

fun MessagePassportDataSent.toData(): TdApi.MessagePassportDataSent = TdApi.MessagePassportDataSent(
    this.types.map { it.toData() }.toTypedArray()
)

fun MessagePaymentRefunded.toData(): TdApi.MessagePaymentRefunded = TdApi.MessagePaymentRefunded(
    this.ownerId.toData(),
    this.currency,
    this.totalAmount,
    this.invoicePayload,
    this.telegramPaymentChargeId,
    this.providerPaymentChargeId
)

fun MessagePaymentSuccessful.toData(): TdApi.MessagePaymentSuccessful = TdApi.MessagePaymentSuccessful(
    this.invoiceChatId,
    this.invoiceMessageId,
    this.currency,
    this.totalAmount,
    this.subscriptionUntilDate,
    this.isRecurring,
    this.isFirstRecurring,
    this.invoiceName
)

fun MessagePaymentSuccessfulBot.toData(): TdApi.MessagePaymentSuccessfulBot = TdApi.MessagePaymentSuccessfulBot(
    this.currency,
    this.totalAmount,
    this.subscriptionUntilDate,
    this.isRecurring,
    this.isFirstRecurring,
    this.invoicePayload,
    this.shippingOptionId,
    this.orderInfo?.toData(),
    this.telegramPaymentChargeId,
    this.providerPaymentChargeId
)

fun MessagePhoto.toData(): TdApi.MessagePhoto = TdApi.MessagePhoto(
    this.photo.toData(),
    this.caption.toData(),
    this.showCaptionAboveMedia,
    this.hasSpoiler,
    this.isSecret
)

fun MessagePinMessage.toData(): TdApi.MessagePinMessage = TdApi.MessagePinMessage(
    this.messageId
)

fun MessagePoll.toData(): TdApi.MessagePoll = TdApi.MessagePoll(
    this.poll.toData()
)

fun MessagePosition.toData(): TdApi.MessagePosition = TdApi.MessagePosition(
    this.position,
    this.messageId,
    this.date
)

fun MessagePositions.toData(): TdApi.MessagePositions = TdApi.MessagePositions(
    this.totalCount,
    this.positions.map { it.toData() }.toTypedArray()
)

fun MessagePremiumGiftCode.toData(): TdApi.MessagePremiumGiftCode = TdApi.MessagePremiumGiftCode(
    this.creatorId?.toData(),
    this.text.toData(),
    this.isFromGiveaway,
    this.isUnclaimed,
    this.currency,
    this.amount,
    this.cryptocurrency,
    this.cryptocurrencyAmount,
    this.monthCount,
    this.sticker?.toData(),
    this.code
)

fun MessageProperties.toData(): TdApi.MessageProperties = TdApi.MessageProperties(
    this.canAddOffer,
    this.canAddTasks,
    this.canBeApproved,
    this.canBeCopied,
    this.canBeCopiedToSecretChat,
    this.canBeDeclined,
    this.canBeDeletedOnlyForSelf,
    this.canBeDeletedForAllUsers,
    this.canBeEdited,
    this.canBeForwarded,
    this.canBePaid,
    this.canBePinned,
    this.canBeReplied,
    this.canBeRepliedInAnotherChat,
    this.canBeSaved,
    this.canBeSharedInStory,
    this.canEditMedia,
    this.canEditSchedulingState,
    this.canEditSuggestedPostInfo,
    this.canGetAuthor,
    this.canGetEmbeddingCode,
    this.canGetLink,
    this.canGetMediaTimestampLinks,
    this.canGetMessageThread,
    this.canGetReadDate,
    this.canGetStatistics,
    this.canGetVideoAdvertisements,
    this.canGetViewers,
    this.canMarkTasksAsDone,
    this.canRecognizeSpeech,
    this.canReportChat,
    this.canReportReactions,
    this.canReportSupergroupSpam,
    this.canSetFactCheck,
    this.needShowStatistics
)

fun MessageProximityAlertTriggered.toData(): TdApi.MessageProximityAlertTriggered = TdApi.MessageProximityAlertTriggered(
    this.travelerId.toData(),
    this.watcherId.toData(),
    this.distance
)

fun MessageReaction.toData(): TdApi.MessageReaction = TdApi.MessageReaction(
    this.type.toData(),
    this.totalCount,
    this.isChosen,
    this.usedSenderId?.toData(),
    this.recentSenderIds.map { it.toData() }.toTypedArray()
)

fun MessageReactions.toData(): TdApi.MessageReactions = TdApi.MessageReactions(
    this.reactions.map { it.toData() }.toTypedArray(),
    this.areTags,
    this.paidReactors.map { it.toData() }.toTypedArray(),
    this.canGetAddedReactions
)

fun MessageReadDate.toData(): TdApi.MessageReadDate = when(this) {
    is MessageReadDateRead -> this.toData()
    is MessageReadDateUnread -> this.toData()
    is MessageReadDateTooOld -> this.toData()
    is MessageReadDateUserPrivacyRestricted -> this.toData()
    is MessageReadDateMyPrivacyRestricted -> this.toData()
}

fun MessageReadDateMyPrivacyRestricted.toData(): TdApi.MessageReadDateMyPrivacyRestricted = TdApi.MessageReadDateMyPrivacyRestricted(
)

fun MessageReadDateRead.toData(): TdApi.MessageReadDateRead = TdApi.MessageReadDateRead(
    this.readDate
)

fun MessageReadDateTooOld.toData(): TdApi.MessageReadDateTooOld = TdApi.MessageReadDateTooOld(
)

fun MessageReadDateUnread.toData(): TdApi.MessageReadDateUnread = TdApi.MessageReadDateUnread(
)

fun MessageReadDateUserPrivacyRestricted.toData(): TdApi.MessageReadDateUserPrivacyRestricted = TdApi.MessageReadDateUserPrivacyRestricted(
)

fun MessageRefundedUpgradedGift.toData(): TdApi.MessageRefundedUpgradedGift = TdApi.MessageRefundedUpgradedGift(
    this.gift.toData(),
    this.senderId.toData(),
    this.receiverId.toData(),
    this.origin.toData()
)

fun MessageReplyInfo.toData(): TdApi.MessageReplyInfo = TdApi.MessageReplyInfo(
    this.replyCount,
    this.recentReplierIds.map { it.toData() }.toTypedArray(),
    this.lastReadInboxMessageId,
    this.lastReadOutboxMessageId,
    this.lastMessageId
)

fun MessageReplyTo.toData(): TdApi.MessageReplyTo = when(this) {
    is MessageReplyToMessage -> this.toData()
    is MessageReplyToStory -> this.toData()
}

fun MessageReplyToMessage.toData(): TdApi.MessageReplyToMessage = TdApi.MessageReplyToMessage(
    this.chatId,
    this.messageId,
    this.quote?.toData(),
    this.checklistTaskId,
    this.origin?.toData(),
    this.originSendDate,
    this.content?.toData()
)

fun MessageReplyToStory.toData(): TdApi.MessageReplyToStory = TdApi.MessageReplyToStory(
    this.storyPosterChatId,
    this.storyId
)

fun MessageSchedulingState.toData(): TdApi.MessageSchedulingState = when(this) {
    is MessageSchedulingStateSendAtDate -> this.toData()
    is MessageSchedulingStateSendWhenOnline -> this.toData()
    is MessageSchedulingStateSendWhenVideoProcessed -> this.toData()
}

fun MessageSchedulingStateSendAtDate.toData(): TdApi.MessageSchedulingStateSendAtDate = TdApi.MessageSchedulingStateSendAtDate(
    this.sendDate
)

fun MessageSchedulingStateSendWhenOnline.toData(): TdApi.MessageSchedulingStateSendWhenOnline = TdApi.MessageSchedulingStateSendWhenOnline(
)

fun MessageSchedulingStateSendWhenVideoProcessed.toData(): TdApi.MessageSchedulingStateSendWhenVideoProcessed = TdApi.MessageSchedulingStateSendWhenVideoProcessed(
    this.sendDate
)

fun MessageScreenshotTaken.toData(): TdApi.MessageScreenshotTaken = TdApi.MessageScreenshotTaken(
)

fun MessageSelfDestructType.toData(): TdApi.MessageSelfDestructType = when(this) {
    is MessageSelfDestructTypeTimer -> this.toData()
    is MessageSelfDestructTypeImmediately -> this.toData()
}

fun MessageSelfDestructTypeImmediately.toData(): TdApi.MessageSelfDestructTypeImmediately = TdApi.MessageSelfDestructTypeImmediately(
)

fun MessageSelfDestructTypeTimer.toData(): TdApi.MessageSelfDestructTypeTimer = TdApi.MessageSelfDestructTypeTimer(
    this.selfDestructTime
)

fun MessageSendOptions.toData(): TdApi.MessageSendOptions = TdApi.MessageSendOptions(
    this.directMessagesChatTopicId,
    this.suggestedPostInfo.toData(),
    this.disableNotification,
    this.fromBackground,
    this.protectContent,
    this.allowPaidBroadcast,
    this.paidMessageStarCount,
    this.updateOrderOfInstalledStickerSets,
    this.schedulingState.toData(),
    this.effectId,
    this.sendingId,
    this.onlyPreview
)

fun MessageSender.toData(): TdApi.MessageSender = when(this) {
    is MessageSenderUser -> this.toData()
    is MessageSenderChat -> this.toData()
}

fun MessageSenderChat.toData(): TdApi.MessageSenderChat = TdApi.MessageSenderChat(
    this.chatId
)

fun MessageSenderUser.toData(): TdApi.MessageSenderUser = TdApi.MessageSenderUser(
    this.userId
)

fun MessageSenders.toData(): TdApi.MessageSenders = TdApi.MessageSenders(
    this.totalCount,
    this.senders.map { it.toData() }.toTypedArray()
)

fun MessageSendingState.toData(): TdApi.MessageSendingState = when(this) {
    is MessageSendingStatePending -> this.toData()
    is MessageSendingStateFailed -> this.toData()
}

fun MessageSendingStateFailed.toData(): TdApi.MessageSendingStateFailed = TdApi.MessageSendingStateFailed(
    this.error.toData(),
    this.canRetry,
    this.needAnotherSender,
    this.needAnotherReplyQuote,
    this.needDropReply,
    this.requiredPaidMessageStarCount,
    this.retryAfter
)

fun MessageSendingStatePending.toData(): TdApi.MessageSendingStatePending = TdApi.MessageSendingStatePending(
    this.sendingId
)

fun MessageSource.toData(): TdApi.MessageSource = when(this) {
    is MessageSourceChatHistory -> this.toData()
    is MessageSourceMessageThreadHistory -> this.toData()
    is MessageSourceForumTopicHistory -> this.toData()
    is MessageSourceDirectMessagesChatTopicHistory -> this.toData()
    is MessageSourceHistoryPreview -> this.toData()
    is MessageSourceChatList -> this.toData()
    is MessageSourceSearch -> this.toData()
    is MessageSourceChatEventLog -> this.toData()
    is MessageSourceNotification -> this.toData()
    is MessageSourceScreenshot -> this.toData()
    is MessageSourceOther -> this.toData()
}

fun MessageSourceChatEventLog.toData(): TdApi.MessageSourceChatEventLog = TdApi.MessageSourceChatEventLog(
)

fun MessageSourceChatHistory.toData(): TdApi.MessageSourceChatHistory = TdApi.MessageSourceChatHistory(
)

fun MessageSourceChatList.toData(): TdApi.MessageSourceChatList = TdApi.MessageSourceChatList(
)

fun MessageSourceDirectMessagesChatTopicHistory.toData(): TdApi.MessageSourceDirectMessagesChatTopicHistory = TdApi.MessageSourceDirectMessagesChatTopicHistory(
)

fun MessageSourceForumTopicHistory.toData(): TdApi.MessageSourceForumTopicHistory = TdApi.MessageSourceForumTopicHistory(
)

fun MessageSourceHistoryPreview.toData(): TdApi.MessageSourceHistoryPreview = TdApi.MessageSourceHistoryPreview(
)

fun MessageSourceMessageThreadHistory.toData(): TdApi.MessageSourceMessageThreadHistory = TdApi.MessageSourceMessageThreadHistory(
)

fun MessageSourceNotification.toData(): TdApi.MessageSourceNotification = TdApi.MessageSourceNotification(
)

fun MessageSourceOther.toData(): TdApi.MessageSourceOther = TdApi.MessageSourceOther(
)

fun MessageSourceScreenshot.toData(): TdApi.MessageSourceScreenshot = TdApi.MessageSourceScreenshot(
)

fun MessageSourceSearch.toData(): TdApi.MessageSourceSearch = TdApi.MessageSourceSearch(
)

fun MessageStatistics.toData(): TdApi.MessageStatistics = TdApi.MessageStatistics(
    this.messageInteractionGraph.toData(),
    this.messageReactionGraph.toData()
)

fun MessageSticker.toData(): TdApi.MessageSticker = TdApi.MessageSticker(
    this.sticker.toData(),
    this.isPremium
)

fun MessageStory.toData(): TdApi.MessageStory = TdApi.MessageStory(
    this.storyPosterChatId,
    this.storyId,
    this.viaMention
)

fun MessageSuggestProfilePhoto.toData(): TdApi.MessageSuggestProfilePhoto = TdApi.MessageSuggestProfilePhoto(
    this.photo.toData()
)

fun MessageSuggestedPostApprovalFailed.toData(): TdApi.MessageSuggestedPostApprovalFailed = TdApi.MessageSuggestedPostApprovalFailed(
    this.suggestedPostMessageId,
    this.price.toData()
)

fun MessageSuggestedPostApproved.toData(): TdApi.MessageSuggestedPostApproved = TdApi.MessageSuggestedPostApproved(
    this.suggestedPostMessageId,
    this.price?.toData(),
    this.sendDate
)

fun MessageSuggestedPostDeclined.toData(): TdApi.MessageSuggestedPostDeclined = TdApi.MessageSuggestedPostDeclined(
    this.suggestedPostMessageId,
    this.comment
)

fun MessageSuggestedPostPaid.toData(): TdApi.MessageSuggestedPostPaid = TdApi.MessageSuggestedPostPaid(
    this.suggestedPostMessageId,
    this.starAmount.toData(),
    this.tonAmount
)

fun MessageSuggestedPostRefunded.toData(): TdApi.MessageSuggestedPostRefunded = TdApi.MessageSuggestedPostRefunded(
    this.suggestedPostMessageId,
    this.reason.toData()
)

fun MessageSupergroupChatCreate.toData(): TdApi.MessageSupergroupChatCreate = TdApi.MessageSupergroupChatCreate(
    this.title
)

fun MessageText.toData(): TdApi.MessageText = TdApi.MessageText(
    this.text.toData(),
    this.linkPreview?.toData(),
    this.linkPreviewOptions?.toData()
)

fun MessageThreadInfo.toData(): TdApi.MessageThreadInfo = TdApi.MessageThreadInfo(
    this.chatId,
    this.messageThreadId,
    this.replyInfo?.toData(),
    this.unreadMessageCount,
    this.messages.map { it.toData() }.toTypedArray(),
    this.draftMessage?.toData()
)

fun MessageTopic.toData(): TdApi.MessageTopic = when(this) {
    is MessageTopicForum -> this.toData()
    is MessageTopicDirectMessages -> this.toData()
    is MessageTopicSavedMessages -> this.toData()
}

fun MessageTopicDirectMessages.toData(): TdApi.MessageTopicDirectMessages = TdApi.MessageTopicDirectMessages(
    this.directMessagesChatTopicId
)

fun MessageTopicForum.toData(): TdApi.MessageTopicForum = TdApi.MessageTopicForum(
    this.forumTopicId
)

fun MessageTopicSavedMessages.toData(): TdApi.MessageTopicSavedMessages = TdApi.MessageTopicSavedMessages(
    this.savedMessagesTopicId
)

fun MessageUnsupported.toData(): TdApi.MessageUnsupported = TdApi.MessageUnsupported(
)

fun MessageUpgradedGift.toData(): TdApi.MessageUpgradedGift = TdApi.MessageUpgradedGift(
    this.gift.toData(),
    this.senderId?.toData(),
    this.receiverId.toData(),
    this.origin.toData(),
    this.receivedGiftId,
    this.isSaved,
    this.canBeTransferred,
    this.wasTransferred,
    this.transferStarCount,
    this.nextTransferDate,
    this.nextResaleDate,
    this.exportDate
)

fun MessageUsersShared.toData(): TdApi.MessageUsersShared = TdApi.MessageUsersShared(
    this.users.map { it.toData() }.toTypedArray(),
    this.buttonId
)

fun MessageVenue.toData(): TdApi.MessageVenue = TdApi.MessageVenue(
    this.venue.toData()
)

fun MessageVideo.toData(): TdApi.MessageVideo = TdApi.MessageVideo(
    this.video.toData(),
    this.alternativeVideos.map { it.toData() }.toTypedArray(),
    this.storyboards.map { it.toData() }.toTypedArray(),
    this.cover?.toData(),
    this.startTimestamp,
    this.caption.toData(),
    this.showCaptionAboveMedia,
    this.hasSpoiler,
    this.isSecret
)

fun MessageVideoChatEnded.toData(): TdApi.MessageVideoChatEnded = TdApi.MessageVideoChatEnded(
    this.duration
)

fun MessageVideoChatScheduled.toData(): TdApi.MessageVideoChatScheduled = TdApi.MessageVideoChatScheduled(
    this.groupCallId,
    this.startDate
)

fun MessageVideoChatStarted.toData(): TdApi.MessageVideoChatStarted = TdApi.MessageVideoChatStarted(
    this.groupCallId
)

fun MessageVideoNote.toData(): TdApi.MessageVideoNote = TdApi.MessageVideoNote(
    this.videoNote.toData(),
    this.isViewed,
    this.isSecret
)

fun MessageViewer.toData(): TdApi.MessageViewer = TdApi.MessageViewer(
    this.userId,
    this.viewDate
)

fun MessageViewers.toData(): TdApi.MessageViewers = TdApi.MessageViewers(
    this.viewers.map { it.toData() }.toTypedArray()
)

fun MessageVoiceNote.toData(): TdApi.MessageVoiceNote = TdApi.MessageVoiceNote(
    this.voiceNote.toData(),
    this.caption.toData(),
    this.isListened
)

fun MessageWebAppDataReceived.toData(): TdApi.MessageWebAppDataReceived = TdApi.MessageWebAppDataReceived(
    this.buttonText,
    this.data
)

fun MessageWebAppDataSent.toData(): TdApi.MessageWebAppDataSent = TdApi.MessageWebAppDataSent(
    this.buttonText
)

fun Messages.toData(): TdApi.Messages = TdApi.Messages(
    this.totalCount,
    this.messages.map { it.toData() }.toTypedArray()
)

fun ResendMessages.toData(): TdApi.ResendMessages = TdApi.ResendMessages(
    this.chatId,
    this.messageIds,
    this.quote.toData(),
    this.paidMessageStarCount
)

fun SendBotStartMessage.toData(): TdApi.SendBotStartMessage = TdApi.SendBotStartMessage(
    this.botUserId,
    this.chatId,
    this.parameter
)

fun SendInlineQueryResultMessage.toData(): TdApi.SendInlineQueryResultMessage = TdApi.SendInlineQueryResultMessage(
    this.chatId,
    this.messageThreadId,
    this.replyTo.toData(),
    this.options.toData(),
    this.queryId,
    this.resultId,
    this.hideViaBot
)

fun SendMessage.toData(): TdApi.SendMessage = TdApi.SendMessage(
    this.chatId,
    this.messageThreadId,
    this.replyTo.toData(),
    this.options.toData(),
    this.replyMarkup.toData(),
    this.inputMessageContent.toData()
)

fun SendMessageAlbum.toData(): TdApi.SendMessageAlbum = TdApi.SendMessageAlbum(
    this.chatId,
    this.messageThreadId,
    this.replyTo.toData(),
    this.options.toData(),
    this.inputMessageContents.map { it.toData() }.toTypedArray()
)

fun SetChatDraftMessage.toData(): TdApi.SetChatDraftMessage = TdApi.SetChatDraftMessage(
    this.chatId,
    this.messageThreadId,
    this.draftMessage.toData()
)

