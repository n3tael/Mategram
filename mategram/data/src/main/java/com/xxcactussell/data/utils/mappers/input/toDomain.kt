package com.xxcactussell.data.utils.mappers.input

import com.xxcactussell.data.utils.mappers.address.toDomain
import com.xxcactussell.data.utils.mappers.bots.toDomain
import com.xxcactussell.data.utils.mappers.chat.toDomain
import com.xxcactussell.data.utils.mappers.date.toDomain
import com.xxcactussell.data.utils.mappers.formatted.toDomain
import com.xxcactussell.data.utils.mappers.link.toDomain
import com.xxcactussell.data.utils.mappers.location.toDomain
import com.xxcactussell.data.utils.mappers.mask.toDomain
import com.xxcactussell.data.utils.mappers.message.toDomain
import com.xxcactussell.data.utils.mappers.monetization.toDomain
import com.xxcactussell.data.utils.mappers.passport.toDomain
import com.xxcactussell.data.utils.mappers.personal.toDomain
import com.xxcactussell.data.utils.mappers.poll.toDomain
import com.xxcactussell.data.utils.mappers.sticker.toDomain
import com.xxcactussell.data.utils.mappers.suggested.toDomain
import com.xxcactussell.data.utils.mappers.user.toDomain
import com.xxcactussell.data.utils.mappers.venue.toDomain
import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: TdApi -> Domain

fun TdApi.InputBackground.toDomain(): InputBackground = when(this) {
    is TdApi.InputBackgroundLocal -> this.toDomain()
    is TdApi.InputBackgroundRemote -> this.toDomain()
    is TdApi.InputBackgroundPrevious -> this.toDomain()
    else -> throw IllegalArgumentException("Unknown type: ${this.javaClass}")
}

fun TdApi.InputBackgroundLocal.toDomain(): InputBackgroundLocal = InputBackgroundLocal(
    background = this.background.toDomain()
)

fun TdApi.InputBackgroundPrevious.toDomain(): InputBackgroundPrevious = InputBackgroundPrevious(
    messageId = this.messageId
)

fun TdApi.InputBackgroundRemote.toDomain(): InputBackgroundRemote = InputBackgroundRemote(
    backgroundId = this.backgroundId
)

fun TdApi.InputBusinessChatLink.toDomain(): InputBusinessChatLink = InputBusinessChatLink(
    text = this.text.toDomain(),
    title = this.title
)

fun TdApi.InputBusinessStartPage.toDomain(): InputBusinessStartPage = InputBusinessStartPage(
    title = this.title,
    message = this.message,
    sticker = this.sticker.toDomain()
)

fun TdApi.InputChatPhoto.toDomain(): InputChatPhoto = when(this) {
    is TdApi.InputChatPhotoPrevious -> this.toDomain()
    is TdApi.InputChatPhotoStatic -> this.toDomain()
    is TdApi.InputChatPhotoAnimation -> this.toDomain()
    is TdApi.InputChatPhotoSticker -> this.toDomain()
    else -> throw IllegalArgumentException("Unknown type: ${this.javaClass}")
}

fun TdApi.InputChatPhotoAnimation.toDomain(): InputChatPhotoAnimation = InputChatPhotoAnimation(
    animation = this.animation.toDomain(),
    mainFrameTimestamp = this.mainFrameTimestamp
)

fun TdApi.InputChatPhotoPrevious.toDomain(): InputChatPhotoPrevious = InputChatPhotoPrevious(
    chatPhotoId = this.chatPhotoId
)

fun TdApi.InputChatPhotoStatic.toDomain(): InputChatPhotoStatic = InputChatPhotoStatic(
    photo = this.photo.toDomain()
)

fun TdApi.InputChatPhotoSticker.toDomain(): InputChatPhotoSticker = InputChatPhotoSticker(
    sticker = this.sticker.toDomain()
)

fun TdApi.InputChatTheme.toDomain(): InputChatTheme = when(this) {
    is TdApi.InputChatThemeEmoji -> this.toDomain()
    is TdApi.InputChatThemeGift -> this.toDomain()
    else -> throw IllegalArgumentException("Unknown type: ${this.javaClass}")
}

fun TdApi.InputChatThemeEmoji.toDomain(): InputChatThemeEmoji = InputChatThemeEmoji(
    name = this.name
)

