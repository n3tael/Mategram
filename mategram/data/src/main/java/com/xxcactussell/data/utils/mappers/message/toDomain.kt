package com.xxcactussell.data.utils.mappers.message

import com.xxcactussell.data.utils.mappers.alternative.toDomain
import com.xxcactussell.data.utils.mappers.animation.toDomain
import com.xxcactussell.data.utils.mappers.bot.toDomain
import com.xxcactussell.data.utils.mappers.bots.toDomain
import com.xxcactussell.data.utils.mappers.calls.toDomain
import com.xxcactussell.data.utils.mappers.chat.toDomain
import com.xxcactussell.data.utils.mappers.checklist.toDomain
import com.xxcactussell.data.utils.mappers.dice.toDomain
import com.xxcactussell.data.utils.mappers.encrypted.toDomain
import com.xxcactussell.data.utils.mappers.error.toDomain
import com.xxcactussell.data.utils.mappers.fact.toDomain
import com.xxcactussell.data.utils.mappers.file.toDomain
import com.xxcactussell.data.utils.mappers.formatted.toDomain
import com.xxcactussell.data.utils.mappers.forum.toDomain
import com.xxcactussell.data.utils.mappers.forward.toDomain
import com.xxcactussell.data.utils.mappers.game.toDomain
import com.xxcactussell.data.utils.mappers.giveaway.toDomain
import com.xxcactussell.data.utils.mappers.input.toDomain
import com.xxcactussell.data.utils.mappers.link.toDomain
import com.xxcactussell.data.utils.mappers.location.toDomain
import com.xxcactussell.data.utils.mappers.monetization.toDomain
import com.xxcactussell.data.utils.mappers.paid.toDomain
import com.xxcactussell.data.utils.mappers.passport.toDomain
import com.xxcactussell.data.utils.mappers.photo.toDomain
import com.xxcactussell.data.utils.mappers.poll.toDomain
import com.xxcactussell.data.utils.mappers.product.toDomain
import com.xxcactussell.data.utils.mappers.reaction.toDomain
import com.xxcactussell.data.utils.mappers.restriction.toDomain
import com.xxcactussell.data.utils.mappers.search.toDomain
import com.xxcactussell.data.utils.mappers.shared.toDomain
import com.xxcactussell.data.utils.mappers.star.toDomain
import com.xxcactussell.data.utils.mappers.statistical.toDomain
import com.xxcactussell.data.utils.mappers.sticker.toDomain
import com.xxcactussell.data.utils.mappers.suggested.toDomain
import com.xxcactussell.data.utils.mappers.text.toDomain
import com.xxcactussell.data.utils.mappers.unread.toDomain
import com.xxcactussell.data.utils.mappers.user.toDomain
import com.xxcactussell.data.utils.mappers.venue.toDomain
import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: TdApi -> Domain

fun TdApi.DeleteChatMessagesByDate.toDomain(): DeleteChatMessagesByDate = DeleteChatMessagesByDate(
    chatId = this.chatId,
    minDate = this.minDate,
    maxDate = this.maxDate,
    revoke = this.revoke
)

fun TdApi.DeleteMessages.toDomain(): DeleteMessages = DeleteMessages(
    chatId = this.chatId,
    messageIds = this.messageIds,
    revoke = this.revoke
)

fun TdApi.DraftMessage.toDomain(): DraftMessage = DraftMessage(
    replyTo = this.replyTo?.toDomain(),
    date = this.date,
    inputMessageText = this.inputMessageText.toDomain(),
    effectId = this.effectId,
    suggestedPostInfo = this.suggestedPostInfo?.toDomain()
)

fun TdApi.EditMessageCaption.toDomain(): EditMessageCaption = EditMessageCaption(
    chatId = this.chatId,
    messageId = this.messageId,
    replyMarkup = this.replyMarkup.toDomain(),
    caption = this.caption.toDomain(),
    showCaptionAboveMedia = this.showCaptionAboveMedia
)

fun TdApi.EditMessageLiveLocation.toDomain(): EditMessageLiveLocation = EditMessageLiveLocation(
    chatId = this.chatId,
    messageId = this.messageId,
    replyMarkup = this.replyMarkup.toDomain(),
    location = this.location.toDomain(),
    livePeriod = this.livePeriod,
    heading = this.heading,
    proximityAlertRadius = this.proximityAlertRadius
)

fun TdApi.EditMessageMedia.toDomain(): EditMessageMedia = EditMessageMedia(
    chatId = this.chatId,
    messageId = this.messageId,
    replyMarkup = this.replyMarkup.toDomain(),
    inputMessageContent = this.inputMessageContent.toDomain()
)

fun TdApi.EditMessageReplyMarkup.toDomain(): EditMessageReplyMarkup = EditMessageReplyMarkup(
    chatId = this.chatId,
    messageId = this.messageId,
    replyMarkup = this.replyMarkup.toDomain()
)

fun TdApi.EditMessageText.toDomain(): EditMessageText = EditMessageText(
    chatId = this.chatId,
    messageId = this.messageId,
    replyMarkup = this.replyMarkup.toDomain(),
    inputMessageContent = this.inputMessageContent.toDomain()
)

fun TdApi.ForwardMessages.toDomain(): ForwardMessages = ForwardMessages(
    chatId = this.chatId,
    messageThreadId = this.messageThreadId,
    fromChatId = this.fromChatId,
    messageIds = this.messageIds,
    options = this.options.toDomain(),
    sendCopy = this.sendCopy,
    removeCaption = this.removeCaption
)

fun TdApi.GetChatMessageByDate.toDomain(): GetChatMessageByDate = GetChatMessageByDate(
    chatId = this.chatId,
    date = this.date
)

fun TdApi.GetChatMessagePosition.toDomain(): GetChatMessagePosition = GetChatMessagePosition(
    chatId = this.chatId,
    topicId = this.topicId.toDomain(),
    filter = this.filter.toDomain(),
    messageId = this.messageId
)

fun TdApi.GetMessageThread.toDomain(): GetMessageThread = GetMessageThread(
    chatId = this.chatId,
    messageId = this.messageId
)

fun TdApi.GetMessageThreadHistory.toDomain(): GetMessageThreadHistory = GetMessageThreadHistory(
    chatId = this.chatId,
    messageId = this.messageId,
    fromMessageId = this.fromMessageId,
    offset = this.offset,
    limit = this.limit
)

fun TdApi.GetMessages.toDomain(): GetMessages = GetMessages(
    chatId = this.chatId,
    messageIds = this.messageIds
)

