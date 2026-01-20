package com.xxcactussell.data.utils.mappers.input

import com.xxcactussell.data.utils.mappers.address.toData
import com.xxcactussell.data.utils.mappers.bots.toData
import com.xxcactussell.data.utils.mappers.chat.toData
import com.xxcactussell.data.utils.mappers.date.toData
import com.xxcactussell.data.utils.mappers.formatted.toData
import com.xxcactussell.data.utils.mappers.link.toData
import com.xxcactussell.data.utils.mappers.location.toData
import com.xxcactussell.data.utils.mappers.mask.toData
import com.xxcactussell.data.utils.mappers.message.toData
import com.xxcactussell.data.utils.mappers.monetization.toData
import com.xxcactussell.data.utils.mappers.passport.toData
import com.xxcactussell.data.utils.mappers.personal.toData
import com.xxcactussell.data.utils.mappers.poll.toData
import com.xxcactussell.data.utils.mappers.sticker.toData
import com.xxcactussell.data.utils.mappers.suggested.toData
import com.xxcactussell.data.utils.mappers.user.toData
import com.xxcactussell.data.utils.mappers.venue.toData
import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: Domain -> TdApi

fun InputBackground.toData(): TdApi.InputBackground = when(this) {
    is InputBackgroundLocal -> this.toData()
    is InputBackgroundRemote -> this.toData()
    is InputBackgroundPrevious -> this.toData()
}

fun InputBackgroundLocal.toData(): TdApi.InputBackgroundLocal = TdApi.InputBackgroundLocal(
    this.background.toData()
)

fun InputBackgroundPrevious.toData(): TdApi.InputBackgroundPrevious = TdApi.InputBackgroundPrevious(
    this.messageId
)

fun InputBackgroundRemote.toData(): TdApi.InputBackgroundRemote = TdApi.InputBackgroundRemote(
    this.backgroundId
)

fun InputBusinessChatLink.toData(): TdApi.InputBusinessChatLink = TdApi.InputBusinessChatLink(
    this.text.toData(),
    this.title
)

fun InputBusinessStartPage.toData(): TdApi.InputBusinessStartPage = TdApi.InputBusinessStartPage(
    this.title,
    this.message,
    this.sticker.toData()
)

fun InputChatPhoto.toData(): TdApi.InputChatPhoto = when(this) {
    is InputChatPhotoPrevious -> this.toData()
    is InputChatPhotoStatic -> this.toData()
    is InputChatPhotoAnimation -> this.toData()
    is InputChatPhotoSticker -> this.toData()
}

fun InputChatPhotoAnimation.toData(): TdApi.InputChatPhotoAnimation = TdApi.InputChatPhotoAnimation(
    this.animation.toData(),
    this.mainFrameTimestamp
)

fun InputChatPhotoPrevious.toData(): TdApi.InputChatPhotoPrevious = TdApi.InputChatPhotoPrevious(
    this.chatPhotoId
)

fun InputChatPhotoStatic.toData(): TdApi.InputChatPhotoStatic = TdApi.InputChatPhotoStatic(
    this.photo.toData()
)

fun InputChatPhotoSticker.toData(): TdApi.InputChatPhotoSticker = TdApi.InputChatPhotoSticker(
    this.sticker.toData()
)

fun InputChatTheme.toData(): TdApi.InputChatTheme = when(this) {
    is InputChatThemeEmoji -> this.toData()
    is InputChatThemeGift -> this.toData()
}

fun InputChatThemeEmoji.toData(): TdApi.InputChatThemeEmoji = TdApi.InputChatThemeEmoji(
    this.name
)

fun InputChatThemeGift.toData(): TdApi.InputChatThemeGift = TdApi.InputChatThemeGift(
    this.name
)

fun InputChecklist.toData(): TdApi.InputChecklist = TdApi.InputChecklist(
    this.title.toData(),
    this.tasks.map { it.toData() }.toTypedArray(),
    this.othersCanAddTasks,
    this.othersCanMarkTasksAsDone
)

