package com.xxcactussell.data.utils.mappers.internal

import com.xxcactussell.data.utils.mappers.bots.toDomain
import com.xxcactussell.data.utils.mappers.chat.toDomain
import com.xxcactussell.data.utils.mappers.formatted.toDomain
import com.xxcactussell.data.utils.mappers.proxy.toDomain
import com.xxcactussell.data.utils.mappers.target.toDomain
import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: TdApi -> Domain

fun TdApi.InternalLinkType.toDomain(): InternalLinkType = when(this) {
    is TdApi.InternalLinkTypeActiveSessions -> this.toDomain()
    is TdApi.InternalLinkTypeAttachmentMenuBot -> this.toDomain()
    is TdApi.InternalLinkTypeAuthenticationCode -> this.toDomain()
    is TdApi.InternalLinkTypeBackground -> this.toDomain()
    is TdApi.InternalLinkTypeBotAddToChannel -> this.toDomain()
    is TdApi.InternalLinkTypeBotStart -> this.toDomain()
    is TdApi.InternalLinkTypeBotStartInGroup -> this.toDomain()
    is TdApi.InternalLinkTypeBusinessChat -> this.toDomain()
    is TdApi.InternalLinkTypeBuyStars -> this.toDomain()
    is TdApi.InternalLinkTypeChangePhoneNumber -> this.toDomain()
    is TdApi.InternalLinkTypeChatAffiliateProgram -> this.toDomain()
    is TdApi.InternalLinkTypeChatBoost -> this.toDomain()
    is TdApi.InternalLinkTypeChatFolderInvite -> this.toDomain()
    is TdApi.InternalLinkTypeChatFolderSettings -> this.toDomain()
    is TdApi.InternalLinkTypeChatInvite -> this.toDomain()
    is TdApi.InternalLinkTypeDefaultMessageAutoDeleteTimerSettings -> this.toDomain()
    is TdApi.InternalLinkTypeDirectMessagesChat -> this.toDomain()
    is TdApi.InternalLinkTypeEditProfileSettings -> this.toDomain()
    is TdApi.InternalLinkTypeGame -> this.toDomain()
    is TdApi.InternalLinkTypeGiftCollection -> this.toDomain()
    is TdApi.InternalLinkTypeGroupCall -> this.toDomain()
    is TdApi.InternalLinkTypeInstantView -> this.toDomain()
    is TdApi.InternalLinkTypeInvoice -> this.toDomain()
    is TdApi.InternalLinkTypeLanguagePack -> this.toDomain()
    is TdApi.InternalLinkTypeLanguageSettings -> this.toDomain()
    is TdApi.InternalLinkTypeMainWebApp -> this.toDomain()
    is TdApi.InternalLinkTypeMessage -> this.toDomain()
    is TdApi.InternalLinkTypeMessageDraft -> this.toDomain()
    is TdApi.InternalLinkTypeMyStars -> this.toDomain()
    is TdApi.InternalLinkTypeMyToncoins -> this.toDomain()
    is TdApi.InternalLinkTypePassportDataRequest -> this.toDomain()
    is TdApi.InternalLinkTypePhoneNumberConfirmation -> this.toDomain()
    is TdApi.InternalLinkTypePremiumGift -> this.toDomain()
    is TdApi.InternalLinkTypePremiumGiftCode -> this.toDomain()
    is TdApi.InternalLinkTypePrivacyAndSecuritySettings -> this.toDomain()
    is TdApi.InternalLinkTypeProxy -> this.toDomain()
    is TdApi.InternalLinkTypePublicChat -> this.toDomain()
    is TdApi.InternalLinkTypeQrCodeAuthentication -> this.toDomain()
    is TdApi.InternalLinkTypeRestorePurchases -> this.toDomain()
    is TdApi.InternalLinkTypeSettings -> this.toDomain()
    is TdApi.InternalLinkTypeStickerSet -> this.toDomain()
    is TdApi.InternalLinkTypeStory -> this.toDomain()
    is TdApi.InternalLinkTypeStoryAlbum -> this.toDomain()
    is TdApi.InternalLinkTypeTheme -> this.toDomain()
    is TdApi.InternalLinkTypeThemeSettings -> this.toDomain()
    is TdApi.InternalLinkTypeUnknownDeepLink -> this.toDomain()
    is TdApi.InternalLinkTypeUnsupportedProxy -> this.toDomain()
    is TdApi.InternalLinkTypeUpgradedGift -> this.toDomain()
    is TdApi.InternalLinkTypeUserPhoneNumber -> this.toDomain()
    is TdApi.InternalLinkTypeUserToken -> this.toDomain()
    is TdApi.InternalLinkTypeVideoChat -> this.toDomain()
    is TdApi.InternalLinkTypeWebApp -> this.toDomain()
    is TdApi.InternalLinkTypePremiumFeatures -> this.toDomain()
    else -> throw IllegalArgumentException("Unknown type: ${this.javaClass}")
}