fun TdApi.Message.toDomain(): Message = Message(
    id = this.id,
    senderId = this.senderId.toDomain(),
    chatId = this.chatId,
    sendingState = this.sendingState?.toDomain(),
    schedulingState = this.schedulingState?.toDomain(),
    isOutgoing = this.isOutgoing,
    isPinned = this.isPinned,
    isFromOffline = this.isFromOffline,
    canBeSaved = this.canBeSaved,
    hasTimestampedMedia = this.hasTimestampedMedia,
    isChannelPost = this.isChannelPost,
    isPaidStarSuggestedPost = this.isPaidStarSuggestedPost,
    isPaidTonSuggestedPost = this.isPaidTonSuggestedPost,
    containsUnreadMention = this.containsUnreadMention,
    date = this.date,
    editDate = this.editDate,
    forwardInfo = this.forwardInfo?.toDomain(),
    importInfo = this.importInfo?.toDomain(),
    interactionInfo = this.interactionInfo?.toDomain(),
    unreadReactions = this.unreadReactions.map { it.toDomain() },
    factCheck = this.factCheck?.toDomain(),
    suggestedPostInfo = this.suggestedPostInfo?.toDomain(),
    replyTo = this.replyTo?.toDomain(),
    messageThreadId = this.messageThreadId,
    topicId = this.topicId?.toDomain(),
    selfDestructType = this.selfDestructType?.toDomain(),
    selfDestructIn = this.selfDestructIn,
    autoDeleteIn = this.autoDeleteIn,
    viaBotUserId = this.viaBotUserId,
    senderBusinessBotUserId = this.senderBusinessBotUserId,
    senderBoostCount = this.senderBoostCount,
    paidMessageStarCount = this.paidMessageStarCount,
    authorSignature = this.authorSignature,
    mediaAlbumId = this.mediaAlbumId,
    effectId = this.effectId,
    restrictionInfo = this.restrictionInfo?.toDomain(),
    content = this.content.toDomain(),
    replyMarkup = this.replyMarkup?.toDomain()
)

fun TdApi.MessageAnimatedEmoji.toDomain(): MessageAnimatedEmoji = MessageAnimatedEmoji(
    animatedEmoji = this.animatedEmoji.toDomain(),
    emoji = this.emoji
)

fun TdApi.MessageAnimation.toDomain(): MessageAnimation = MessageAnimation(
    animation = this.animation.toDomain(),
    caption = this.caption.toDomain(),
    showCaptionAboveMedia = this.showCaptionAboveMedia,
    hasSpoiler = this.hasSpoiler,
    isSecret = this.isSecret
)

fun TdApi.MessageAudio.toDomain(): MessageAudio = MessageAudio(
    audio = this.audio.toDomain(),
    caption = this.caption.toDomain()
)

fun TdApi.MessageAutoDeleteTime.toDomain(): MessageAutoDeleteTime = MessageAutoDeleteTime(
    time = this.time
)

fun TdApi.MessageBasicGroupChatCreate.toDomain(): MessageBasicGroupChatCreate = MessageBasicGroupChatCreate(
    title = this.title,
    memberUserIds = this.memberUserIds
)

fun TdApi.MessageBotWriteAccessAllowed.toDomain(): MessageBotWriteAccessAllowed = MessageBotWriteAccessAllowed(
    reason = this.reason.toDomain()
)

fun TdApi.MessageCalendar.toDomain(): MessageCalendar = MessageCalendar(
    totalCount = this.totalCount,
    days = this.days.map { it.toDomain() }
)

fun TdApi.MessageCalendarDay.toDomain(): MessageCalendarDay = MessageCalendarDay(
    totalCount = this.totalCount,
    message = this.message.toDomain()
)

fun TdApi.MessageCall.toDomain(): MessageCall = MessageCall(
    isVideo = this.isVideo,
    discardReason = this.discardReason.toDomain(),
    duration = this.duration
)

fun TdApi.MessageChatAddMembers.toDomain(): MessageChatAddMembers = MessageChatAddMembers(
    memberUserIds = this.memberUserIds
)

fun TdApi.MessageChatBoost.toDomain(): MessageChatBoost = MessageChatBoost(
    boostCount = this.boostCount
)

fun TdApi.MessageChatChangePhoto.toDomain(): MessageChatChangePhoto = MessageChatChangePhoto(
    photo = this.photo.toDomain()
)

fun TdApi.MessageChatChangeTitle.toDomain(): MessageChatChangeTitle = MessageChatChangeTitle(
    title = this.title
)

fun TdApi.MessageChatDeleteMember.toDomain(): MessageChatDeleteMember = MessageChatDeleteMember(
    userId = this.userId
)

fun TdApi.MessageChatDeletePhoto.toDomain(): MessageChatDeletePhoto = MessageChatDeletePhoto

fun TdApi.MessageChatJoinByLink.toDomain(): MessageChatJoinByLink = MessageChatJoinByLink

fun TdApi.MessageChatJoinByRequest.toDomain(): MessageChatJoinByRequest = MessageChatJoinByRequest

fun TdApi.MessageChatSetBackground.toDomain(): MessageChatSetBackground = MessageChatSetBackground(
    oldBackgroundMessageId = this.oldBackgroundMessageId,
    background = this.background.toDomain(),
    onlyForSelf = this.onlyForSelf
)

fun TdApi.MessageChatSetMessageAutoDeleteTime.toDomain(): MessageChatSetMessageAutoDeleteTime = MessageChatSetMessageAutoDeleteTime(
    messageAutoDeleteTime = this.messageAutoDeleteTime,
    fromUserId = this.fromUserId
)

fun TdApi.MessageChatSetTheme.toDomain(): MessageChatSetTheme = MessageChatSetTheme(
    theme = this.theme?.toDomain()
)

fun TdApi.MessageChatShared.toDomain(): MessageChatShared = MessageChatShared(
    chat = this.chat.toDomain(),
    buttonId = this.buttonId
)

fun TdApi.MessageChatUpgradeFrom.toDomain(): MessageChatUpgradeFrom = MessageChatUpgradeFrom(
    title = this.title,
    basicGroupId = this.basicGroupId
)

fun TdApi.MessageChatUpgradeTo.toDomain(): MessageChatUpgradeTo = MessageChatUpgradeTo(
    supergroupId = this.supergroupId
)

fun TdApi.MessageChecklist.toDomain(): MessageChecklist = MessageChecklist(
    list = this.list.toDomain()
)

fun TdApi.MessageChecklistTasksAdded.toDomain(): MessageChecklistTasksAdded = MessageChecklistTasksAdded(
    checklistMessageId = this.checklistMessageId,
    tasks = this.tasks.map { it.toDomain() }
)

fun TdApi.MessageChecklistTasksDone.toDomain(): MessageChecklistTasksDone = MessageChecklistTasksDone(
    checklistMessageId = this.checklistMessageId,
    markedAsDoneTaskIds = this.markedAsDoneTaskIds,
    markedAsNotDoneTaskIds = this.markedAsNotDoneTaskIds
)

fun TdApi.MessageContact.toDomain(): MessageContact = MessageContact(
    contact = this.contact.toDomain()
)

fun TdApi.MessageContactRegistered.toDomain(): MessageContactRegistered = MessageContactRegistered