fun TdApi.InputChatThemeGift.toDomain(): InputChatThemeGift = InputChatThemeGift(
    name = this.name
)

fun TdApi.InputChecklist.toDomain(): InputChecklist = InputChecklist(
    title = this.title.toDomain(),
    tasks = this.tasks.map { it.toDomain() },
    othersCanAddTasks = this.othersCanAddTasks,
    othersCanMarkTasksAsDone = this.othersCanMarkTasksAsDone
)

fun TdApi.InputChecklistTask.toDomain(): InputChecklistTask = InputChecklistTask(
    id = this.id,
    text = this.text.toDomain()
)

fun TdApi.InputCredentials.toDomain(): InputCredentials = when(this) {
    is TdApi.InputCredentialsSaved -> this.toDomain()
    is TdApi.InputCredentialsNew -> this.toDomain()
    is TdApi.InputCredentialsApplePay -> this.toDomain()
    is TdApi.InputCredentialsGooglePay -> this.toDomain()
    else -> throw IllegalArgumentException("Unknown type: ${this.javaClass}")
}

fun TdApi.InputCredentialsApplePay.toDomain(): InputCredentialsApplePay = InputCredentialsApplePay(
    data = this.data
)

fun TdApi.InputCredentialsGooglePay.toDomain(): InputCredentialsGooglePay = InputCredentialsGooglePay(
    data = this.data
)

fun TdApi.InputCredentialsNew.toDomain(): InputCredentialsNew = InputCredentialsNew(
    data = this.data,
    allowSave = this.allowSave
)

fun TdApi.InputCredentialsSaved.toDomain(): InputCredentialsSaved = InputCredentialsSaved(
    savedCredentialsId = this.savedCredentialsId
)

fun TdApi.InputFile.toDomain(): InputFile = when(this) {
    is TdApi.InputFileId -> this.toDomain()
    is TdApi.InputFileRemote -> this.toDomain()
    is TdApi.InputFileLocal -> this.toDomain()
    is TdApi.InputFileGenerated -> this.toDomain()
    else -> throw IllegalArgumentException("Unknown type: ${this.javaClass}")
}

fun TdApi.InputFileGenerated.toDomain(): InputFileGenerated = InputFileGenerated(
    originalPath = this.originalPath,
    conversion = this.conversion,
    expectedSize = this.expectedSize
)

fun TdApi.InputFileId.toDomain(): InputFileId = InputFileId(
    id = this.id
)

fun TdApi.InputFileLocal.toDomain(): InputFileLocal = InputFileLocal(
    path = this.path
)

fun TdApi.InputFileRemote.toDomain(): InputFileRemote = InputFileRemote(
    id = this.id
)

fun TdApi.InputGroupCall.toDomain(): InputGroupCall = when(this) {
    is TdApi.InputGroupCallLink -> this.toDomain()
    is TdApi.InputGroupCallMessage -> this.toDomain()
    else -> throw IllegalArgumentException("Unknown type: ${this.javaClass}")
}

fun TdApi.InputGroupCallLink.toDomain(): InputGroupCallLink = InputGroupCallLink(
    link = this.link
)

fun TdApi.InputGroupCallMessage.toDomain(): InputGroupCallMessage = InputGroupCallMessage(
    chatId = this.chatId,
    messageId = this.messageId
)

fun TdApi.InputIdentityDocument.toDomain(): InputIdentityDocument = InputIdentityDocument(
    number = this.number,
    expirationDate = this.expirationDate.toDomain(),
    frontSide = this.frontSide.toDomain(),
    reverseSide = this.reverseSide.toDomain(),
    selfie = this.selfie.toDomain(),
    translation = this.translation.map { it.toDomain() }
)

fun TdApi.InputInlineQueryResult.toDomain(): InputInlineQueryResult = when(this) {
    is TdApi.InputInlineQueryResultAnimation -> this.toDomain()
    is TdApi.InputInlineQueryResultArticle -> this.toDomain()
    is TdApi.InputInlineQueryResultAudio -> this.toDomain()
    is TdApi.InputInlineQueryResultContact -> this.toDomain()
    is TdApi.InputInlineQueryResultDocument -> this.toDomain()
    is TdApi.InputInlineQueryResultGame -> this.toDomain()
    is TdApi.InputInlineQueryResultLocation -> this.toDomain()
    is TdApi.InputInlineQueryResultPhoto -> this.toDomain()
    is TdApi.InputInlineQueryResultSticker -> this.toDomain()
    is TdApi.InputInlineQueryResultVenue -> this.toDomain()
    is TdApi.InputInlineQueryResultVideo -> this.toDomain()
    is TdApi.InputInlineQueryResultVoiceNote -> this.toDomain()
    else -> throw IllegalArgumentException("Unknown type: ${this.javaClass}")
}