fun InputChecklistTask.toData(): TdApi.InputChecklistTask = TdApi.InputChecklistTask(
    this.id,
    this.text.toData()
)

fun InputCredentials.toData(): TdApi.InputCredentials = when(this) {
    is InputCredentialsSaved -> this.toData()
    is InputCredentialsNew -> this.toData()
    is InputCredentialsApplePay -> this.toData()
    is InputCredentialsGooglePay -> this.toData()
}

fun InputCredentialsApplePay.toData(): TdApi.InputCredentialsApplePay = TdApi.InputCredentialsApplePay(
    this.data
)

fun InputCredentialsGooglePay.toData(): TdApi.InputCredentialsGooglePay = TdApi.InputCredentialsGooglePay(
    this.data
)

fun InputCredentialsNew.toData(): TdApi.InputCredentialsNew = TdApi.InputCredentialsNew(
    this.data,
    this.allowSave
)

fun InputCredentialsSaved.toData(): TdApi.InputCredentialsSaved = TdApi.InputCredentialsSaved(
    this.savedCredentialsId
)

fun InputFile.toData(): TdApi.InputFile = when(this) {
    is InputFileId -> this.toData()
    is InputFileRemote -> this.toData()
    is InputFileLocal -> this.toData()
    is InputFileGenerated -> this.toData()
}

fun InputFileGenerated.toData(): TdApi.InputFileGenerated = TdApi.InputFileGenerated(
    this.originalPath,
    this.conversion,
    this.expectedSize
)

fun InputFileId.toData(): TdApi.InputFileId = TdApi.InputFileId(
    this.id
)

fun InputFileLocal.toData(): TdApi.InputFileLocal = TdApi.InputFileLocal(
    this.path
)

fun InputFileRemote.toData(): TdApi.InputFileRemote = TdApi.InputFileRemote(
    this.id
)

fun InputGroupCall.toData(): TdApi.InputGroupCall = when(this) {
    is InputGroupCallLink -> this.toData()
    is InputGroupCallMessage -> this.toData()
}

fun InputGroupCallLink.toData(): TdApi.InputGroupCallLink = TdApi.InputGroupCallLink(
    this.link
)

fun InputGroupCallMessage.toData(): TdApi.InputGroupCallMessage = TdApi.InputGroupCallMessage(
    this.chatId,
    this.messageId
)

fun InputIdentityDocument.toData(): TdApi.InputIdentityDocument = TdApi.InputIdentityDocument(
    this.number,
    this.expirationDate.toData(),
    this.frontSide.toData(),
    this.reverseSide.toData(),
    this.selfie.toData(),
    this.translation.map { it.toData() }.toTypedArray()
)

fun InputInlineQueryResult.toData(): TdApi.InputInlineQueryResult = when(this) {
    is InputInlineQueryResultAnimation -> this.toData()
    is InputInlineQueryResultArticle -> this.toData()
    is InputInlineQueryResultAudio -> this.toData()
    is InputInlineQueryResultContact -> this.toData()
    is InputInlineQueryResultDocument -> this.toData()
    is InputInlineQueryResultGame -> this.toData()
    is InputInlineQueryResultLocation -> this.toData()
    is InputInlineQueryResultPhoto -> this.toData()
    is InputInlineQueryResultSticker -> this.toData()
    is InputInlineQueryResultVenue -> this.toData()
    is InputInlineQueryResultVideo -> this.toData()
    is InputInlineQueryResultVoiceNote -> this.toData()
}

fun InputInlineQueryResultAnimation.toData(): TdApi.InputInlineQueryResultAnimation = TdApi.InputInlineQueryResultAnimation(
    this.id,
    this.title,
    this.thumbnailUrl,
    this.thumbnailMimeType,
    this.videoUrl,
    this.videoMimeType,
    this.videoDuration,
    this.videoWidth,
    this.videoHeight,
    this.replyMarkup.toData(),
    this.inputMessageContent.toData()
)