fun TdApi.MessageContent.toDomain(): MessageContent = when(this) {
    is TdApi.MessageText -> this.toDomain()
    is TdApi.MessageAnimation -> this.toDomain()
    is TdApi.MessageAudio -> this.toDomain()
    is TdApi.MessageDocument -> this.toDomain()
    is TdApi.MessagePaidMedia -> this.toDomain()
    is TdApi.MessagePhoto -> this.toDomain()
    is TdApi.MessageSticker -> this.toDomain()
    is TdApi.MessageVideo -> this.toDomain()
    is TdApi.MessageVideoNote -> this.toDomain()
    is TdApi.MessageVoiceNote -> this.toDomain()
    is TdApi.MessageExpiredPhoto -> this.toDomain()
    is TdApi.MessageExpiredVideo -> this.toDomain()
    is TdApi.MessageExpiredVideoNote -> this.toDomain()
    is TdApi.MessageExpiredVoiceNote -> this.toDomain()
    is TdApi.MessageLocation -> this.toDomain()
    is TdApi.MessageVenue -> this.toDomain()
    is TdApi.MessageContact -> this.toDomain()
    is TdApi.MessageAnimatedEmoji -> this.toDomain()
    is TdApi.MessageDice -> this.toDomain()
    is TdApi.MessageGame -> this.toDomain()
    is TdApi.MessagePoll -> this.toDomain()
    is TdApi.MessageStory -> this.toDomain()
    is TdApi.MessageChecklist -> this.toDomain()
    is TdApi.MessageInvoice -> this.toDomain()
    is TdApi.MessageCall -> this.toDomain()
    is TdApi.MessageGroupCall -> this.toDomain()
    is TdApi.MessageVideoChatScheduled -> this.toDomain()
    is TdApi.MessageVideoChatStarted -> this.toDomain()
    is TdApi.MessageVideoChatEnded -> this.toDomain()
    is TdApi.MessageInviteVideoChatParticipants -> this.toDomain()
    is TdApi.MessageBasicGroupChatCreate -> this.toDomain()
    is TdApi.MessageSupergroupChatCreate -> this.toDomain()
    is TdApi.MessageChatChangeTitle -> this.toDomain()
    is TdApi.MessageChatChangePhoto -> this.toDomain()
    is TdApi.MessageChatDeletePhoto -> this.toDomain()
    is TdApi.MessageChatAddMembers -> this.toDomain()
    is TdApi.MessageChatJoinByLink -> this.toDomain()
    is TdApi.MessageChatJoinByRequest -> this.toDomain()
    is TdApi.MessageChatDeleteMember -> this.toDomain()
    is TdApi.MessageChatUpgradeTo -> this.toDomain()
    is TdApi.MessageChatUpgradeFrom -> this.toDomain()
    is TdApi.MessagePinMessage -> this.toDomain()
    is TdApi.MessageScreenshotTaken -> this.toDomain()
    is TdApi.MessageChatSetBackground -> this.toDomain()
    is TdApi.MessageChatSetTheme -> this.toDomain()
    is TdApi.MessageChatSetMessageAutoDeleteTime -> this.toDomain()
    is TdApi.MessageChatBoost -> this.toDomain()
    is TdApi.MessageForumTopicCreated -> this.toDomain()
    is TdApi.MessageForumTopicEdited -> this.toDomain()
    is TdApi.MessageForumTopicIsClosedToggled -> this.toDomain()
    is TdApi.MessageForumTopicIsHiddenToggled -> this.toDomain()
    is TdApi.MessageSuggestProfilePhoto -> this.toDomain()
    is TdApi.MessageCustomServiceAction -> this.toDomain()
    is TdApi.MessageGameScore -> this.toDomain()
    is TdApi.MessagePaymentSuccessful -> this.toDomain()
    is TdApi.MessagePaymentSuccessfulBot -> this.toDomain()
    is TdApi.MessagePaymentRefunded -> this.toDomain()
    is TdApi.MessageGiftedPremium -> this.toDomain()
    is TdApi.MessagePremiumGiftCode -> this.toDomain()
    is TdApi.MessageGiveawayCreated -> this.toDomain()
    is TdApi.MessageGiveaway -> this.toDomain()
    is TdApi.MessageGiveawayCompleted -> this.toDomain()
    is TdApi.MessageGiveawayWinners -> this.toDomain()
    is TdApi.MessageGiftedStars -> this.toDomain()
    is TdApi.MessageGiftedTon -> this.toDomain()
    is TdApi.MessageGiveawayPrizeStars -> this.toDomain()
    is TdApi.MessageGift -> this.toDomain()
    is TdApi.MessageUpgradedGift -> this.toDomain()
    is TdApi.MessageRefundedUpgradedGift -> this.toDomain()
    is TdApi.MessagePaidMessagesRefunded -> this.toDomain()
    is TdApi.MessagePaidMessagePriceChanged -> this.toDomain()
    is TdApi.MessageDirectMessagePriceChanged -> this.toDomain()
    is TdApi.MessageChecklistTasksDone -> this.toDomain()
    is TdApi.MessageChecklistTasksAdded -> this.toDomain()
    is TdApi.MessageSuggestedPostApprovalFailed -> this.toDomain()
    is TdApi.MessageSuggestedPostApproved -> this.toDomain()
    is TdApi.MessageSuggestedPostDeclined -> this.toDomain()
    is TdApi.MessageSuggestedPostPaid -> this.toDomain()
    is TdApi.MessageSuggestedPostRefunded -> this.toDomain()
    is TdApi.MessageContactRegistered -> this.toDomain()
    is TdApi.MessageUsersShared -> this.toDomain()
    is TdApi.MessageChatShared -> this.toDomain()
    is TdApi.MessageBotWriteAccessAllowed -> this.toDomain()
    is TdApi.MessageWebAppDataSent -> this.toDomain()
    is TdApi.MessageWebAppDataReceived -> this.toDomain()
    is TdApi.MessagePassportDataSent -> this.toDomain()
    is TdApi.MessagePassportDataReceived -> this.toDomain()
    is TdApi.MessageProximityAlertTriggered -> this.toDomain()
    is TdApi.MessageUnsupported -> this.toDomain()
    else -> throw IllegalArgumentException("Unknown type: ${this.javaClass}")
}

fun TdApi.MessageCopyOptions.toDomain(): MessageCopyOptions = MessageCopyOptions(
    sendCopy = this.sendCopy,
    replaceCaption = this.replaceCaption,
    newCaption = this.newCaption.toDomain(),
    newShowCaptionAboveMedia = this.newShowCaptionAboveMedia
)

fun TdApi.MessageCustomServiceAction.toDomain(): MessageCustomServiceAction = MessageCustomServiceAction(
    text = this.text
)

fun TdApi.MessageDice.toDomain(): MessageDice = MessageDice(
    initialState = this.initialState?.toDomain(),
    finalState = this.finalState?.toDomain(),
    emoji = this.emoji,
    value = this.value,
    successAnimationFrameNumber = this.successAnimationFrameNumber
)

fun TdApi.MessageDirectMessagePriceChanged.toDomain(): MessageDirectMessagePriceChanged = MessageDirectMessagePriceChanged(
    isEnabled = this.isEnabled,
    paidMessageStarCount = this.paidMessageStarCount
)

fun TdApi.MessageDocument.toDomain(): MessageDocument = MessageDocument(
    document = this.document.toDomain(),
    caption = this.caption.toDomain()
)

fun TdApi.MessageEffect.toDomain(): MessageEffect = MessageEffect(
    id = this.id,
    staticIcon = this.staticIcon?.toDomain(),
    emoji = this.emoji,
    isPremium = this.isPremium,
    type = this.type.toDomain()
)

fun TdApi.MessageEffectType.toDomain(): MessageEffectType = when(this) {
    is TdApi.MessageEffectTypeEmojiReaction -> this.toDomain()
    is TdApi.MessageEffectTypePremiumSticker -> this.toDomain()
    else -> throw IllegalArgumentException("Unknown type: ${this.javaClass}")
}

fun TdApi.MessageEffectTypeEmojiReaction.toDomain(): MessageEffectTypeEmojiReaction = MessageEffectTypeEmojiReaction(
    selectAnimation = this.selectAnimation.toDomain(),
    effectAnimation = this.effectAnimation.toDomain()
)

