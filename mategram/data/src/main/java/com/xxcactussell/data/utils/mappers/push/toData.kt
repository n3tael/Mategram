package com.xxcactussell.data.utils.mappers.push

import com.xxcactussell.data.utils.mappers.animation.toData
import com.xxcactussell.data.utils.mappers.file.toData
import com.xxcactussell.data.utils.mappers.giveaway.toData
import com.xxcactussell.data.utils.mappers.photo.toData
import com.xxcactussell.data.utils.mappers.sticker.toData
import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: Domain -> TdApi

fun PushMessageContent.toData(): TdApi.PushMessageContent = when(this) {
    is PushMessageContentHidden -> this.toData()
    is PushMessageContentAnimation -> this.toData()
    is PushMessageContentAudio -> this.toData()
    is PushMessageContentContact -> this.toData()
    is PushMessageContentContactRegistered -> this.toData()
    is PushMessageContentDocument -> this.toData()
    is PushMessageContentGame -> this.toData()
    is PushMessageContentGameScore -> this.toData()
    is PushMessageContentInvoice -> this.toData()
    is PushMessageContentLocation -> this.toData()
    is PushMessageContentPaidMedia -> this.toData()
    is PushMessageContentPhoto -> this.toData()
    is PushMessageContentPoll -> this.toData()
    is PushMessageContentPremiumGiftCode -> this.toData()
    is PushMessageContentGiveaway -> this.toData()
    is PushMessageContentGift -> this.toData()
    is PushMessageContentUpgradedGift -> this.toData()
    is PushMessageContentScreenshotTaken -> this.toData()
    is PushMessageContentSticker -> this.toData()
    is PushMessageContentStory -> this.toData()
    is PushMessageContentText -> this.toData()
    is PushMessageContentChecklist -> this.toData()
    is PushMessageContentVideo -> this.toData()
    is PushMessageContentVideoNote -> this.toData()
    is PushMessageContentVoiceNote -> this.toData()
    is PushMessageContentBasicGroupChatCreate -> this.toData()
    is PushMessageContentVideoChatStarted -> this.toData()
    is PushMessageContentVideoChatEnded -> this.toData()
    is PushMessageContentInviteVideoChatParticipants -> this.toData()
    is PushMessageContentChatAddMembers -> this.toData()
    is PushMessageContentChatChangePhoto -> this.toData()
    is PushMessageContentChatChangeTitle -> this.toData()
    is PushMessageContentChatSetBackground -> this.toData()
    is PushMessageContentChatSetTheme -> this.toData()
    is PushMessageContentChatDeleteMember -> this.toData()
    is PushMessageContentChatJoinByLink -> this.toData()
    is PushMessageContentChatJoinByRequest -> this.toData()
    is PushMessageContentRecurringPayment -> this.toData()
    is PushMessageContentSuggestProfilePhoto -> this.toData()
    is PushMessageContentProximityAlertTriggered -> this.toData()
    is PushMessageContentChecklistTasksAdded -> this.toData()
    is PushMessageContentChecklistTasksDone -> this.toData()
    is PushMessageContentMessageForwards -> this.toData()
    is PushMessageContentMediaAlbum -> this.toData()
}

fun PushMessageContentAnimation.toData(): TdApi.PushMessageContentAnimation = TdApi.PushMessageContentAnimation(
    this.animation?.toData(),
    this.caption,
    this.isPinned
)

fun PushMessageContentAudio.toData(): TdApi.PushMessageContentAudio = TdApi.PushMessageContentAudio(
    this.audio?.toData(),
    this.isPinned
)

fun PushMessageContentBasicGroupChatCreate.toData(): TdApi.PushMessageContentBasicGroupChatCreate = TdApi.PushMessageContentBasicGroupChatCreate(
)

fun PushMessageContentChatAddMembers.toData(): TdApi.PushMessageContentChatAddMembers = TdApi.PushMessageContentChatAddMembers(
    this.memberName,
    this.isCurrentUser,
    this.isReturned
)