fun TdApi.InputInlineQueryResultAnimation.toDomain(): InputInlineQueryResultAnimation = InputInlineQueryResultAnimation(
    id = this.id,
    title = this.title,
    thumbnailUrl = this.thumbnailUrl,
    thumbnailMimeType = this.thumbnailMimeType,
    videoUrl = this.videoUrl,
    videoMimeType = this.videoMimeType,
    videoDuration = this.videoDuration,
    videoWidth = this.videoWidth,
    videoHeight = this.videoHeight,
    replyMarkup = this.replyMarkup.toDomain(),
    inputMessageContent = this.inputMessageContent.toDomain()
)

fun TdApi.InputInlineQueryResultArticle.toDomain(): InputInlineQueryResultArticle = InputInlineQueryResultArticle(
    id = this.id,
    url = this.url,
    title = this.title,
    description = this.description,
    thumbnailUrl = this.thumbnailUrl,
    thumbnailWidth = this.thumbnailWidth,
    thumbnailHeight = this.thumbnailHeight,
    replyMarkup = this.replyMarkup.toDomain(),
    inputMessageContent = this.inputMessageContent.toDomain()
)

fun TdApi.InputInlineQueryResultAudio.toDomain(): InputInlineQueryResultAudio = InputInlineQueryResultAudio(
    id = this.id,
    title = this.title,
    performer = this.performer,
    audioUrl = this.audioUrl,
    audioDuration = this.audioDuration,
    replyMarkup = this.replyMarkup.toDomain(),
    inputMessageContent = this.inputMessageContent.toDomain()
)

fun TdApi.InputInlineQueryResultContact.toDomain(): InputInlineQueryResultContact = InputInlineQueryResultContact(
    id = this.id,
    contact = this.contact.toDomain(),
    thumbnailUrl = this.thumbnailUrl,
    thumbnailWidth = this.thumbnailWidth,
    thumbnailHeight = this.thumbnailHeight,
    replyMarkup = this.replyMarkup.toDomain(),
    inputMessageContent = this.inputMessageContent.toDomain()
)

fun TdApi.InputInlineQueryResultDocument.toDomain(): InputInlineQueryResultDocument = InputInlineQueryResultDocument(
    id = this.id,
    title = this.title,
    description = this.description,
    documentUrl = this.documentUrl,
    mimeType = this.mimeType,
    thumbnailUrl = this.thumbnailUrl,
    thumbnailWidth = this.thumbnailWidth,
    thumbnailHeight = this.thumbnailHeight,
    replyMarkup = this.replyMarkup.toDomain(),
    inputMessageContent = this.inputMessageContent.toDomain()
)

fun TdApi.InputInlineQueryResultGame.toDomain(): InputInlineQueryResultGame = InputInlineQueryResultGame(
    id = this.id,
    gameShortName = this.gameShortName,
    replyMarkup = this.replyMarkup.toDomain()
)

fun TdApi.InputInlineQueryResultLocation.toDomain(): InputInlineQueryResultLocation = InputInlineQueryResultLocation(
    id = this.id,
    location = this.location.toDomain(),
    livePeriod = this.livePeriod,
    title = this.title,
    thumbnailUrl = this.thumbnailUrl,
    thumbnailWidth = this.thumbnailWidth,
    thumbnailHeight = this.thumbnailHeight,
    replyMarkup = this.replyMarkup.toDomain(),
    inputMessageContent = this.inputMessageContent.toDomain()
)

fun TdApi.InputInlineQueryResultPhoto.toDomain(): InputInlineQueryResultPhoto = InputInlineQueryResultPhoto(
    id = this.id,
    title = this.title,
    description = this.description,
    thumbnailUrl = this.thumbnailUrl,
    photoUrl = this.photoUrl,
    photoWidth = this.photoWidth,
    photoHeight = this.photoHeight,
    replyMarkup = this.replyMarkup.toDomain(),
    inputMessageContent = this.inputMessageContent.toDomain()
)