fun TdApi.MessageEffectTypePremiumSticker.toDomain(): MessageEffectTypePremiumSticker = MessageEffectTypePremiumSticker(
    sticker = this.sticker.toDomain()
)

fun TdApi.MessageExpiredPhoto.toDomain(): MessageExpiredPhoto = MessageExpiredPhoto

fun TdApi.MessageExpiredVideo.toDomain(): MessageExpiredVideo = MessageExpiredVideo

fun TdApi.MessageExpiredVideoNote.toDomain(): MessageExpiredVideoNote = MessageExpiredVideoNote

fun TdApi.MessageExpiredVoiceNote.toDomain(): MessageExpiredVoiceNote = MessageExpiredVoiceNote

fun TdApi.MessageFileType.toDomain(): MessageFileType = when(this) {
    is TdApi.MessageFileTypePrivate -> this.toDomain()
    is TdApi.MessageFileTypeGroup -> this.toDomain()
    is TdApi.MessageFileTypeUnknown -> this.toDomain()
    else -> throw IllegalArgumentException("Unknown type: ${this.javaClass}")
}

fun TdApi.MessageFileTypeGroup.toDomain(): MessageFileTypeGroup = MessageFileTypeGroup(
    title = this.title
)

fun TdApi.MessageFileTypePrivate.toDomain(): MessageFileTypePrivate = MessageFileTypePrivate(
    name = this.name
)

fun TdApi.MessageFileTypeUnknown.toDomain(): MessageFileTypeUnknown = MessageFileTypeUnknown

fun TdApi.MessageForumTopicCreated.toDomain(): MessageForumTopicCreated = MessageForumTopicCreated(
    name = this.name,
    icon = this.icon.toDomain()
)

fun TdApi.MessageForumTopicEdited.toDomain(): MessageForumTopicEdited = MessageForumTopicEdited(
    name = this.name,
    editIconCustomEmojiId = this.editIconCustomEmojiId,
    iconCustomEmojiId = this.iconCustomEmojiId
)

fun TdApi.MessageForumTopicIsClosedToggled.toDomain(): MessageForumTopicIsClosedToggled = MessageForumTopicIsClosedToggled(
    isClosed = this.isClosed
)

fun TdApi.MessageForumTopicIsHiddenToggled.toDomain(): MessageForumTopicIsHiddenToggled = MessageForumTopicIsHiddenToggled(
    isHidden = this.isHidden
)

fun TdApi.MessageForwardInfo.toDomain(): MessageForwardInfo = MessageForwardInfo(
    origin = this.origin.toDomain(),
    date = this.date,
    source = this.source?.toDomain(),
    publicServiceAnnouncementType = this.publicServiceAnnouncementType
)

fun TdApi.MessageGame.toDomain(): MessageGame = MessageGame(
    game = this.game.toDomain()
)

fun TdApi.MessageGameScore.toDomain(): MessageGameScore = MessageGameScore(
    gameMessageId = this.gameMessageId,
    gameId = this.gameId,
    score = this.score
)

fun TdApi.MessageGift.toDomain(): MessageGift = MessageGift(
    gift = this.gift.toDomain(),
    senderId = this.senderId?.toDomain(),
    receiverId = this.receiverId.toDomain(),
    receivedGiftId = this.receivedGiftId,
    text = this.text.toDomain(),
    sellStarCount = this.sellStarCount,
    prepaidUpgradeStarCount = this.prepaidUpgradeStarCount,
    isUpgradeSeparate = this.isUpgradeSeparate,
    isPrivate = this.isPrivate,
    isSaved = this.isSaved,
    isPrepaidUpgrade = this.isPrepaidUpgrade,
    canBeUpgraded = this.canBeUpgraded,
    wasConverted = this.wasConverted,
    wasUpgraded = this.wasUpgraded,
    wasRefunded = this.wasRefunded,
    upgradedReceivedGiftId = this.upgradedReceivedGiftId,
    prepaidUpgradeHash = this.prepaidUpgradeHash
)

fun TdApi.MessageGiftedPremium.toDomain(): MessageGiftedPremium = MessageGiftedPremium(
    gifterUserId = this.gifterUserId,
    receiverUserId = this.receiverUserId,
    text = this.text.toDomain(),
    currency = this.currency,
    amount = this.amount,
    cryptocurrency = this.cryptocurrency,
    cryptocurrencyAmount = this.cryptocurrencyAmount,
    monthCount = this.monthCount,
    sticker = this.sticker?.toDomain()
)

fun TdApi.MessageGiftedStars.toDomain(): MessageGiftedStars = MessageGiftedStars(
    gifterUserId = this.gifterUserId,
    receiverUserId = this.receiverUserId,
    currency = this.currency,
    amount = this.amount,
    cryptocurrency = this.cryptocurrency,
    cryptocurrencyAmount = this.cryptocurrencyAmount,
    starCount = this.starCount,
    transactionId = this.transactionId,
    sticker = this.sticker?.toDomain()
)

fun TdApi.MessageGiftedTon.toDomain(): MessageGiftedTon = MessageGiftedTon(
    gifterUserId = this.gifterUserId,
    receiverUserId = this.receiverUserId,
    tonAmount = this.tonAmount,
    transactionId = this.transactionId,
    sticker = this.sticker?.toDomain()
)

fun TdApi.MessageGiveaway.toDomain(): MessageGiveaway = MessageGiveaway(
    parameters = this.parameters.toDomain(),
    winnerCount = this.winnerCount,
    prize = this.prize.toDomain(),
    sticker = this.sticker?.toDomain()
)

fun TdApi.MessageGiveawayCompleted.toDomain(): MessageGiveawayCompleted = MessageGiveawayCompleted(
    giveawayMessageId = this.giveawayMessageId,
    winnerCount = this.winnerCount,
    isStarGiveaway = this.isStarGiveaway,
    unclaimedPrizeCount = this.unclaimedPrizeCount
)

fun TdApi.MessageGiveawayCreated.toDomain(): MessageGiveawayCreated = MessageGiveawayCreated(
    starCount = this.starCount
)

fun TdApi.MessageGiveawayPrizeStars.toDomain(): MessageGiveawayPrizeStars = MessageGiveawayPrizeStars(
    starCount = this.starCount,
    transactionId = this.transactionId,
    boostedChatId = this.boostedChatId,
    giveawayMessageId = this.giveawayMessageId,
    isUnclaimed = this.isUnclaimed,
    sticker = this.sticker?.toDomain()
)

fun TdApi.MessageGiveawayWinners.toDomain(): MessageGiveawayWinners = MessageGiveawayWinners(
    boostedChatId = this.boostedChatId,
    giveawayMessageId = this.giveawayMessageId,
    additionalChatCount = this.additionalChatCount,
    actualWinnersSelectionDate = this.actualWinnersSelectionDate,
    onlyNewMembers = this.onlyNewMembers,
    wasRefunded = this.wasRefunded,
    prize = this.prize.toDomain(),
    prizeDescription = this.prizeDescription,
    winnerCount = this.winnerCount,
    winnerUserIds = this.winnerUserIds,
    unclaimedPrizeCount = this.unclaimedPrizeCount
)