fun TdApi.InternalLinkTypeActiveSessions.toDomain(): InternalLinkTypeActiveSessions = InternalLinkTypeActiveSessions

fun TdApi.InternalLinkTypeAttachmentMenuBot.toDomain(): InternalLinkTypeAttachmentMenuBot = InternalLinkTypeAttachmentMenuBot(
    targetChat = this.targetChat.toDomain(),
    botUsername = this.botUsername,
    url = this.url
)

fun TdApi.InternalLinkTypeAuthenticationCode.toDomain(): InternalLinkTypeAuthenticationCode = InternalLinkTypeAuthenticationCode(
    code = this.code
)

fun TdApi.InternalLinkTypeBackground.toDomain(): InternalLinkTypeBackground = InternalLinkTypeBackground(
    backgroundName = this.backgroundName
)

fun TdApi.InternalLinkTypeBotAddToChannel.toDomain(): InternalLinkTypeBotAddToChannel = InternalLinkTypeBotAddToChannel(
    botUsername = this.botUsername,
    administratorRights = this.administratorRights.toDomain()
)

fun TdApi.InternalLinkTypeBotStart.toDomain(): InternalLinkTypeBotStart = InternalLinkTypeBotStart(
    botUsername = this.botUsername,
    startParameter = this.startParameter,
    autostart = this.autostart
)

fun TdApi.InternalLinkTypeBotStartInGroup.toDomain(): InternalLinkTypeBotStartInGroup = InternalLinkTypeBotStartInGroup(
    botUsername = this.botUsername,
    startParameter = this.startParameter,
    administratorRights = this.administratorRights?.toDomain()
)

fun TdApi.InternalLinkTypeBusinessChat.toDomain(): InternalLinkTypeBusinessChat = InternalLinkTypeBusinessChat(
    linkName = this.linkName
)

fun TdApi.InternalLinkTypeBuyStars.toDomain(): InternalLinkTypeBuyStars = InternalLinkTypeBuyStars(
    starCount = this.starCount,
    purpose = this.purpose
)

fun TdApi.InternalLinkTypeChangePhoneNumber.toDomain(): InternalLinkTypeChangePhoneNumber = InternalLinkTypeChangePhoneNumber

fun TdApi.InternalLinkTypeChatAffiliateProgram.toDomain(): InternalLinkTypeChatAffiliateProgram = InternalLinkTypeChatAffiliateProgram(
    username = this.username,
    referrer = this.referrer
)

fun TdApi.InternalLinkTypeChatBoost.toDomain(): InternalLinkTypeChatBoost = InternalLinkTypeChatBoost(
    url = this.url
)

fun TdApi.InternalLinkTypeChatFolderInvite.toDomain(): InternalLinkTypeChatFolderInvite = InternalLinkTypeChatFolderInvite(
    inviteLink = this.inviteLink
)

fun TdApi.InternalLinkTypeChatFolderSettings.toDomain(): InternalLinkTypeChatFolderSettings = InternalLinkTypeChatFolderSettings