fun InputInlineQueryResultArticle.toData(): TdApi.InputInlineQueryResultArticle = TdApi.InputInlineQueryResultArticle(
    this.id,
    this.url,
    this.title,
    this.description,
    this.thumbnailUrl,
    this.thumbnailWidth,
    this.thumbnailHeight,
    this.replyMarkup.toData(),
    this.inputMessageContent.toData()
)

fun InputInlineQueryResultAudio.toData(): TdApi.InputInlineQueryResultAudio = TdApi.InputInlineQueryResultAudio(
    this.id,
    this.title,
    this.performer,
    this.audioUrl,
    this.audioDuration,
    this.replyMarkup.toData(),
    this.inputMessageContent.toData()
)

fun InputInlineQueryResultContact.toData(): TdApi.InputInlineQueryResultContact = TdApi.InputInlineQueryResultContact(
    this.id,
    this.contact.toData(),
    this.thumbnailUrl,
    this.thumbnailWidth,
    this.thumbnailHeight,
    this.replyMarkup.toData(),
    this.inputMessageContent.toData()
)

fun InputInlineQueryResultDocument.toData(): TdApi.InputInlineQueryResultDocument = TdApi.InputInlineQueryResultDocument(
    this.id,
    this.title,
    this.description,
    this.documentUrl,
    this.mimeType,
    this.thumbnailUrl,
    this.thumbnailWidth,
    this.thumbnailHeight,
    this.replyMarkup.toData(),
    this.inputMessageContent.toData()
)

fun InputInlineQueryResultGame.toData(): TdApi.InputInlineQueryResultGame = TdApi.InputInlineQueryResultGame(
    this.id,
    this.gameShortName,
    this.replyMarkup.toData()
)

fun InputInlineQueryResultLocation.toData(): TdApi.InputInlineQueryResultLocation = TdApi.InputInlineQueryResultLocation(
    this.id,
    this.location.toData(),
    this.livePeriod,
    this.title,
    this.thumbnailUrl,
    this.thumbnailWidth,
    this.thumbnailHeight,
    this.replyMarkup.toData(),
    this.inputMessageContent.toData()
)

fun InputInlineQueryResultPhoto.toData(): TdApi.InputInlineQueryResultPhoto = TdApi.InputInlineQueryResultPhoto(
    this.id,
    this.title,
    this.description,
    this.thumbnailUrl,
    this.photoUrl,
    this.photoWidth,
    this.photoHeight,
    this.replyMarkup.toData(),
    this.inputMessageContent.toData()
)

fun InputInlineQueryResultSticker.toData(): TdApi.InputInlineQueryResultSticker = TdApi.InputInlineQueryResultSticker(
    this.id,
    this.thumbnailUrl,
    this.stickerUrl,
    this.stickerWidth,
    this.stickerHeight,
    this.replyMarkup.toData(),
    this.inputMessageContent.toData()
)

fun InputInlineQueryResultVenue.toData(): TdApi.InputInlineQueryResultVenue = TdApi.InputInlineQueryResultVenue(
    this.id,
    this.venue.toData(),
    this.thumbnailUrl,
    this.thumbnailWidth,
    this.thumbnailHeight,
    this.replyMarkup.toData(),
    this.inputMessageContent.toData()
)

fun InputInlineQueryResultVideo.toData(): TdApi.InputInlineQueryResultVideo = TdApi.InputInlineQueryResultVideo(
    this.id,
    this.title,
    this.description,
    this.thumbnailUrl,
    this.videoUrl,
    this.mimeType,
    this.videoWidth,
    this.videoHeight,
    this.videoDuration,
    this.replyMarkup.toData(),
    this.inputMessageContent.toData()
)

fun InputInlineQueryResultVoiceNote.toData(): TdApi.InputInlineQueryResultVoiceNote = TdApi.InputInlineQueryResultVoiceNote(
    this.id,
    this.title,
    this.voiceNoteUrl,
    this.voiceNoteDuration,
    this.replyMarkup.toData(),
    this.inputMessageContent.toData()
)