fun TdApi.MessageGroupCall.toDomain(): MessageGroupCall = MessageGroupCall(
    isActive = this.isActive,
    wasMissed = this.wasMissed,
    isVideo = this.isVideo,
    duration = this.duration,
    otherParticipantIds = this.otherParticipantIds.map { it.toDomain() }
)

fun TdApi.MessageImportInfo.toDomain(): MessageImportInfo = MessageImportInfo(
    senderName = this.senderName,
    date = this.date
)

fun TdApi.MessageInteractionInfo.toDomain(): MessageInteractionInfo = MessageInteractionInfo(
    viewCount = this.viewCount,
    forwardCount = this.forwardCount,
    replyInfo = this.replyInfo?.toDomain(),
    reactions = this.reactions?.toDomain()
)

fun TdApi.MessageInviteVideoChatParticipants.toDomain(): MessageInviteVideoChatParticipants = MessageInviteVideoChatParticipants(
    groupCallId = this.groupCallId,
    userIds = this.userIds
)

fun TdApi.MessageInvoice.toDomain(): MessageInvoice = MessageInvoice(
    productInfo = this.productInfo.toDomain(),
    currency = this.currency,
    totalAmount = this.totalAmount,
    startParameter = this.startParameter,
    isTest = this.isTest,
    needShippingAddress = this.needShippingAddress,
    receiptMessageId = this.receiptMessageId,
    paidMedia = this.paidMedia?.toDomain(),
    paidMediaCaption = this.paidMediaCaption?.toDomain()
)

fun TdApi.MessageLink.toDomain(): MessageLink = MessageLink(
    link = this.link,
    isPublic = this.isPublic
)

fun TdApi.MessageLinkInfo.toDomain(): MessageLinkInfo = MessageLinkInfo(
    isPublic = this.isPublic,
    chatId = this.chatId,
    messageThreadId = this.messageThreadId,
    message = this.message?.toDomain(),
    mediaTimestamp = this.mediaTimestamp,
    forAlbum = this.forAlbum
)

fun TdApi.MessageLocation.toDomain(): MessageLocation = MessageLocation(
    location = this.location.toDomain(),
    livePeriod = this.livePeriod,
    expiresIn = this.expiresIn,
    heading = this.heading,
    proximityAlertRadius = this.proximityAlertRadius
)

fun TdApi.MessageOrigin.toDomain(): MessageOrigin = when(this) {
    is TdApi.MessageOriginUser -> this.toDomain()
    is TdApi.MessageOriginHiddenUser -> this.toDomain()
    is TdApi.MessageOriginChat -> this.toDomain()
    is TdApi.MessageOriginChannel -> this.toDomain()
    else -> throw IllegalArgumentException("Unknown type: ${this.javaClass}")
}

fun TdApi.MessageOriginChannel.toDomain(): MessageOriginChannel = MessageOriginChannel(
    chatId = this.chatId,
    messageId = this.messageId,
    authorSignature = this.authorSignature
)

fun TdApi.MessageOriginChat.toDomain(): MessageOriginChat = MessageOriginChat(
    senderChatId = this.senderChatId,
    authorSignature = this.authorSignature
)

fun TdApi.MessageOriginHiddenUser.toDomain(): MessageOriginHiddenUser = MessageOriginHiddenUser(
    senderName = this.senderName
)

fun TdApi.MessageOriginUser.toDomain(): MessageOriginUser = MessageOriginUser(
    senderUserId = this.senderUserId
)

fun TdApi.MessagePaidMedia.toDomain(): MessagePaidMedia = MessagePaidMedia(
    starCount = this.starCount,
    media = this.media.map { it.toDomain() },
    caption = this.caption.toDomain(),
    showCaptionAboveMedia = this.showCaptionAboveMedia
)

fun TdApi.MessagePaidMessagePriceChanged.toDomain(): MessagePaidMessagePriceChanged = MessagePaidMessagePriceChanged(
    paidMessageStarCount = this.paidMessageStarCount
)

fun TdApi.MessagePaidMessagesRefunded.toDomain(): MessagePaidMessagesRefunded = MessagePaidMessagesRefunded(
    messageCount = this.messageCount,
    starCount = this.starCount
)

fun TdApi.MessagePassportDataReceived.toDomain(): MessagePassportDataReceived = MessagePassportDataReceived(
    elements = this.elements.map { it.toDomain() },
    credentials = this.credentials.toDomain()
)

fun TdApi.MessagePassportDataSent.toDomain(): MessagePassportDataSent = MessagePassportDataSent(
    types = this.types.map { it.toDomain() }
)

fun TdApi.MessagePaymentRefunded.toDomain(): MessagePaymentRefunded = MessagePaymentRefunded(
    ownerId = this.ownerId.toDomain(),
    currency = this.currency,
    totalAmount = this.totalAmount,
    invoicePayload = this.invoicePayload,
    telegramPaymentChargeId = this.telegramPaymentChargeId,
    providerPaymentChargeId = this.providerPaymentChargeId
)

fun TdApi.MessagePaymentSuccessful.toDomain(): MessagePaymentSuccessful = MessagePaymentSuccessful(
    invoiceChatId = this.invoiceChatId,
    invoiceMessageId = this.invoiceMessageId,
    currency = this.currency,
    totalAmount = this.totalAmount,
    subscriptionUntilDate = this.subscriptionUntilDate,
    isRecurring = this.isRecurring,
    isFirstRecurring = this.isFirstRecurring,
    invoiceName = this.invoiceName
)

fun TdApi.MessagePaymentSuccessfulBot.toDomain(): MessagePaymentSuccessfulBot = MessagePaymentSuccessfulBot(
    currency = this.currency,
    totalAmount = this.totalAmount,
    subscriptionUntilDate = this.subscriptionUntilDate,
    isRecurring = this.isRecurring,
    isFirstRecurring = this.isFirstRecurring,
    invoicePayload = this.invoicePayload,
    shippingOptionId = this.shippingOptionId,
    orderInfo = this.orderInfo?.toDomain(),
    telegramPaymentChargeId = this.telegramPaymentChargeId,
    providerPaymentChargeId = this.providerPaymentChargeId
)

fun TdApi.MessagePhoto.toDomain(): MessagePhoto = MessagePhoto(
    photo = this.photo.toDomain(),
    caption = this.caption.toDomain(),
    showCaptionAboveMedia = this.showCaptionAboveMedia,
    hasSpoiler = this.hasSpoiler,
    isSecret = this.isSecret
)

fun TdApi.MessagePinMessage.toDomain(): MessagePinMessage = MessagePinMessage(
    messageId = this.messageId
)

fun TdApi.MessagePoll.toDomain(): MessagePoll = MessagePoll(
    poll = this.poll.toDomain()
)

fun TdApi.MessagePosition.toDomain(): MessagePosition = MessagePosition(
    position = this.position,
    messageId = this.messageId,
    date = this.date
)

fun TdApi.MessagePositions.toDomain(): MessagePositions = MessagePositions(
    totalCount = this.totalCount,
    positions = this.positions.map { it.toDomain() }
)

fun TdApi.MessagePremiumGiftCode.toDomain(): MessagePremiumGiftCode = MessagePremiumGiftCode(
    creatorId = this.creatorId?.toDomain(),
    text = this.text.toDomain(),
    isFromGiveaway = this.isFromGiveaway,
    isUnclaimed = this.isUnclaimed,
    currency = this.currency,
    amount = this.amount,
    cryptocurrency = this.cryptocurrency,
    cryptocurrencyAmount = this.cryptocurrencyAmount,
    monthCount = this.monthCount,
    sticker = this.sticker?.toDomain(),
    code = this.code
)