fun TdApi.InputInlineQueryResultSticker.toDomain(): InputInlineQueryResultSticker = InputInlineQueryResultSticker(
    id = this.id,
    thumbnailUrl = this.thumbnailUrl,
    stickerUrl = this.stickerUrl,
    stickerWidth = this.stickerWidth,
    stickerHeight = this.stickerHeight,
    replyMarkup = this.replyMarkup.toDomain(),
    inputMessageContent = this.inputMessageContent.toDomain()
)

fun TdApi.InputInlineQueryResultVenue.toDomain(): InputInlineQueryResultVenue = InputInlineQueryResultVenue(
    id = this.id,
    venue = this.venue.toDomain(),
    thumbnailUrl = this.thumbnailUrl,
    thumbnailWidth = this.thumbnailWidth,
    thumbnailHeight = this.thumbnailHeight,
    replyMarkup = this.replyMarkup.toDomain(),
    inputMessageContent = this.inputMessageContent.toDomain()
)

fun TdApi.InputInlineQueryResultVideo.toDomain(): InputInlineQueryResultVideo = InputInlineQueryResultVideo(
    id = this.id,
    title = this.title,
    description = this.description,
    thumbnailUrl = this.thumbnailUrl,
    videoUrl = this.videoUrl,
    mimeType = this.mimeType,
    videoWidth = this.videoWidth,
    videoHeight = this.videoHeight,
    videoDuration = this.videoDuration,
    replyMarkup = this.replyMarkup.toDomain(),
    inputMessageContent = this.inputMessageContent.toDomain()
)

fun TdApi.InputInlineQueryResultVoiceNote.toDomain(): InputInlineQueryResultVoiceNote = InputInlineQueryResultVoiceNote(
    id = this.id,
    title = this.title,
    voiceNoteUrl = this.voiceNoteUrl,
    voiceNoteDuration = this.voiceNoteDuration,
    replyMarkup = this.replyMarkup.toDomain(),
    inputMessageContent = this.inputMessageContent.toDomain()
)

fun TdApi.InputMessageAnimation.toDomain(): InputMessageAnimation = InputMessageAnimation(
    animation = this.animation.toDomain(),
    thumbnail = this.thumbnail.toDomain(),
    addedStickerFileIds = this.addedStickerFileIds,
    duration = this.duration,
    width = this.width,
    height = this.height,
    caption = this.caption.toDomain(),
    showCaptionAboveMedia = this.showCaptionAboveMedia,
    hasSpoiler = this.hasSpoiler
)

fun TdApi.InputMessageAudio.toDomain(): InputMessageAudio = InputMessageAudio(
    audio = this.audio.toDomain(),
    albumCoverThumbnail = this.albumCoverThumbnail.toDomain(),
    duration = this.duration,
    title = this.title,
    performer = this.performer,
    caption = this.caption.toDomain()
)

fun TdApi.InputMessageChecklist.toDomain(): InputMessageChecklist = InputMessageChecklist(
    checklist = this.checklist.toDomain()
)

fun TdApi.InputMessageContact.toDomain(): InputMessageContact = InputMessageContact(
    contact = this.contact.toDomain()
)

fun TdApi.InputMessageContent.toDomain(): InputMessageContent = when(this) {
    is TdApi.InputMessageText -> this.toDomain()
    is TdApi.InputMessageAnimation -> this.toDomain()
    is TdApi.InputMessageAudio -> this.toDomain()
    is TdApi.InputMessageDocument -> this.toDomain()
    is TdApi.InputMessagePaidMedia -> this.toDomain()
    is TdApi.InputMessagePhoto -> this.toDomain()
    is TdApi.InputMessageSticker -> this.toDomain()
    is TdApi.InputMessageVideo -> this.toDomain()
    is TdApi.InputMessageVideoNote -> this.toDomain()
    is TdApi.InputMessageVoiceNote -> this.toDomain()
    is TdApi.InputMessageLocation -> this.toDomain()
    is TdApi.InputMessageVenue -> this.toDomain()
    is TdApi.InputMessageContact -> this.toDomain()
    is TdApi.InputMessageDice -> this.toDomain()
    is TdApi.InputMessageGame -> this.toDomain()
    is TdApi.InputMessageInvoice -> this.toDomain()
    is TdApi.InputMessagePoll -> this.toDomain()
    is TdApi.InputMessageStory -> this.toDomain()
    is TdApi.InputMessageChecklist -> this.toDomain()
    is TdApi.InputMessageForwarded -> this.toDomain()
    else -> throw IllegalArgumentException("Unknown type: ${this.javaClass}")
}

