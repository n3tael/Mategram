package com.xxcactussell.data.utils.mappers.push

import com.xxcactussell.data.utils.mappers.animation.toDomain
import com.xxcactussell.data.utils.mappers.file.toDomain
import com.xxcactussell.data.utils.mappers.giveaway.toDomain
import com.xxcactussell.data.utils.mappers.photo.toDomain
import com.xxcactussell.data.utils.mappers.sticker.toDomain
import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: TdApi -> Domain

fun TdApi.PushMessageContent.toDomain(): PushMessageContent = when(this) {
    is TdApi.PushMessageContentHidden -> this.toDomain()
    is TdApi.PushMessageContentAnimation -> this.toDomain()
    is TdApi.PushMessageContentAudio -> this.toDomain()
    is TdApi.PushMessageContentContact -> this.toDomain()
    is TdApi.PushMessageContentContactRegistered -> this.toDomain()
    is TdApi.PushMessageContentDocument -> this.toDomain()
    is TdApi.PushMessageContentGame -> this.toDomain()
    is TdApi.PushMessageContentGameScore -> this.toDomain()
    is TdApi.PushMessageContentInvoice -> this.toDomain()
    is TdApi.PushMessageContentLocation -> this.toDomain()
    is TdApi.PushMessageContentPaidMedia -> this.toDomain()
    is TdApi.PushMessageContentPhoto -> this.toDomain()
    is TdApi.PushMessageContentPoll -> this.toDomain()
    is TdApi.PushMessageContentPremiumGiftCode -> this.toDomain()
    is TdApi.PushMessageContentGiveaway -> this.toDomain()
    is TdApi.PushMessageContentGift -> this.toDomain()
    is TdApi.PushMessageContentUpgradedGift -> this.toDomain()
    is TdApi.PushMessageContentScreenshotTaken -> this.toDomain()
    is TdApi.PushMessageContentSticker -> this.toDomain()
    is TdApi.PushMessageContentStory -> this.toDomain()
    is TdApi.PushMessageContentText -> this.toDomain()
    is TdApi.PushMessageContentChecklist -> this.toDomain()
    is TdApi.PushMessageContentVideo -> this.toDomain()
    is TdApi.PushMessageContentVideoNote -> this.toDomain()
    is TdApi.PushMessageContentVoiceNote -> this.toDomain()
    is TdApi.PushMessageContentBasicGroupChatCreate -> this.toDomain()
    is TdApi.PushMessageContentVideoChatStarted -> this.toDomain()
    is TdApi.PushMessageContentVideoChatEnded -> this.toDomain()
    is TdApi.PushMessageContentInviteVideoChatParticipants -> this.toDomain()
    is TdApi.PushMessageContentChatAddMembers -> this.toDomain()
    is TdApi.PushMessageContentChatChangePhoto -> this.toDomain()
    is TdApi.PushMessageContentChatChangeTitle -> this.toDomain()
    is TdApi.PushMessageContentChatSetBackground -> this.toDomain()
    is TdApi.PushMessageContentChatSetTheme -> this.toDomain()
    is TdApi.PushMessageContentChatDeleteMember -> this.toDomain()
    is TdApi.PushMessageContentChatJoinByLink -> this.toDomain()
    is TdApi.PushMessageContentChatJoinByRequest -> this.toDomain()
    is TdApi.PushMessageContentRecurringPayment -> this.toDomain()
    is TdApi.PushMessageContentSuggestProfilePhoto -> this.toDomain()
    is TdApi.PushMessageContentProximityAlertTriggered -> this.toDomain()
    is TdApi.PushMessageContentChecklistTasksAdded -> this.toDomain()
    is TdApi.PushMessageContentChecklistTasksDone -> this.toDomain()
    is TdApi.PushMessageContentMessageForwards -> this.toDomain()
    is TdApi.PushMessageContentMediaAlbum -> this.toDomain()
    else -> throw IllegalArgumentException("Unknown type: ${this.javaClass}")
}

fun TdApi.PushMessageContentAnimation.toDomain(): PushMessageContentAnimation = PushMessageContentAnimation(
    animation = this.animation?.toDomain(),
    caption = this.caption,
    isPinned = this.isPinned
)

fun TdApi.PushMessageContentAudio.toDomain(): PushMessageContentAudio = PushMessageContentAudio(
    audio = this.audio?.toDomain(),
    isPinned = this.isPinned
)