fun TdApi.MessageProperties.toDomain(): MessageProperties = MessageProperties(
    canAddOffer = this.canAddOffer,
    canAddTasks = this.canAddTasks,
    canBeApproved = this.canBeApproved,
    canBeCopied = this.canBeCopied,
    canBeCopiedToSecretChat = this.canBeCopiedToSecretChat,
    canBeDeclined = this.canBeDeclined,
    canBeDeletedOnlyForSelf = this.canBeDeletedOnlyForSelf,
    canBeDeletedForAllUsers = this.canBeDeletedForAllUsers,
    canBeEdited = this.canBeEdited,
    canBeForwarded = this.canBeForwarded,
    canBePaid = this.canBePaid,
    canBePinned = this.canBePinned,
    canBeReplied = this.canBeReplied,
    canBeRepliedInAnotherChat = this.canBeRepliedInAnotherChat,
    canBeSaved = this.canBeSaved,
    canBeSharedInStory = this.canBeSharedInStory,
    canEditMedia = this.canEditMedia,
    canEditSchedulingState = this.canEditSchedulingState,
    canEditSuggestedPostInfo = this.canEditSuggestedPostInfo,
    canGetAuthor = this.canGetAuthor,
    canGetEmbeddingCode = this.canGetEmbeddingCode,
    canGetLink = this.canGetLink,
    canGetMediaTimestampLinks = this.canGetMediaTimestampLinks,
    canGetMessageThread = this.canGetMessageThread,
    canGetReadDate = this.canGetReadDate,
    canGetStatistics = this.canGetStatistics,
    canGetVideoAdvertisements = this.canGetVideoAdvertisements,
    canGetViewers = this.canGetViewers,
    canMarkTasksAsDone = this.canMarkTasksAsDone,
    canRecognizeSpeech = this.canRecognizeSpeech,
    canReportChat = this.canReportChat,
    canReportReactions = this.canReportReactions,
    canReportSupergroupSpam = this.canReportSupergroupSpam,
    canSetFactCheck = this.canSetFactCheck,
    needShowStatistics = this.needShowStatistics
)

fun TdApi.MessageProximityAlertTriggered.toDomain(): MessageProximityAlertTriggered = MessageProximityAlertTriggered(
    travelerId = this.travelerId.toDomain(),
    watcherId = this.watcherId.toDomain(),
    distance = this.distance
)

fun TdApi.MessageReaction.toDomain(): MessageReaction = MessageReaction(
    type = this.type.toDomain(),
    totalCount = this.totalCount,
    isChosen = this.isChosen,
    usedSenderId = this.usedSenderId?.toDomain(),
    recentSenderIds = this.recentSenderIds.map { it.toDomain() }
)

fun TdApi.MessageReactions.toDomain(): MessageReactions = MessageReactions(
    reactions = this.reactions.map { it.toDomain() },
    areTags = this.areTags,
    paidReactors = this.paidReactors.map { it.toDomain() },
    canGetAddedReactions = this.canGetAddedReactions
)

fun TdApi.MessageReadDate.toDomain(): MessageReadDate = when(this) {
    is TdApi.MessageReadDateRead -> this.toDomain()
    is TdApi.MessageReadDateUnread -> this.toDomain()
    is TdApi.MessageReadDateTooOld -> this.toDomain()
    is TdApi.MessageReadDateUserPrivacyRestricted -> this.toDomain()
    is TdApi.MessageReadDateMyPrivacyRestricted -> this.toDomain()
    else -> throw IllegalArgumentException("Unknown type: ${this.javaClass}")
}

fun TdApi.MessageReadDateMyPrivacyRestricted.toDomain(): MessageReadDateMyPrivacyRestricted = MessageReadDateMyPrivacyRestricted

fun TdApi.MessageReadDateRead.toDomain(): MessageReadDateRead = MessageReadDateRead(
    readDate = this.readDate
)

fun TdApi.MessageReadDateTooOld.toDomain(): MessageReadDateTooOld = MessageReadDateTooOld

fun TdApi.MessageReadDateUnread.toDomain(): MessageReadDateUnread = MessageReadDateUnread

fun TdApi.MessageReadDateUserPrivacyRestricted.toDomain(): MessageReadDateUserPrivacyRestricted = MessageReadDateUserPrivacyRestricted

fun TdApi.MessageRefundedUpgradedGift.toDomain(): MessageRefundedUpgradedGift = MessageRefundedUpgradedGift(
    gift = this.gift.toDomain(),
    senderId = this.senderId.toDomain(),
    receiverId = this.receiverId.toDomain(),
    origin = this.origin.toDomain()
)

fun TdApi.MessageReplyInfo.toDomain(): MessageReplyInfo = MessageReplyInfo(
    replyCount = this.replyCount,
    recentReplierIds = this.recentReplierIds.map { it.toDomain() },
    lastReadInboxMessageId = this.lastReadInboxMessageId,
    lastReadOutboxMessageId = this.lastReadOutboxMessageId,
    lastMessageId = this.lastMessageId
)

fun TdApi.MessageReplyTo.toDomain(): MessageReplyTo = when(this) {
    is TdApi.MessageReplyToMessage -> this.toDomain()
    is TdApi.MessageReplyToStory -> this.toDomain()
    else -> throw IllegalArgumentException("Unknown type: ${this.javaClass}")
}

fun TdApi.MessageReplyToMessage.toDomain(): MessageReplyToMessage = MessageReplyToMessage(
    chatId = this.chatId,
    messageId = this.messageId,
    quote = this.quote?.toDomain(),
    checklistTaskId = this.checklistTaskId,
    origin = this.origin?.toDomain(),
    originSendDate = this.originSendDate,
    content = this.content?.toDomain()
)

fun TdApi.MessageReplyToStory.toDomain(): MessageReplyToStory = MessageReplyToStory(
    storyPosterChatId = this.storyPosterChatId,
    storyId = this.storyId
)

fun TdApi.MessageSchedulingState.toDomain(): MessageSchedulingState = when(this) {
    is TdApi.MessageSchedulingStateSendAtDate -> this.toDomain()
    is TdApi.MessageSchedulingStateSendWhenOnline -> this.toDomain()
    is TdApi.MessageSchedulingStateSendWhenVideoProcessed -> this.toDomain()
    else -> throw IllegalArgumentException("Unknown type: ${this.javaClass}")
}

fun TdApi.MessageSchedulingStateSendAtDate.toDomain(): MessageSchedulingStateSendAtDate = MessageSchedulingStateSendAtDate(
    sendDate = this.sendDate
)

fun TdApi.MessageSchedulingStateSendWhenOnline.toDomain(): MessageSchedulingStateSendWhenOnline = MessageSchedulingStateSendWhenOnline

fun TdApi.MessageSchedulingStateSendWhenVideoProcessed.toDomain(): MessageSchedulingStateSendWhenVideoProcessed = MessageSchedulingStateSendWhenVideoProcessed(
    sendDate = this.sendDate
)

fun TdApi.MessageScreenshotTaken.toDomain(): MessageScreenshotTaken = MessageScreenshotTaken