fun TdApi.InputMessageDice.toDomain(): InputMessageDice = InputMessageDice(
    emoji = this.emoji,
    clearDraft = this.clearDraft
)

fun TdApi.InputMessageDocument.toDomain(): InputMessageDocument = InputMessageDocument(
    document = this.document.toDomain(),
    thumbnail = this.thumbnail.toDomain(),
    disableContentTypeDetection = this.disableContentTypeDetection,
    caption = this.caption.toDomain()
)

fun TdApi.InputMessageForwarded.toDomain(): InputMessageForwarded = InputMessageForwarded(
    fromChatId = this.fromChatId,
    messageId = this.messageId,
    inGameShare = this.inGameShare,
    replaceVideoStartTimestamp = this.replaceVideoStartTimestamp,
    newVideoStartTimestamp = this.newVideoStartTimestamp,
    copyOptions = this.copyOptions.toDomain()
)

fun TdApi.InputMessageGame.toDomain(): InputMessageGame = InputMessageGame(
    botUserId = this.botUserId,
    gameShortName = this.gameShortName
)

fun TdApi.InputMessageInvoice.toDomain(): InputMessageInvoice = InputMessageInvoice(
    invoice = this.invoice.toDomain(),
    title = this.title,
    description = this.description,
    photoUrl = this.photoUrl,
    photoSize = this.photoSize,
    photoWidth = this.photoWidth,
    photoHeight = this.photoHeight,
    payload = this.payload,
    providerToken = this.providerToken,
    providerData = this.providerData,
    startParameter = this.startParameter,
    paidMedia = this.paidMedia.toDomain(),
    paidMediaCaption = this.paidMediaCaption.toDomain()
)

fun TdApi.InputMessageLocation.toDomain(): InputMessageLocation = InputMessageLocation(
    location = this.location.toDomain(),
    livePeriod = this.livePeriod,
    heading = this.heading,
    proximityAlertRadius = this.proximityAlertRadius
)

fun TdApi.InputMessagePaidMedia.toDomain(): InputMessagePaidMedia = InputMessagePaidMedia(
    starCount = this.starCount,
    paidMedia = this.paidMedia.map { it.toDomain() },
    caption = this.caption.toDomain(),
    showCaptionAboveMedia = this.showCaptionAboveMedia,
    payload = this.payload
)

fun TdApi.InputMessagePhoto.toDomain(): InputMessagePhoto = InputMessagePhoto(
    photo = this.photo.toDomain(),
    thumbnail = this.thumbnail.toDomain(),
    addedStickerFileIds = this.addedStickerFileIds,
    width = this.width,
    height = this.height,
    caption = this.caption.toDomain(),
    showCaptionAboveMedia = this.showCaptionAboveMedia,
    selfDestructType = this.selfDestructType.toDomain(),
    hasSpoiler = this.hasSpoiler
)

fun TdApi.InputMessagePoll.toDomain(): InputMessagePoll = InputMessagePoll(
    question = this.question.toDomain(),
    options = this.options.map { it.toDomain() },
    isAnonymous = this.isAnonymous,
    type = this.type.toDomain(),
    openPeriod = this.openPeriod,
    closeDate = this.closeDate,
    isClosed = this.isClosed
)

fun TdApi.InputMessageReplyTo.toDomain(): InputMessageReplyTo = when(this) {
    is TdApi.InputMessageReplyToMessage -> this.toDomain()
    is TdApi.InputMessageReplyToExternalMessage -> this.toDomain()
    is TdApi.InputMessageReplyToStory -> this.toDomain()
    else -> throw IllegalArgumentException("Unknown type: ${this.javaClass}")
}