fun InputMessageAnimation.toData(): TdApi.InputMessageAnimation = TdApi.InputMessageAnimation(
    this.animation.toData(),
    this.thumbnail.toData(),
    this.addedStickerFileIds,
    this.duration,
    this.width,
    this.height,
    this.caption.toData(),
    this.showCaptionAboveMedia,
    this.hasSpoiler
)

fun InputMessageAudio.toData(): TdApi.InputMessageAudio = TdApi.InputMessageAudio(
    this.audio.toData(),
    this.albumCoverThumbnail.toData(),
    this.duration,
    this.title,
    this.performer,
    this.caption.toData()
)

fun InputMessageChecklist.toData(): TdApi.InputMessageChecklist = TdApi.InputMessageChecklist(
    this.checklist.toData()
)

fun InputMessageContact.toData(): TdApi.InputMessageContact = TdApi.InputMessageContact(
    this.contact.toData()
)

fun InputMessageContent.toData(): TdApi.InputMessageContent = when(this) {
    is InputMessageText -> this.toData()
    is InputMessageAnimation -> this.toData()
    is InputMessageAudio -> this.toData()
    is InputMessageDocument -> this.toData()
    is InputMessagePaidMedia -> this.toData()
    is InputMessagePhoto -> this.toData()
    is InputMessageSticker -> this.toData()
    is InputMessageVideo -> this.toData()
    is InputMessageVideoNote -> this.toData()
    is InputMessageVoiceNote -> this.toData()
    is InputMessageLocation -> this.toData()
    is InputMessageVenue -> this.toData()
    is InputMessageContact -> this.toData()
    is InputMessageDice -> this.toData()
    is InputMessageGame -> this.toData()
    is InputMessageInvoice -> this.toData()
    is InputMessagePoll -> this.toData()
    is InputMessageStory -> this.toData()
    is InputMessageChecklist -> this.toData()
    is InputMessageForwarded -> this.toData()
}

fun InputMessageDice.toData(): TdApi.InputMessageDice = TdApi.InputMessageDice(
    this.emoji,
    this.clearDraft
)

fun InputMessageDocument.toData(): TdApi.InputMessageDocument = TdApi.InputMessageDocument(
    this.document.toData(),
    this.thumbnail.toData(),
    this.disableContentTypeDetection,
    this.caption.toData()
)

fun InputMessageForwarded.toData(): TdApi.InputMessageForwarded = TdApi.InputMessageForwarded(
    this.fromChatId,
    this.messageId,
    this.inGameShare,
    this.replaceVideoStartTimestamp,
    this.newVideoStartTimestamp,
    this.copyOptions.toData()
)

fun InputMessageGame.toData(): TdApi.InputMessageGame = TdApi.InputMessageGame(
    this.botUserId,
    this.gameShortName
)

fun InputMessageInvoice.toData(): TdApi.InputMessageInvoice = TdApi.InputMessageInvoice(
    this.invoice.toData(),
    this.title,
    this.description,
    this.photoUrl,
    this.photoSize,
    this.photoWidth,
    this.photoHeight,
    this.payload,
    this.providerToken,
    this.providerData,
    this.startParameter,
    this.paidMedia.toData(),
    this.paidMediaCaption.toData()
)

fun InputMessageLocation.toData(): TdApi.InputMessageLocation = TdApi.InputMessageLocation(
    this.location.toData(),
    this.livePeriod,
    this.heading,
    this.proximityAlertRadius
)

fun InputMessagePaidMedia.toData(): TdApi.InputMessagePaidMedia = TdApi.InputMessagePaidMedia(
    this.starCount,
    this.paidMedia.map { it.toData() }.toTypedArray(),
    this.caption.toData(),
    this.showCaptionAboveMedia,
    this.payload
)