fun TdApi.MessageSelfDestructType.toDomain(): MessageSelfDestructType = when(this) {
    is TdApi.MessageSelfDestructTypeTimer -> this.toDomain()
    is TdApi.MessageSelfDestructTypeImmediately -> this.toDomain()
    else -> throw IllegalArgumentException("Unknown type: ${this.javaClass}")
}

fun TdApi.MessageSelfDestructTypeImmediately.toDomain(): MessageSelfDestructTypeImmediately = MessageSelfDestructTypeImmediately

fun TdApi.MessageSelfDestructTypeTimer.toDomain(): MessageSelfDestructTypeTimer = MessageSelfDestructTypeTimer(
    selfDestructTime = this.selfDestructTime
)

fun TdApi.MessageSendOptions.toDomain(): MessageSendOptions = MessageSendOptions(
    directMessagesChatTopicId = this.directMessagesChatTopicId,
    suggestedPostInfo = this.suggestedPostInfo.toDomain(),
    disableNotification = this.disableNotification,
    fromBackground = this.fromBackground,
    protectContent = this.protectContent,
    allowPaidBroadcast = this.allowPaidBroadcast,
    paidMessageStarCount = this.paidMessageStarCount,
    updateOrderOfInstalledStickerSets = this.updateOrderOfInstalledStickerSets,
    schedulingState = this.schedulingState.toDomain(),
    effectId = this.effectId,
    sendingId = this.sendingId,
    onlyPreview = this.onlyPreview
)

fun TdApi.MessageSender.toDomain(): MessageSender = when(this) {
    is TdApi.MessageSenderUser -> this.toDomain()
    is TdApi.MessageSenderChat -> this.toDomain()
    else -> throw IllegalArgumentException("Unknown type: ${this.javaClass}")
}

fun TdApi.MessageSenderChat.toDomain(): MessageSenderChat = MessageSenderChat(
    chatId = this.chatId
)

fun TdApi.MessageSenderUser.toDomain(): MessageSenderUser = MessageSenderUser(
    userId = this.userId
)

fun TdApi.MessageSenders.toDomain(): MessageSenders = MessageSenders(
    totalCount = this.totalCount,
    senders = this.senders.map { it.toDomain() }
)

fun TdApi.MessageSendingState.toDomain(): MessageSendingState = when(this) {
    is TdApi.MessageSendingStatePending -> this.toDomain()
    is TdApi.MessageSendingStateFailed -> this.toDomain()
    else -> throw IllegalArgumentException("Unknown type: ${this.javaClass}")
}

fun TdApi.MessageSendingStateFailed.toDomain(): MessageSendingStateFailed = MessageSendingStateFailed(
    error = this.error.toDomain(),
    canRetry = this.canRetry,
    needAnotherSender = this.needAnotherSender,
    needAnotherReplyQuote = this.needAnotherReplyQuote,
    needDropReply = this.needDropReply,
    requiredPaidMessageStarCount = this.requiredPaidMessageStarCount,
    retryAfter = this.retryAfter
)

fun TdApi.MessageSendingStatePending.toDomain(): MessageSendingStatePending = MessageSendingStatePending(
    sendingId = this.sendingId
)

fun TdApi.MessageSource.toDomain(): MessageSource = when(this) {
    is TdApi.MessageSourceChatHistory -> this.toDomain()
    is TdApi.MessageSourceMessageThreadHistory -> this.toDomain()
    is TdApi.MessageSourceForumTopicHistory -> this.toDomain()
    is TdApi.MessageSourceDirectMessagesChatTopicHistory -> this.toDomain()
    is TdApi.MessageSourceHistoryPreview -> this.toDomain()
    is TdApi.MessageSourceChatList -> this.toDomain()
    is TdApi.MessageSourceSearch -> this.toDomain()
    is TdApi.MessageSourceChatEventLog -> this.toDomain()
    is TdApi.MessageSourceNotification -> this.toDomain()
    is TdApi.MessageSourceScreenshot -> this.toDomain()
    is TdApi.MessageSourceOther -> this.toDomain()
    else -> throw IllegalArgumentException("Unknown type: ${this.javaClass}")
}

fun TdApi.MessageSourceChatEventLog.toDomain(): MessageSourceChatEventLog = MessageSourceChatEventLog

fun TdApi.MessageSourceChatHistory.toDomain(): MessageSourceChatHistory = MessageSourceChatHistory

fun TdApi.MessageSourceChatList.toDomain(): MessageSourceChatList = MessageSourceChatList

fun TdApi.MessageSourceDirectMessagesChatTopicHistory.toDomain(): MessageSourceDirectMessagesChatTopicHistory = MessageSourceDirectMessagesChatTopicHistory

fun TdApi.MessageSourceForumTopicHistory.toDomain(): MessageSourceForumTopicHistory = MessageSourceForumTopicHistory

fun TdApi.MessageSourceHistoryPreview.toDomain(): MessageSourceHistoryPreview = MessageSourceHistoryPreview

fun TdApi.MessageSourceMessageThreadHistory.toDomain(): MessageSourceMessageThreadHistory = MessageSourceMessageThreadHistory

fun TdApi.MessageSourceNotification.toDomain(): MessageSourceNotification = MessageSourceNotification

fun TdApi.MessageSourceOther.toDomain(): MessageSourceOther = MessageSourceOther

fun TdApi.MessageSourceScreenshot.toDomain(): MessageSourceScreenshot = MessageSourceScreenshot

fun TdApi.MessageSourceSearch.toDomain(): MessageSourceSearch = MessageSourceSearch

fun TdApi.MessageStatistics.toDomain(): MessageStatistics = MessageStatistics(
    messageInteractionGraph = this.messageInteractionGraph.toDomain(),
    messageReactionGraph = this.messageReactionGraph.toDomain()
)

fun TdApi.MessageSticker.toDomain(): MessageSticker = MessageSticker(
    sticker = this.sticker.toDomain(),
    isPremium = this.isPremium
)

fun TdApi.MessageStory.toDomain(): MessageStory = MessageStory(
    storyPosterChatId = this.storyPosterChatId,
    storyId = this.storyId,
    viaMention = this.viaMention
)

fun TdApi.MessageSuggestProfilePhoto.toDomain(): MessageSuggestProfilePhoto = MessageSuggestProfilePhoto(
    photo = this.photo.toDomain()
)

fun TdApi.MessageSuggestedPostApprovalFailed.toDomain(): MessageSuggestedPostApprovalFailed = MessageSuggestedPostApprovalFailed(
    suggestedPostMessageId = this.suggestedPostMessageId,
    price = this.price.toDomain()
)

fun TdApi.MessageSuggestedPostApproved.toDomain(): MessageSuggestedPostApproved = MessageSuggestedPostApproved(
    suggestedPostMessageId = this.suggestedPostMessageId,
    price = this.price?.toDomain(),
    sendDate = this.sendDate
)

fun TdApi.MessageSuggestedPostDeclined.toDomain(): MessageSuggestedPostDeclined = MessageSuggestedPostDeclined(
    suggestedPostMessageId = this.suggestedPostMessageId,
    comment = this.comment
)

fun TdApi.MessageSuggestedPostPaid.toDomain(): MessageSuggestedPostPaid = MessageSuggestedPostPaid(
    suggestedPostMessageId = this.suggestedPostMessageId,
    starAmount = this.starAmount.toDomain(),
    tonAmount = this.tonAmount
)