fun TdApi.InputMessageReplyToExternalMessage.toDomain(): InputMessageReplyToExternalMessage = InputMessageReplyToExternalMessage(
    chatId = this.chatId,
    messageId = this.messageId,
    quote = this.quote.toDomain(),
    checklistTaskId = this.checklistTaskId
)

fun TdApi.InputMessageReplyToMessage.toDomain(): InputMessageReplyToMessage = InputMessageReplyToMessage(
    messageId = this.messageId,
    quote = this.quote.toDomain(),
    checklistTaskId = this.checklistTaskId
)

fun TdApi.InputMessageReplyToStory.toDomain(): InputMessageReplyToStory = InputMessageReplyToStory(
    storyPosterChatId = this.storyPosterChatId,
    storyId = this.storyId
)

fun TdApi.InputMessageSticker.toDomain(): InputMessageSticker = InputMessageSticker(
    sticker = this.sticker.toDomain(),
    thumbnail = this.thumbnail.toDomain(),
    width = this.width,
    height = this.height,
    emoji = this.emoji
)

fun TdApi.InputMessageStory.toDomain(): InputMessageStory = InputMessageStory(
    storyPosterChatId = this.storyPosterChatId,
    storyId = this.storyId
)

fun TdApi.InputMessageText.toDomain(): InputMessageText = InputMessageText(
    text = this.text.toDomain(),
    linkPreviewOptions = this.linkPreviewOptions?.toDomain(),
    clearDraft = this.clearDraft
)

fun TdApi.InputMessageVenue.toDomain(): InputMessageVenue = InputMessageVenue(
    venue = this.venue.toDomain()
)

fun TdApi.InputMessageVideo.toDomain(): InputMessageVideo = InputMessageVideo(
    video = this.video.toDomain(),
    thumbnail = this.thumbnail.toDomain(),
    cover = this.cover.toDomain(),
    startTimestamp = this.startTimestamp,
    addedStickerFileIds = this.addedStickerFileIds,
    duration = this.duration,
    width = this.width,
    height = this.height,
    supportsStreaming = this.supportsStreaming,
    caption = this.caption.toDomain(),
    showCaptionAboveMedia = this.showCaptionAboveMedia,
    selfDestructType = this.selfDestructType.toDomain(),
    hasSpoiler = this.hasSpoiler
)

fun TdApi.InputMessageVideoNote.toDomain(): InputMessageVideoNote = InputMessageVideoNote(
    videoNote = this.videoNote.toDomain(),
    thumbnail = this.thumbnail?.toDomain(),
    duration = this.duration,
    length = this.length,
    selfDestructType = this.selfDestructType?.toDomain()
)

fun TdApi.InputMessageVoiceNote.toDomain(): InputMessageVoiceNote = InputMessageVoiceNote(
    voiceNote = this.voiceNote.toDomain(),
    duration = this.duration,
    waveform = this.waveform,
    caption = this.caption?.toDomain(),
    selfDestructType = this.selfDestructType?.toDomain()
)

fun TdApi.InputPaidMedia.toDomain(): InputPaidMedia = InputPaidMedia(
    type = this.type.toDomain(),
    media = this.media.toDomain(),
    thumbnail = this.thumbnail.toDomain(),
    addedStickerFileIds = this.addedStickerFileIds,
    width = this.width,
    height = this.height
)

fun TdApi.InputPaidMediaType.toDomain(): InputPaidMediaType = when(this) {
    is TdApi.InputPaidMediaTypePhoto -> this.toDomain()
    is TdApi.InputPaidMediaTypeVideo -> this.toDomain()
    else -> throw IllegalArgumentException("Unknown type: ${this.javaClass}")
}

fun TdApi.InputPaidMediaTypePhoto.toDomain(): InputPaidMediaTypePhoto = InputPaidMediaTypePhoto

fun TdApi.InputPaidMediaTypeVideo.toDomain(): InputPaidMediaTypeVideo = InputPaidMediaTypeVideo(
    cover = this.cover.toDomain(),
    startTimestamp = this.startTimestamp,
    duration = this.duration,
    supportsStreaming = this.supportsStreaming
)