fun InputMessagePhoto.toData(): TdApi.InputMessagePhoto = TdApi.InputMessagePhoto(
    this.photo.toData(),
    this.thumbnail.toData(),
    this.addedStickerFileIds,
    this.width,
    this.height,
    this.caption.toData(),
    this.showCaptionAboveMedia,
    this.selfDestructType?.toData(),
    this.hasSpoiler
)

fun InputMessagePoll.toData(): TdApi.InputMessagePoll = TdApi.InputMessagePoll(
    this.question.toData(),
    this.options.map { it.toData() }.toTypedArray(),
    this.isAnonymous,
    this.type.toData(),
    this.openPeriod,
    this.closeDate,
    this.isClosed
)

fun InputMessageReplyTo.toData(): TdApi.InputMessageReplyTo = when(this) {
    is InputMessageReplyToMessage -> this.toData()
    is InputMessageReplyToExternalMessage -> this.toData()
    is InputMessageReplyToStory -> this.toData()
}

fun InputMessageReplyToExternalMessage.toData(): TdApi.InputMessageReplyToExternalMessage = TdApi.InputMessageReplyToExternalMessage(
    this.chatId,
    this.messageId,
    this.quote.toData(),
    this.checklistTaskId
)

fun InputMessageReplyToMessage.toData(): TdApi.InputMessageReplyToMessage = TdApi.InputMessageReplyToMessage(
    this.messageId,
    this.quote.toData(),
    this.checklistTaskId
)

fun InputMessageReplyToStory.toData(): TdApi.InputMessageReplyToStory = TdApi.InputMessageReplyToStory(
    this.storyPosterChatId,
    this.storyId
)

fun InputMessageSticker.toData(): TdApi.InputMessageSticker = TdApi.InputMessageSticker(
    this.sticker.toData(),
    this.thumbnail.toData(),
    this.width,
    this.height,
    this.emoji
)

fun InputMessageStory.toData(): TdApi.InputMessageStory = TdApi.InputMessageStory(
    this.storyPosterChatId,
    this.storyId
)

fun InputMessageText.toData(): TdApi.InputMessageText = TdApi.InputMessageText(
    this.text.toData(),
    this.linkPreviewOptions?.toData(),
    this.clearDraft
)

fun InputMessageVenue.toData(): TdApi.InputMessageVenue = TdApi.InputMessageVenue(
    this.venue.toData()
)

fun InputMessageVideo.toData(): TdApi.InputMessageVideo = TdApi.InputMessageVideo(
    this.video.toData(),
    this.thumbnail.toData(),
    this.cover.toData(),
    this.startTimestamp,
    this.addedStickerFileIds,
    this.duration,
    this.width,
    this.height,
    this.supportsStreaming,
    this.caption.toData(),
    this.showCaptionAboveMedia,
    this.selfDestructType?.toData(),
    this.hasSpoiler
)

fun InputMessageVideoNote.toData(): TdApi.InputMessageVideoNote = TdApi.InputMessageVideoNote(
    this.videoNote.toData(),
    this.thumbnail?.toData(),
    this.duration,
    this.length,
    this.selfDestructType?.toData()
)

fun InputMessageVoiceNote.toData(): TdApi.InputMessageVoiceNote = TdApi.InputMessageVoiceNote(
    this.voiceNote.toData(),
    this.duration,
    this.waveform,
    this.caption?.toData(),
    this.selfDestructType?.toData()
)

fun InputPaidMedia.toData(): TdApi.InputPaidMedia = TdApi.InputPaidMedia(
    this.type.toData(),
    this.media.toData(),
    this.thumbnail.toData(),
    this.addedStickerFileIds,
    this.width,
    this.height
)

fun InputPaidMediaType.toData(): TdApi.InputPaidMediaType = when(this) {
    is InputPaidMediaTypePhoto -> this.toData()
    is InputPaidMediaTypeVideo -> this.toData()
}

fun InputPaidMediaTypePhoto.toData(): TdApi.InputPaidMediaTypePhoto = TdApi.InputPaidMediaTypePhoto(
)