fun TdApi.MessageSuggestedPostRefunded.toDomain(): MessageSuggestedPostRefunded = MessageSuggestedPostRefunded(
    suggestedPostMessageId = this.suggestedPostMessageId,
    reason = this.reason.toDomain()
)

fun TdApi.MessageSupergroupChatCreate.toDomain(): MessageSupergroupChatCreate = MessageSupergroupChatCreate(
    title = this.title
)

fun TdApi.MessageText.toDomain(): MessageText = MessageText(
    text = this.text.toDomain(),
    linkPreview = this.linkPreview?.toDomain(),
    linkPreviewOptions = this.linkPreviewOptions?.toDomain()
)

fun TdApi.MessageThreadInfo.toDomain(): MessageThreadInfo = MessageThreadInfo(
    chatId = this.chatId,
    messageThreadId = this.messageThreadId,
    replyInfo = this.replyInfo?.toDomain(),
    unreadMessageCount = this.unreadMessageCount,
    messages = this.messages.map { it.toDomain() },
    draftMessage = this.draftMessage?.toDomain()
)

fun TdApi.MessageTopic.toDomain(): MessageTopic = when(this) {
    is TdApi.MessageTopicForum -> this.toDomain()
    is TdApi.MessageTopicDirectMessages -> this.toDomain()
    is TdApi.MessageTopicSavedMessages -> this.toDomain()
    else -> throw IllegalArgumentException("Unknown type: ${this.javaClass}")
}

fun TdApi.MessageTopicDirectMessages.toDomain(): MessageTopicDirectMessages = MessageTopicDirectMessages(
    directMessagesChatTopicId = this.directMessagesChatTopicId
)

fun TdApi.MessageTopicForum.toDomain(): MessageTopicForum = MessageTopicForum(
    forumTopicId = this.forumTopicId
)

fun TdApi.MessageTopicSavedMessages.toDomain(): MessageTopicSavedMessages = MessageTopicSavedMessages(
    savedMessagesTopicId = this.savedMessagesTopicId
)

fun TdApi.MessageUnsupported.toDomain(): MessageUnsupported = MessageUnsupported

fun TdApi.MessageUpgradedGift.toDomain(): MessageUpgradedGift = MessageUpgradedGift(
    gift = this.gift.toDomain(),
    senderId = this.senderId?.toDomain(),
    receiverId = this.receiverId.toDomain(),
    origin = this.origin.toDomain(),
    receivedGiftId = this.receivedGiftId,
    isSaved = this.isSaved,
    canBeTransferred = this.canBeTransferred,
    wasTransferred = this.wasTransferred,
    transferStarCount = this.transferStarCount,
    nextTransferDate = this.nextTransferDate,
    nextResaleDate = this.nextResaleDate,
    exportDate = this.exportDate
)

fun TdApi.MessageUsersShared.toDomain(): MessageUsersShared = MessageUsersShared(
    users = this.users.map { it.toDomain() },
    buttonId = this.buttonId
)

fun TdApi.MessageVenue.toDomain(): MessageVenue = MessageVenue(
    venue = this.venue.toDomain()
)

fun TdApi.MessageVideo.toDomain(): MessageVideo = MessageVideo(
    video = this.video.toDomain(),
    alternativeVideos = this.alternativeVideos.map { it.toDomain() },
    storyboards = this.storyboards.map { it.toDomain() },
    cover = this.cover?.toDomain(),
    startTimestamp = this.startTimestamp,
    caption = this.caption.toDomain(),
    showCaptionAboveMedia = this.showCaptionAboveMedia,
    hasSpoiler = this.hasSpoiler,
    isSecret = this.isSecret
)

fun TdApi.MessageVideoChatEnded.toDomain(): MessageVideoChatEnded = MessageVideoChatEnded(
    duration = this.duration
)

fun TdApi.MessageVideoChatScheduled.toDomain(): MessageVideoChatScheduled = MessageVideoChatScheduled(
    groupCallId = this.groupCallId,
    startDate = this.startDate
)

fun TdApi.MessageVideoChatStarted.toDomain(): MessageVideoChatStarted = MessageVideoChatStarted(
    groupCallId = this.groupCallId
)

fun TdApi.MessageVideoNote.toDomain(): MessageVideoNote = MessageVideoNote(
    videoNote = this.videoNote.toDomain(),
    isViewed = this.isViewed,
    isSecret = this.isSecret
)

fun TdApi.MessageViewer.toDomain(): MessageViewer = MessageViewer(
    userId = this.userId,
    viewDate = this.viewDate
)

fun TdApi.MessageViewers.toDomain(): MessageViewers = MessageViewers(
    viewers = this.viewers.map { it.toDomain() }
)

fun TdApi.MessageVoiceNote.toDomain(): MessageVoiceNote = MessageVoiceNote(
    voiceNote = this.voiceNote.toDomain(),
    caption = this.caption.toDomain(),
    isListened = this.isListened
)

fun TdApi.MessageWebAppDataReceived.toDomain(): MessageWebAppDataReceived = MessageWebAppDataReceived(
    buttonText = this.buttonText,
    data = this.data
)

fun TdApi.MessageWebAppDataSent.toDomain(): MessageWebAppDataSent = MessageWebAppDataSent(
    buttonText = this.buttonText
)

fun TdApi.Messages.toDomain(): Messages = Messages(
    totalCount = this.totalCount,
    messages = this.messages.map { it.toDomain() }
)

fun TdApi.ResendMessages.toDomain(): ResendMessages = ResendMessages(
    chatId = this.chatId,
    messageIds = this.messageIds,
    quote = this.quote.toDomain(),
    paidMessageStarCount = this.paidMessageStarCount
)

fun TdApi.SendBotStartMessage.toDomain(): SendBotStartMessage = SendBotStartMessage(
    botUserId = this.botUserId,
    chatId = this.chatId,
    parameter = this.parameter
)

fun TdApi.SendInlineQueryResultMessage.toDomain(): SendInlineQueryResultMessage = SendInlineQueryResultMessage(
    chatId = this.chatId,
    messageThreadId = this.messageThreadId,
    replyTo = this.replyTo.toDomain(),
    options = this.options.toDomain(),
    queryId = this.queryId,
    resultId = this.resultId,
    hideViaBot = this.hideViaBot
)

fun TdApi.SendMessage.toDomain(): SendMessage = SendMessage(
    chatId = this.chatId,
    messageThreadId = this.messageThreadId,
    replyTo = this.replyTo.toDomain(),
    options = this.options.toDomain(),
    replyMarkup = this.replyMarkup.toDomain(),
    inputMessageContent = this.inputMessageContent.toDomain()
)

fun TdApi.SendMessageAlbum.toDomain(): SendMessageAlbum = SendMessageAlbum(
    chatId = this.chatId,
    messageThreadId = this.messageThreadId,
    replyTo = this.replyTo.toDomain(),
    options = this.options.toDomain(),
    inputMessageContents = this.inputMessageContents.map { it.toDomain() }
)

fun TdApi.SetChatDraftMessage.toDomain(): SetChatDraftMessage = SetChatDraftMessage(
    chatId = this.chatId,
    messageThreadId = this.messageThreadId,
    draftMessage = this.draftMessage.toDomain()
)