fun PushMessageContentChatChangePhoto.toData(): TdApi.PushMessageContentChatChangePhoto = TdApi.PushMessageContentChatChangePhoto(
)

fun PushMessageContentChatChangeTitle.toData(): TdApi.PushMessageContentChatChangeTitle = TdApi.PushMessageContentChatChangeTitle(
    this.title
)

fun PushMessageContentChatDeleteMember.toData(): TdApi.PushMessageContentChatDeleteMember = TdApi.PushMessageContentChatDeleteMember(
    this.memberName,
    this.isCurrentUser,
    this.isLeft
)

fun PushMessageContentChatJoinByLink.toData(): TdApi.PushMessageContentChatJoinByLink = TdApi.PushMessageContentChatJoinByLink(
)

fun PushMessageContentChatJoinByRequest.toData(): TdApi.PushMessageContentChatJoinByRequest = TdApi.PushMessageContentChatJoinByRequest(
)

fun PushMessageContentChatSetBackground.toData(): TdApi.PushMessageContentChatSetBackground = TdApi.PushMessageContentChatSetBackground(
    this.isSame
)

fun PushMessageContentChatSetTheme.toData(): TdApi.PushMessageContentChatSetTheme = TdApi.PushMessageContentChatSetTheme(
    this.name
)

fun PushMessageContentChecklist.toData(): TdApi.PushMessageContentChecklist = TdApi.PushMessageContentChecklist(
    this.title,
    this.isPinned
)

fun PushMessageContentChecklistTasksAdded.toData(): TdApi.PushMessageContentChecklistTasksAdded = TdApi.PushMessageContentChecklistTasksAdded(
    this.taskCount
)

fun PushMessageContentChecklistTasksDone.toData(): TdApi.PushMessageContentChecklistTasksDone = TdApi.PushMessageContentChecklistTasksDone(
    this.taskCount
)

fun PushMessageContentContact.toData(): TdApi.PushMessageContentContact = TdApi.PushMessageContentContact(
    this.name,
    this.isPinned
)

fun PushMessageContentContactRegistered.toData(): TdApi.PushMessageContentContactRegistered = TdApi.PushMessageContentContactRegistered(
    this.asPremiumAccount
)

fun PushMessageContentDocument.toData(): TdApi.PushMessageContentDocument = TdApi.PushMessageContentDocument(
    this.document?.toData(),
    this.isPinned
)

fun PushMessageContentGame.toData(): TdApi.PushMessageContentGame = TdApi.PushMessageContentGame(
    this.title,
    this.isPinned
)

fun PushMessageContentGameScore.toData(): TdApi.PushMessageContentGameScore = TdApi.PushMessageContentGameScore(
    this.title,
    this.score,
    this.isPinned
)

fun PushMessageContentGift.toData(): TdApi.PushMessageContentGift = TdApi.PushMessageContentGift(
    this.starCount,
    this.isPrepaidUpgrade
)

fun PushMessageContentGiveaway.toData(): TdApi.PushMessageContentGiveaway = TdApi.PushMessageContentGiveaway(
    this.winnerCount,
    this.prize?.toData(),
    this.isPinned
)

fun PushMessageContentHidden.toData(): TdApi.PushMessageContentHidden = TdApi.PushMessageContentHidden(
    this.isPinned
)

fun PushMessageContentInviteVideoChatParticipants.toData(): TdApi.PushMessageContentInviteVideoChatParticipants = TdApi.PushMessageContentInviteVideoChatParticipants(
    this.isCurrentUser
)

fun PushMessageContentInvoice.toData(): TdApi.PushMessageContentInvoice = TdApi.PushMessageContentInvoice(
    this.price,
    this.isPinned
)

fun PushMessageContentLocation.toData(): TdApi.PushMessageContentLocation = TdApi.PushMessageContentLocation(
    this.isLive,
    this.isPinned
)