fun InputPaidMediaTypeVideo.toData(): TdApi.InputPaidMediaTypeVideo = TdApi.InputPaidMediaTypeVideo(
    this.cover.toData(),
    this.startTimestamp,
    this.duration,
    this.supportsStreaming
)

fun InputPassportElement.toData(): TdApi.InputPassportElement = when(this) {
    is InputPassportElementPersonalDetails -> this.toData()
    is InputPassportElementPassport -> this.toData()
    is InputPassportElementDriverLicense -> this.toData()
    is InputPassportElementIdentityCard -> this.toData()
    is InputPassportElementInternalPassport -> this.toData()
    is InputPassportElementAddress -> this.toData()
    is InputPassportElementUtilityBill -> this.toData()
    is InputPassportElementBankStatement -> this.toData()
    is InputPassportElementRentalAgreement -> this.toData()
    is InputPassportElementPassportRegistration -> this.toData()
    is InputPassportElementTemporaryRegistration -> this.toData()
    is InputPassportElementPhoneNumber -> this.toData()
    is InputPassportElementEmailAddress -> this.toData()
}

fun InputPassportElementAddress.toData(): TdApi.InputPassportElementAddress = TdApi.InputPassportElementAddress(
    this.address.toData()
)

fun InputPassportElementBankStatement.toData(): TdApi.InputPassportElementBankStatement = TdApi.InputPassportElementBankStatement(
    this.bankStatement.toData()
)

fun InputPassportElementDriverLicense.toData(): TdApi.InputPassportElementDriverLicense = TdApi.InputPassportElementDriverLicense(
    this.driverLicense.toData()
)

fun InputPassportElementEmailAddress.toData(): TdApi.InputPassportElementEmailAddress = TdApi.InputPassportElementEmailAddress(
    this.emailAddress
)

fun InputPassportElementError.toData(): TdApi.InputPassportElementError = TdApi.InputPassportElementError(
    this.type.toData(),
    this.message,
    this.source.toData()
)

fun InputPassportElementErrorSource.toData(): TdApi.InputPassportElementErrorSource = when(this) {
    is InputPassportElementErrorSourceUnspecified -> this.toData()
    is InputPassportElementErrorSourceDataField -> this.toData()
    is InputPassportElementErrorSourceFrontSide -> this.toData()
    is InputPassportElementErrorSourceReverseSide -> this.toData()
    is InputPassportElementErrorSourceSelfie -> this.toData()
    is InputPassportElementErrorSourceTranslationFile -> this.toData()
    is InputPassportElementErrorSourceTranslationFiles -> this.toData()
    is InputPassportElementErrorSourceFile -> this.toData()
    is InputPassportElementErrorSourceFiles -> this.toData()
}

fun InputPassportElementErrorSourceDataField.toData(): TdApi.InputPassportElementErrorSourceDataField = TdApi.InputPassportElementErrorSourceDataField(
    this.fieldName,
    this.dataHash
)

fun InputPassportElementErrorSourceFile.toData(): TdApi.InputPassportElementErrorSourceFile = TdApi.InputPassportElementErrorSourceFile(
    this.fileHash
)

fun InputPassportElementErrorSourceFiles.toData(): TdApi.InputPassportElementErrorSourceFiles = TdApi.InputPassportElementErrorSourceFiles(
    this.fileHashes.toTypedArray()
)

fun InputPassportElementErrorSourceFrontSide.toData(): TdApi.InputPassportElementErrorSourceFrontSide = TdApi.InputPassportElementErrorSourceFrontSide(
    this.fileHash
)

fun InputPassportElementErrorSourceReverseSide.toData(): TdApi.InputPassportElementErrorSourceReverseSide = TdApi.InputPassportElementErrorSourceReverseSide(
    this.fileHash
)

fun InputPassportElementErrorSourceSelfie.toData(): TdApi.InputPassportElementErrorSourceSelfie = TdApi.InputPassportElementErrorSourceSelfie(
    this.fileHash
)