fun TdApi.InternalLinkTypeChatInvite.toDomain(): InternalLinkTypeChatInvite = InternalLinkTypeChatInvite(
    inviteLink = this.inviteLink
)

fun TdApi.InternalLinkTypeDefaultMessageAutoDeleteTimerSettings.toDomain(): InternalLinkTypeDefaultMessageAutoDeleteTimerSettings = InternalLinkTypeDefaultMessageAutoDeleteTimerSettings

fun TdApi.InternalLinkTypeDirectMessagesChat.toDomain(): InternalLinkTypeDirectMessagesChat = InternalLinkTypeDirectMessagesChat(
    channelUsername = this.channelUsername
)

fun TdApi.InternalLinkTypeEditProfileSettings.toDomain(): InternalLinkTypeEditProfileSettings = InternalLinkTypeEditProfileSettings

fun TdApi.InternalLinkTypeGame.toDomain(): InternalLinkTypeGame = InternalLinkTypeGame(
    botUsername = this.botUsername,
    gameShortName = this.gameShortName
)

fun TdApi.InternalLinkTypeGiftCollection.toDomain(): InternalLinkTypeGiftCollection = InternalLinkTypeGiftCollection(
    giftOwnerUsername = this.giftOwnerUsername,
    collectionId = this.collectionId
)

fun TdApi.InternalLinkTypeGroupCall.toDomain(): InternalLinkTypeGroupCall = InternalLinkTypeGroupCall(
    inviteLink = this.inviteLink
)

fun TdApi.InternalLinkTypeInstantView.toDomain(): InternalLinkTypeInstantView = InternalLinkTypeInstantView(
    url = this.url,
    fallbackUrl = this.fallbackUrl
)

fun TdApi.InternalLinkTypeInvoice.toDomain(): InternalLinkTypeInvoice = InternalLinkTypeInvoice(
    invoiceName = this.invoiceName
)

fun TdApi.InternalLinkTypeLanguagePack.toDomain(): InternalLinkTypeLanguagePack = InternalLinkTypeLanguagePack(
    languagePackId = this.languagePackId
)

fun TdApi.InternalLinkTypeLanguageSettings.toDomain(): InternalLinkTypeLanguageSettings = InternalLinkTypeLanguageSettings

fun TdApi.InternalLinkTypeMainWebApp.toDomain(): InternalLinkTypeMainWebApp = InternalLinkTypeMainWebApp(
    botUsername = this.botUsername,
    startParameter = this.startParameter,
    mode = this.mode.toDomain()
)

fun TdApi.InternalLinkTypeMessage.toDomain(): InternalLinkTypeMessage = InternalLinkTypeMessage(
    url = this.url
)

fun TdApi.InternalLinkTypeMessageDraft.toDomain(): InternalLinkTypeMessageDraft = InternalLinkTypeMessageDraft(
    text = this.text.toDomain(),
    containsLink = this.containsLink
)

fun TdApi.InternalLinkTypeMyStars.toDomain(): InternalLinkTypeMyStars = InternalLinkTypeMyStars

fun TdApi.InternalLinkTypeMyToncoins.toDomain(): InternalLinkTypeMyToncoins = InternalLinkTypeMyToncoins

fun TdApi.InternalLinkTypePassportDataRequest.toDomain(): InternalLinkTypePassportDataRequest = InternalLinkTypePassportDataRequest(
    botUserId = this.botUserId,
    scope = this.scope,
    publicKey = this.publicKey,
    nonce = this.nonce,
    callbackUrl = this.callbackUrl
)

fun TdApi.InternalLinkTypePhoneNumberConfirmation.toDomain(): InternalLinkTypePhoneNumberConfirmation = InternalLinkTypePhoneNumberConfirmation(
    hash = this.hash,
    phoneNumber = this.phoneNumber
)

fun TdApi.InternalLinkTypePremiumGift.toDomain(): InternalLinkTypePremiumGift = InternalLinkTypePremiumGift(
    referrer = this.referrer
)