fun TdApi.PushMessageContentBasicGroupChatCreate.toDomain(): PushMessageContentBasicGroupChatCreate = PushMessageContentBasicGroupChatCreate

fun TdApi.PushMessageContentChatAddMembers.toDomain(): PushMessageContentChatAddMembers = PushMessageContentChatAddMembers(
    memberName = this.memberName,
    isCurrentUser = this.isCurrentUser,
    isReturned = this.isReturned
)

fun TdApi.PushMessageContentChatChangePhoto.toDomain(): PushMessageContentChatChangePhoto = PushMessageContentChatChangePhoto

fun TdApi.PushMessageContentChatChangeTitle.toDomain(): PushMessageContentChatChangeTitle = PushMessageContentChatChangeTitle(
    title = this.title
)

fun TdApi.PushMessageContentChatDeleteMember.toDomain(): PushMessageContentChatDeleteMember = PushMessageContentChatDeleteMember(
    memberName = this.memberName,
    isCurrentUser = this.isCurrentUser,
    isLeft = this.isLeft
)

fun TdApi.PushMessageContentChatJoinByLink.toDomain(): PushMessageContentChatJoinByLink = PushMessageContentChatJoinByLink

fun TdApi.PushMessageContentChatJoinByRequest.toDomain(): PushMessageContentChatJoinByRequest = PushMessageContentChatJoinByRequest

fun TdApi.PushMessageContentChatSetBackground.toDomain(): PushMessageContentChatSetBackground = PushMessageContentChatSetBackground(
    isSame = this.isSame
)

fun TdApi.PushMessageContentChatSetTheme.toDomain(): PushMessageContentChatSetTheme = PushMessageContentChatSetTheme(
    name = this.name
)

fun TdApi.PushMessageContentChecklist.toDomain(): PushMessageContentChecklist = PushMessageContentChecklist(
    title = this.title,
    isPinned = this.isPinned
)

fun TdApi.PushMessageContentChecklistTasksAdded.toDomain(): PushMessageContentChecklistTasksAdded = PushMessageContentChecklistTasksAdded(
    taskCount = this.taskCount
)

fun TdApi.PushMessageContentChecklistTasksDone.toDomain(): PushMessageContentChecklistTasksDone = PushMessageContentChecklistTasksDone(
    taskCount = this.taskCount
)

fun TdApi.PushMessageContentContact.toDomain(): PushMessageContentContact = PushMessageContentContact(
    name = this.name,
    isPinned = this.isPinned
)

fun TdApi.PushMessageContentContactRegistered.toDomain(): PushMessageContentContactRegistered = PushMessageContentContactRegistered(
    asPremiumAccount = this.asPremiumAccount
)

fun TdApi.PushMessageContentDocument.toDomain(): PushMessageContentDocument = PushMessageContentDocument(
    document = this.document?.toDomain(),
    isPinned = this.isPinned
)

fun TdApi.PushMessageContentGame.toDomain(): PushMessageContentGame = PushMessageContentGame(
    title = this.title,
    isPinned = this.isPinned
)

fun TdApi.PushMessageContentGameScore.toDomain(): PushMessageContentGameScore = PushMessageContentGameScore(
    title = this.title,
    score = this.score,
    isPinned = this.isPinned
)

fun TdApi.PushMessageContentGift.toDomain(): PushMessageContentGift = PushMessageContentGift(
    starCount = this.starCount,
    isPrepaidUpgrade = this.isPrepaidUpgrade
)

fun TdApi.PushMessageContentGiveaway.toDomain(): PushMessageContentGiveaway = PushMessageContentGiveaway(
    winnerCount = this.winnerCount,
    prize = this.prize?.toDomain(),
    isPinned = this.isPinned
)

fun TdApi.PushMessageContentHidden.toDomain(): PushMessageContentHidden = PushMessageContentHidden(
    isPinned = this.isPinned
)

fun TdApi.PushMessageContentInviteVideoChatParticipants.toDomain(): PushMessageContentInviteVideoChatParticipants = PushMessageContentInviteVideoChatParticipants(
    isCurrentUser = this.isCurrentUser
)

fun TdApi.PushMessageContentInvoice.toDomain(): PushMessageContentInvoice = PushMessageContentInvoice(
    price = this.price,
    isPinned = this.isPinned
)