fun PushMessageContentMediaAlbum.toData(): TdApi.PushMessageContentMediaAlbum = TdApi.PushMessageContentMediaAlbum(
    this.totalCount,
    this.hasPhotos,
    this.hasVideos,
    this.hasAudios,
    this.hasDocuments
)

fun PushMessageContentMessageForwards.toData(): TdApi.PushMessageContentMessageForwards = TdApi.PushMessageContentMessageForwards(
    this.totalCount
)

fun PushMessageContentPaidMedia.toData(): TdApi.PushMessageContentPaidMedia = TdApi.PushMessageContentPaidMedia(
    this.starCount,
    this.isPinned
)

fun PushMessageContentPhoto.toData(): TdApi.PushMessageContentPhoto = TdApi.PushMessageContentPhoto(
    this.photo?.toData(),
    this.caption,
    this.isSecret,
    this.isPinned
)

fun PushMessageContentPoll.toData(): TdApi.PushMessageContentPoll = TdApi.PushMessageContentPoll(
    this.question,
    this.isRegular,
    this.isPinned
)

fun PushMessageContentPremiumGiftCode.toData(): TdApi.PushMessageContentPremiumGiftCode = TdApi.PushMessageContentPremiumGiftCode(
    this.monthCount
)

fun PushMessageContentProximityAlertTriggered.toData(): TdApi.PushMessageContentProximityAlertTriggered = TdApi.PushMessageContentProximityAlertTriggered(
    this.distance
)

fun PushMessageContentRecurringPayment.toData(): TdApi.PushMessageContentRecurringPayment = TdApi.PushMessageContentRecurringPayment(
    this.amount
)

fun PushMessageContentScreenshotTaken.toData(): TdApi.PushMessageContentScreenshotTaken = TdApi.PushMessageContentScreenshotTaken(
)

fun PushMessageContentSticker.toData(): TdApi.PushMessageContentSticker = TdApi.PushMessageContentSticker(
    this.sticker?.toData(),
    this.emoji,
    this.isPinned
)

fun PushMessageContentStory.toData(): TdApi.PushMessageContentStory = TdApi.PushMessageContentStory(
    this.isMention,
    this.isPinned
)

fun PushMessageContentSuggestProfilePhoto.toData(): TdApi.PushMessageContentSuggestProfilePhoto = TdApi.PushMessageContentSuggestProfilePhoto(
)

fun PushMessageContentText.toData(): TdApi.PushMessageContentText = TdApi.PushMessageContentText(
    this.text,
    this.isPinned
)

fun PushMessageContentUpgradedGift.toData(): TdApi.PushMessageContentUpgradedGift = TdApi.PushMessageContentUpgradedGift(
    this.isUpgrade,
    this.isPrepaidUpgrade
)

fun PushMessageContentVideo.toData(): TdApi.PushMessageContentVideo = TdApi.PushMessageContentVideo(
    this.video?.toData(),
    this.caption,
    this.isSecret,
    this.isPinned
)

fun PushMessageContentVideoChatEnded.toData(): TdApi.PushMessageContentVideoChatEnded = TdApi.PushMessageContentVideoChatEnded(
)

fun PushMessageContentVideoChatStarted.toData(): TdApi.PushMessageContentVideoChatStarted = TdApi.PushMessageContentVideoChatStarted(
)

fun PushMessageContentVideoNote.toData(): TdApi.PushMessageContentVideoNote = TdApi.PushMessageContentVideoNote(
    this.videoNote?.toData(),
    this.isPinned
)

fun PushMessageContentVoiceNote.toData(): TdApi.PushMessageContentVoiceNote = TdApi.PushMessageContentVoiceNote(
    this.voiceNote?.toData(),
    this.isPinned
)

fun PushReceiverId.toData(): TdApi.PushReceiverId = TdApi.PushReceiverId(
    this.id
)