fun InputPassportElementErrorSourceTranslationFile.toData(): TdApi.InputPassportElementErrorSourceTranslationFile = TdApi.InputPassportElementErrorSourceTranslationFile(
    this.fileHash
)

fun InputPassportElementErrorSourceTranslationFiles.toData(): TdApi.InputPassportElementErrorSourceTranslationFiles = TdApi.InputPassportElementErrorSourceTranslationFiles(
    this.fileHashes.toTypedArray()
)

fun InputPassportElementErrorSourceUnspecified.toData(): TdApi.InputPassportElementErrorSourceUnspecified = TdApi.InputPassportElementErrorSourceUnspecified(
    this.elementHash
)

fun InputPassportElementIdentityCard.toData(): TdApi.InputPassportElementIdentityCard = TdApi.InputPassportElementIdentityCard(
    this.identityCard.toData()
)

fun InputPassportElementInternalPassport.toData(): TdApi.InputPassportElementInternalPassport = TdApi.InputPassportElementInternalPassport(
    this.internalPassport.toData()
)

fun InputPassportElementPassport.toData(): TdApi.InputPassportElementPassport = TdApi.InputPassportElementPassport(
    this.passport.toData()
)

fun InputPassportElementPassportRegistration.toData(): TdApi.InputPassportElementPassportRegistration = TdApi.InputPassportElementPassportRegistration(
    this.passportRegistration.toData()
)

fun InputPassportElementPersonalDetails.toData(): TdApi.InputPassportElementPersonalDetails = TdApi.InputPassportElementPersonalDetails(
    this.personalDetails.toData()
)

fun InputPassportElementPhoneNumber.toData(): TdApi.InputPassportElementPhoneNumber = TdApi.InputPassportElementPhoneNumber(
    this.phoneNumber
)

fun InputPassportElementRentalAgreement.toData(): TdApi.InputPassportElementRentalAgreement = TdApi.InputPassportElementRentalAgreement(
    this.rentalAgreement.toData()
)

fun InputPassportElementTemporaryRegistration.toData(): TdApi.InputPassportElementTemporaryRegistration = TdApi.InputPassportElementTemporaryRegistration(
    this.temporaryRegistration.toData()
)

fun InputPassportElementUtilityBill.toData(): TdApi.InputPassportElementUtilityBill = TdApi.InputPassportElementUtilityBill(
    this.utilityBill.toData()
)

fun InputPersonalDocument.toData(): TdApi.InputPersonalDocument = TdApi.InputPersonalDocument(
    this.files.map { it.toData() }.toTypedArray(),
    this.translation.map { it.toData() }.toTypedArray()
)

fun InputSticker.toData(): TdApi.InputSticker = TdApi.InputSticker(
    this.sticker.toData(),
    this.format.toData(),
    this.emojis,
    this.maskPosition.toData(),
    this.keywords.toTypedArray()
)

fun InputStoryContent.toData(): TdApi.InputStoryContent = when(this) {
    is InputStoryContentPhoto -> this.toData()
    is InputStoryContentVideo -> this.toData()
}

fun InputStoryContentPhoto.toData(): TdApi.InputStoryContentPhoto = TdApi.InputStoryContentPhoto(
    this.photo.toData(),
    this.addedStickerFileIds
)

fun InputStoryContentVideo.toData(): TdApi.InputStoryContentVideo = TdApi.InputStoryContentVideo(
    this.video.toData(),
    this.addedStickerFileIds,
    this.duration,
    this.coverFrameTimestamp,
    this.isAnimation
)

fun InputSuggestedPostInfo.toData(): TdApi.InputSuggestedPostInfo = TdApi.InputSuggestedPostInfo(
    this.price.toData(),
    this.sendDate
)

fun InputTextQuote.toData(): TdApi.InputTextQuote = TdApi.InputTextQuote(
    this.text.toData(),
    this.position
)

fun InputThumbnail.toData(): TdApi.InputThumbnail = TdApi.InputThumbnail(
    this.thumbnail.toData(),
    this.width,
    this.height
)