fun TdApi.PushMessageContentLocation.toDomain(): PushMessageContentLocation = PushMessageContentLocation(
    isLive = this.isLive,
    isPinned = this.isPinned
)

fun TdApi.PushMessageContentMediaAlbum.toDomain(): PushMessageContentMediaAlbum = PushMessageContentMediaAlbum(
    totalCount = this.totalCount,
    hasPhotos = this.hasPhotos,
    hasVideos = this.hasVideos,
    hasAudios = this.hasAudios,
    hasDocuments = this.hasDocuments
)

fun TdApi.PushMessageContentMessageForwards.toDomain(): PushMessageContentMessageForwards = PushMessageContentMessageForwards(
    totalCount = this.totalCount
)

fun TdApi.PushMessageContentPaidMedia.toDomain(): PushMessageContentPaidMedia = PushMessageContentPaidMedia(
    starCount = this.starCount,
    isPinned = this.isPinned
)

fun TdApi.PushMessageContentPhoto.toDomain(): PushMessageContentPhoto = PushMessageContentPhoto(
    photo = this.photo?.toDomain(),
    caption = this.caption,
    isSecret = this.isSecret,
    isPinned = this.isPinned
)

fun TdApi.PushMessageContentPoll.toDomain(): PushMessageContentPoll = PushMessageContentPoll(
    question = this.question,
    isRegular = this.isRegular,
    isPinned = this.isPinned
)

fun TdApi.PushMessageContentPremiumGiftCode.toDomain(): PushMessageContentPremiumGiftCode = PushMessageContentPremiumGiftCode(
    monthCount = this.monthCount
)

fun TdApi.PushMessageContentProximityAlertTriggered.toDomain(): PushMessageContentProximityAlertTriggered = PushMessageContentProximityAlertTriggered(
    distance = this.distance
)

fun TdApi.PushMessageContentRecurringPayment.toDomain(): PushMessageContentRecurringPayment = PushMessageContentRecurringPayment(
    amount = this.amount
)

fun TdApi.PushMessageContentScreenshotTaken.toDomain(): PushMessageContentScreenshotTaken = PushMessageContentScreenshotTaken

fun TdApi.PushMessageContentSticker.toDomain(): PushMessageContentSticker = PushMessageContentSticker(
    sticker = this.sticker?.toDomain(),
    emoji = this.emoji,
    isPinned = this.isPinned
)

fun TdApi.PushMessageContentStory.toDomain(): PushMessageContentStory = PushMessageContentStory(
    isMention = this.isMention,
    isPinned = this.isPinned
)

fun TdApi.PushMessageContentSuggestProfilePhoto.toDomain(): PushMessageContentSuggestProfilePhoto = PushMessageContentSuggestProfilePhoto

fun TdApi.PushMessageContentText.toDomain(): PushMessageContentText = PushMessageContentText(
    text = this.text,
    isPinned = this.isPinned
)

fun TdApi.PushMessageContentUpgradedGift.toDomain(): PushMessageContentUpgradedGift = PushMessageContentUpgradedGift(
    isUpgrade = this.isUpgrade,
    isPrepaidUpgrade = this.isPrepaidUpgrade
)

fun TdApi.PushMessageContentVideo.toDomain(): PushMessageContentVideo = PushMessageContentVideo(
    video = this.video?.toDomain(),
    caption = this.caption,
    isSecret = this.isSecret,
    isPinned = this.isPinned
)

fun TdApi.PushMessageContentVideoChatEnded.toDomain(): PushMessageContentVideoChatEnded = PushMessageContentVideoChatEnded

fun TdApi.PushMessageContentVideoChatStarted.toDomain(): PushMessageContentVideoChatStarted = PushMessageContentVideoChatStarted

fun TdApi.PushMessageContentVideoNote.toDomain(): PushMessageContentVideoNote = PushMessageContentVideoNote(
    videoNote = this.videoNote?.toDomain(),
    isPinned = this.isPinned
)

fun TdApi.PushMessageContentVoiceNote.toDomain(): PushMessageContentVoiceNote = PushMessageContentVoiceNote(
    voiceNote = this.voiceNote?.toDomain(),
    isPinned = this.isPinned
)

fun TdApi.PushReceiverId.toDomain(): PushReceiverId = PushReceiverId(
    id = this.id
)