fun TdApi.InputPassportElement.toDomain(): InputPassportElement = when(this) {
    is TdApi.InputPassportElementPersonalDetails -> this.toDomain()
    is TdApi.InputPassportElementPassport -> this.toDomain()
    is TdApi.InputPassportElementDriverLicense -> this.toDomain()
    is TdApi.InputPassportElementIdentityCard -> this.toDomain()
    is TdApi.InputPassportElementInternalPassport -> this.toDomain()
    is TdApi.InputPassportElementAddress -> this.toDomain()
    is TdApi.InputPassportElementUtilityBill -> this.toDomain()
    is TdApi.InputPassportElementBankStatement -> this.toDomain()
    is TdApi.InputPassportElementRentalAgreement -> this.toDomain()
    is TdApi.InputPassportElementPassportRegistration -> this.toDomain()
    is TdApi.InputPassportElementTemporaryRegistration -> this.toDomain()
    is TdApi.InputPassportElementPhoneNumber -> this.toDomain()
    is TdApi.InputPassportElementEmailAddress -> this.toDomain()
    else -> throw IllegalArgumentException("Unknown type: ${this.javaClass}")
}

fun TdApi.InputPassportElementAddress.toDomain(): InputPassportElementAddress = InputPassportElementAddress(
    address = this.address.toDomain()
)

fun TdApi.InputPassportElementBankStatement.toDomain(): InputPassportElementBankStatement = InputPassportElementBankStatement(
    bankStatement = this.bankStatement.toDomain()
)

fun TdApi.InputPassportElementDriverLicense.toDomain(): InputPassportElementDriverLicense = InputPassportElementDriverLicense(
    driverLicense = this.driverLicense.toDomain()
)

fun TdApi.InputPassportElementEmailAddress.toDomain(): InputPassportElementEmailAddress = InputPassportElementEmailAddress(
    emailAddress = this.emailAddress
)

fun TdApi.InputPassportElementError.toDomain(): InputPassportElementError = InputPassportElementError(
    type = this.type.toDomain(),
    message = this.message,
    source = this.source.toDomain()
)

fun TdApi.InputPassportElementErrorSource.toDomain(): InputPassportElementErrorSource = when(this) {
    is TdApi.InputPassportElementErrorSourceUnspecified -> this.toDomain()
    is TdApi.InputPassportElementErrorSourceDataField -> this.toDomain()
    is TdApi.InputPassportElementErrorSourceFrontSide -> this.toDomain()
    is TdApi.InputPassportElementErrorSourceReverseSide -> this.toDomain()
    is TdApi.InputPassportElementErrorSourceSelfie -> this.toDomain()
    is TdApi.InputPassportElementErrorSourceTranslationFile -> this.toDomain()
    is TdApi.InputPassportElementErrorSourceTranslationFiles -> this.toDomain()
    is TdApi.InputPassportElementErrorSourceFile -> this.toDomain()
    is TdApi.InputPassportElementErrorSourceFiles -> this.toDomain()
    else -> throw IllegalArgumentException("Unknown type: ${this.javaClass}")
}

fun TdApi.InputPassportElementErrorSourceDataField.toDomain(): InputPassportElementErrorSourceDataField = InputPassportElementErrorSourceDataField(
    fieldName = this.fieldName,
    dataHash = this.dataHash
)

fun TdApi.InputPassportElementErrorSourceFile.toDomain(): InputPassportElementErrorSourceFile = InputPassportElementErrorSourceFile(
    fileHash = this.fileHash
)

fun TdApi.InputPassportElementErrorSourceFiles.toDomain(): InputPassportElementErrorSourceFiles = InputPassportElementErrorSourceFiles(
    fileHashes = this.fileHashes.toList()
)

fun TdApi.InputPassportElementErrorSourceFrontSide.toDomain(): InputPassportElementErrorSourceFrontSide = InputPassportElementErrorSourceFrontSide(
    fileHash = this.fileHash
)

fun TdApi.InputPassportElementErrorSourceReverseSide.toDomain(): InputPassportElementErrorSourceReverseSide = InputPassportElementErrorSourceReverseSide(
    fileHash = this.fileHash
)

fun TdApi.InputPassportElementErrorSourceSelfie.toDomain(): InputPassportElementErrorSourceSelfie = InputPassportElementErrorSourceSelfie(
    fileHash = this.fileHash
)

fun TdApi.InputPassportElementErrorSourceTranslationFile.toDomain(): InputPassportElementErrorSourceTranslationFile = InputPassportElementErrorSourceTranslationFile(
    fileHash = this.fileHash
)