fun TdApi.InternalLinkTypePremiumGiftCode.toDomain(): InternalLinkTypePremiumGiftCode = InternalLinkTypePremiumGiftCode(
    code = this.code
)

fun TdApi.InternalLinkTypePrivacyAndSecuritySettings.toDomain(): InternalLinkTypePrivacyAndSecuritySettings = InternalLinkTypePrivacyAndSecuritySettings

fun TdApi.InternalLinkTypeProxy.toDomain(): InternalLinkTypeProxy = InternalLinkTypeProxy(
    server = this.server,
    port = this.port,
    type = this.type.toDomain()
)

fun TdApi.InternalLinkTypePublicChat.toDomain(): InternalLinkTypePublicChat = InternalLinkTypePublicChat(
    chatUsername = this.chatUsername,
    draftText = this.draftText,
    openProfile = this.openProfile
)

fun TdApi.InternalLinkTypeQrCodeAuthentication.toDomain(): InternalLinkTypeQrCodeAuthentication = InternalLinkTypeQrCodeAuthentication

fun TdApi.InternalLinkTypeRestorePurchases.toDomain(): InternalLinkTypeRestorePurchases = InternalLinkTypeRestorePurchases

fun TdApi.InternalLinkTypeSettings.toDomain(): InternalLinkTypeSettings = InternalLinkTypeSettings

fun TdApi.InternalLinkTypeStickerSet.toDomain(): InternalLinkTypeStickerSet = InternalLinkTypeStickerSet(
    stickerSetName = this.stickerSetName,
    expectCustomEmoji = this.expectCustomEmoji
)

fun TdApi.InternalLinkTypeStory.toDomain(): InternalLinkTypeStory = InternalLinkTypeStory(
    storyPosterUsername = this.storyPosterUsername,
    storyId = this.storyId
)

fun TdApi.InternalLinkTypeStoryAlbum.toDomain(): InternalLinkTypeStoryAlbum = InternalLinkTypeStoryAlbum(
    storyAlbumOwnerUsername = this.storyAlbumOwnerUsername,
    storyAlbumId = this.storyAlbumId
)

fun TdApi.InternalLinkTypeTheme.toDomain(): InternalLinkTypeTheme = InternalLinkTypeTheme(
    themeName = this.themeName
)

fun TdApi.InternalLinkTypeThemeSettings.toDomain(): InternalLinkTypeThemeSettings = InternalLinkTypeThemeSettings

fun TdApi.InternalLinkTypeUnknownDeepLink.toDomain(): InternalLinkTypeUnknownDeepLink = InternalLinkTypeUnknownDeepLink(
    link = this.link
)

fun TdApi.InternalLinkTypeUnsupportedProxy.toDomain(): InternalLinkTypeUnsupportedProxy = InternalLinkTypeUnsupportedProxy

fun TdApi.InternalLinkTypeUpgradedGift.toDomain(): InternalLinkTypeUpgradedGift = InternalLinkTypeUpgradedGift(
    name = this.name
)

fun TdApi.InternalLinkTypeUserPhoneNumber.toDomain(): InternalLinkTypeUserPhoneNumber = InternalLinkTypeUserPhoneNumber(
    phoneNumber = this.phoneNumber,
    draftText = this.draftText,
    openProfile = this.openProfile
)

fun TdApi.InternalLinkTypeUserToken.toDomain(): InternalLinkTypeUserToken = InternalLinkTypeUserToken(
    token = this.token
)

fun TdApi.InternalLinkTypeVideoChat.toDomain(): InternalLinkTypeVideoChat = InternalLinkTypeVideoChat(
    chatUsername = this.chatUsername,
    inviteHash = this.inviteHash,
    isLiveStream = this.isLiveStream
)

fun TdApi.InternalLinkTypeWebApp.toDomain(): InternalLinkTypeWebApp = InternalLinkTypeWebApp(
    botUsername = this.botUsername,
    webAppShortName = this.webAppShortName,
    startParameter = this.startParameter,
    mode = this.mode.toDomain()
)