fun TdApi.InputPassportElementErrorSourceTranslationFiles.toDomain(): InputPassportElementErrorSourceTranslationFiles = InputPassportElementErrorSourceTranslationFiles(
    fileHashes = this.fileHashes.toList()
)

fun TdApi.InputPassportElementErrorSourceUnspecified.toDomain(): InputPassportElementErrorSourceUnspecified = InputPassportElementErrorSourceUnspecified(
    elementHash = this.elementHash
)

fun TdApi.InputPassportElementIdentityCard.toDomain(): InputPassportElementIdentityCard = InputPassportElementIdentityCard(
    identityCard = this.identityCard.toDomain()
)

fun TdApi.InputPassportElementInternalPassport.toDomain(): InputPassportElementInternalPassport = InputPassportElementInternalPassport(
    internalPassport = this.internalPassport.toDomain()
)

fun TdApi.InputPassportElementPassport.toDomain(): InputPassportElementPassport = InputPassportElementPassport(
    passport = this.passport.toDomain()
)

fun TdApi.InputPassportElementPassportRegistration.toDomain(): InputPassportElementPassportRegistration = InputPassportElementPassportRegistration(
    passportRegistration = this.passportRegistration.toDomain()
)

fun TdApi.InputPassportElementPersonalDetails.toDomain(): InputPassportElementPersonalDetails = InputPassportElementPersonalDetails(
    personalDetails = this.personalDetails.toDomain()
)

fun TdApi.InputPassportElementPhoneNumber.toDomain(): InputPassportElementPhoneNumber = InputPassportElementPhoneNumber(
    phoneNumber = this.phoneNumber
)

fun TdApi.InputPassportElementRentalAgreement.toDomain(): InputPassportElementRentalAgreement = InputPassportElementRentalAgreement(
    rentalAgreement = this.rentalAgreement.toDomain()
)

fun TdApi.InputPassportElementTemporaryRegistration.toDomain(): InputPassportElementTemporaryRegistration = InputPassportElementTemporaryRegistration(
    temporaryRegistration = this.temporaryRegistration.toDomain()
)

fun TdApi.InputPassportElementUtilityBill.toDomain(): InputPassportElementUtilityBill = InputPassportElementUtilityBill(
    utilityBill = this.utilityBill.toDomain()
)

fun TdApi.InputPersonalDocument.toDomain(): InputPersonalDocument = InputPersonalDocument(
    files = this.files.map { it.toDomain() },
    translation = this.translation.map { it.toDomain() }
)

fun TdApi.InputSticker.toDomain(): InputSticker = InputSticker(
    sticker = this.sticker.toDomain(),
    format = this.format.toDomain(),
    emojis = this.emojis,
    maskPosition = this.maskPosition.toDomain(),
    keywords = this.keywords.toList()
)

fun TdApi.InputStoryContent.toDomain(): InputStoryContent = when(this) {
    is TdApi.InputStoryContentPhoto -> this.toDomain()
    is TdApi.InputStoryContentVideo -> this.toDomain()
    else -> throw IllegalArgumentException("Unknown type: ${this.javaClass}")
}

fun TdApi.InputStoryContentPhoto.toDomain(): InputStoryContentPhoto = InputStoryContentPhoto(
    photo = this.photo.toDomain(),
    addedStickerFileIds = this.addedStickerFileIds
)

fun TdApi.InputStoryContentVideo.toDomain(): InputStoryContentVideo = InputStoryContentVideo(
    video = this.video.toDomain(),
    addedStickerFileIds = this.addedStickerFileIds,
    duration = this.duration,
    coverFrameTimestamp = this.coverFrameTimestamp,
    isAnimation = this.isAnimation
)

fun TdApi.InputSuggestedPostInfo.toDomain(): InputSuggestedPostInfo = InputSuggestedPostInfo(
    price = this.price.toDomain(),
    sendDate = this.sendDate
)

fun TdApi.InputTextQuote.toDomain(): InputTextQuote = InputTextQuote(
    text = this.text.toDomain(),
    position = this.position
)

fun TdApi.InputThumbnail.toDomain(): InputThumbnail = InputThumbnail(
    thumbnail = this.thumbnail.toDomain(),
    width = this.width,
    height = this.height
)

