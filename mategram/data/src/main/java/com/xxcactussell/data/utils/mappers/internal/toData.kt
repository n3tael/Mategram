package com.xxcactussell.data.utils.mappers.internal

import com.xxcactussell.data.utils.mappers.bots.toData
import com.xxcactussell.data.utils.mappers.chat.toData
import com.xxcactussell.data.utils.mappers.formatted.toData
import com.xxcactussell.data.utils.mappers.proxy.toData
import com.xxcactussell.data.utils.mappers.target.toData
import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: Domain -> TdApi

fun InternalLinkType.toData(): TdApi.InternalLinkType = when(this) {
    is InternalLinkTypeActiveSessions -> this.toData()
    is InternalLinkTypeAttachmentMenuBot -> this.toData()
    is InternalLinkTypeAuthenticationCode -> this.toData()
    is InternalLinkTypeBackground -> this.toData()
    is InternalLinkTypeBotAddToChannel -> this.toData()
    is InternalLinkTypeBotStart -> this.toData()
    is InternalLinkTypeBotStartInGroup -> this.toData()
    is InternalLinkTypeBusinessChat -> this.toData()
    is InternalLinkTypeBuyStars -> this.toData()
    is InternalLinkTypeChangePhoneNumber -> this.toData()
    is InternalLinkTypeChatAffiliateProgram -> this.toData()
    is InternalLinkTypeChatBoost -> this.toData()
    is InternalLinkTypeChatFolderInvite -> this.toData()
    is InternalLinkTypeChatFolderSettings -> this.toData()
    is InternalLinkTypeChatInvite -> this.toData()
    is InternalLinkTypeDefaultMessageAutoDeleteTimerSettings -> this.toData()
    is InternalLinkTypeDirectMessagesChat -> this.toData()
    is InternalLinkTypeEditProfileSettings -> this.toData()
    is InternalLinkTypeGame -> this.toData()
    is InternalLinkTypeGiftCollection -> this.toData()
    is InternalLinkTypeGroupCall -> this.toData()
    is InternalLinkTypeInstantView -> this.toData()
    is InternalLinkTypeInvoice -> this.toData()
    is InternalLinkTypeLanguagePack -> this.toData()
    is InternalLinkTypeLanguageSettings -> this.toData()
    is InternalLinkTypeMainWebApp -> this.toData()
    is InternalLinkTypeMessage -> this.toData()
    is InternalLinkTypeMessageDraft -> this.toData()
    is InternalLinkTypeMyStars -> this.toData()
    is InternalLinkTypeMyToncoins -> this.toData()
    is InternalLinkTypePassportDataRequest -> this.toData()
    is InternalLinkTypePhoneNumberConfirmation -> this.toData()
    is InternalLinkTypePremiumGift -> this.toData()
    is InternalLinkTypePremiumGiftCode -> this.toData()
    is InternalLinkTypePrivacyAndSecuritySettings -> this.toData()
    is InternalLinkTypeProxy -> this.toData()
    is InternalLinkTypePublicChat -> this.toData()
    is InternalLinkTypeQrCodeAuthentication -> this.toData()
    is InternalLinkTypeRestorePurchases -> this.toData()
    is InternalLinkTypeSettings -> this.toData()
    is InternalLinkTypeStickerSet -> this.toData()
    is InternalLinkTypeStory -> this.toData()
    is InternalLinkTypeStoryAlbum -> this.toData()
    is InternalLinkTypeTheme -> this.toData()
    is InternalLinkTypeThemeSettings -> this.toData()
    is InternalLinkTypeUnknownDeepLink -> this.toData()
    is InternalLinkTypeUnsupportedProxy -> this.toData()
    is InternalLinkTypeUpgradedGift -> this.toData()
    is InternalLinkTypeUserPhoneNumber -> this.toData()
    is InternalLinkTypeUserToken -> this.toData()
    is InternalLinkTypeVideoChat -> this.toData()
    is InternalLinkTypeWebApp -> this.toData()
    is InternalLinkTypePremiumFeatures -> this.toData()
}

fun InternalLinkTypeActiveSessions.toData(): TdApi.InternalLinkTypeActiveSessions = TdApi.InternalLinkTypeActiveSessions(
)

fun InternalLinkTypeAttachmentMenuBot.toData(): TdApi.InternalLinkTypeAttachmentMenuBot = TdApi.InternalLinkTypeAttachmentMenuBot(
    this.targetChat.toData(),
    this.botUsername,
    this.url
)

fun InternalLinkTypeAuthenticationCode.toData(): TdApi.InternalLinkTypeAuthenticationCode = TdApi.InternalLinkTypeAuthenticationCode(
    this.code
)

fun InternalLinkTypeBackground.toData(): TdApi.InternalLinkTypeBackground = TdApi.InternalLinkTypeBackground(
    this.backgroundName
)

fun InternalLinkTypeBotAddToChannel.toData(): TdApi.InternalLinkTypeBotAddToChannel = TdApi.InternalLinkTypeBotAddToChannel(
    this.botUsername,
    this.administratorRights.toData()
)

fun InternalLinkTypeBotStart.toData(): TdApi.InternalLinkTypeBotStart = TdApi.InternalLinkTypeBotStart(
    this.botUsername,
    this.startParameter,
    this.autostart
)

fun InternalLinkTypeBotStartInGroup.toData(): TdApi.InternalLinkTypeBotStartInGroup = TdApi.InternalLinkTypeBotStartInGroup(
    this.botUsername,
    this.startParameter,
    this.administratorRights?.toData()
)

fun InternalLinkTypeBusinessChat.toData(): TdApi.InternalLinkTypeBusinessChat = TdApi.InternalLinkTypeBusinessChat(
    this.linkName
)

fun InternalLinkTypeBuyStars.toData(): TdApi.InternalLinkTypeBuyStars = TdApi.InternalLinkTypeBuyStars(
    this.starCount,
    this.purpose
)

fun InternalLinkTypeChangePhoneNumber.toData(): TdApi.InternalLinkTypeChangePhoneNumber = TdApi.InternalLinkTypeChangePhoneNumber(
)

fun InternalLinkTypeChatAffiliateProgram.toData(): TdApi.InternalLinkTypeChatAffiliateProgram = TdApi.InternalLinkTypeChatAffiliateProgram(
    this.username,
    this.referrer
)

fun InternalLinkTypeChatBoost.toData(): TdApi.InternalLinkTypeChatBoost = TdApi.InternalLinkTypeChatBoost(
    this.url
)

fun InternalLinkTypeChatFolderInvite.toData(): TdApi.InternalLinkTypeChatFolderInvite = TdApi.InternalLinkTypeChatFolderInvite(
    this.inviteLink
)

fun InternalLinkTypeChatFolderSettings.toData(): TdApi.InternalLinkTypeChatFolderSettings = TdApi.InternalLinkTypeChatFolderSettings(
)

fun InternalLinkTypeChatInvite.toData(): TdApi.InternalLinkTypeChatInvite = TdApi.InternalLinkTypeChatInvite(
    this.inviteLink
)

fun InternalLinkTypeDefaultMessageAutoDeleteTimerSettings.toData(): TdApi.InternalLinkTypeDefaultMessageAutoDeleteTimerSettings = TdApi.InternalLinkTypeDefaultMessageAutoDeleteTimerSettings(
)

fun InternalLinkTypeDirectMessagesChat.toData(): TdApi.InternalLinkTypeDirectMessagesChat = TdApi.InternalLinkTypeDirectMessagesChat(
    this.channelUsername
)

fun InternalLinkTypeEditProfileSettings.toData(): TdApi.InternalLinkTypeEditProfileSettings = TdApi.InternalLinkTypeEditProfileSettings(
)

fun InternalLinkTypeGame.toData(): TdApi.InternalLinkTypeGame = TdApi.InternalLinkTypeGame(
    this.botUsername,
    this.gameShortName
)

fun InternalLinkTypeGiftCollection.toData(): TdApi.InternalLinkTypeGiftCollection = TdApi.InternalLinkTypeGiftCollection(
    this.giftOwnerUsername,
    this.collectionId
)

fun InternalLinkTypeGroupCall.toData(): TdApi.InternalLinkTypeGroupCall = TdApi.InternalLinkTypeGroupCall(
    this.inviteLink
)

fun InternalLinkTypeInstantView.toData(): TdApi.InternalLinkTypeInstantView = TdApi.InternalLinkTypeInstantView(
    this.url,
    this.fallbackUrl
)

fun InternalLinkTypeInvoice.toData(): TdApi.InternalLinkTypeInvoice = TdApi.InternalLinkTypeInvoice(
    this.invoiceName
)

fun InternalLinkTypeLanguagePack.toData(): TdApi.InternalLinkTypeLanguagePack = TdApi.InternalLinkTypeLanguagePack(
    this.languagePackId
)

fun InternalLinkTypeLanguageSettings.toData(): TdApi.InternalLinkTypeLanguageSettings = TdApi.InternalLinkTypeLanguageSettings(
)

fun InternalLinkTypeMainWebApp.toData(): TdApi.InternalLinkTypeMainWebApp = TdApi.InternalLinkTypeMainWebApp(
    this.botUsername,
    this.startParameter,
    this.mode.toData()
)

fun InternalLinkTypeMessage.toData(): TdApi.InternalLinkTypeMessage = TdApi.InternalLinkTypeMessage(
    this.url
)

fun InternalLinkTypeMessageDraft.toData(): TdApi.InternalLinkTypeMessageDraft = TdApi.InternalLinkTypeMessageDraft(
    this.text.toData(),
    this.containsLink
)

fun InternalLinkTypeMyStars.toData(): TdApi.InternalLinkTypeMyStars = TdApi.InternalLinkTypeMyStars(
)

fun InternalLinkTypeMyToncoins.toData(): TdApi.InternalLinkTypeMyToncoins = TdApi.InternalLinkTypeMyToncoins(
)

fun InternalLinkTypePassportDataRequest.toData(): TdApi.InternalLinkTypePassportDataRequest = TdApi.InternalLinkTypePassportDataRequest(
    this.botUserId,
    this.scope,
    this.publicKey,
    this.nonce,
    this.callbackUrl
)

fun InternalLinkTypePhoneNumberConfirmation.toData(): TdApi.InternalLinkTypePhoneNumberConfirmation = TdApi.InternalLinkTypePhoneNumberConfirmation(
    this.hash,
    this.phoneNumber
)

fun InternalLinkTypePremiumGift.toData(): TdApi.InternalLinkTypePremiumGift = TdApi.InternalLinkTypePremiumGift(
    this.referrer
)

fun InternalLinkTypePremiumGiftCode.toData(): TdApi.InternalLinkTypePremiumGiftCode = TdApi.InternalLinkTypePremiumGiftCode(
    this.code
)

fun InternalLinkTypePrivacyAndSecuritySettings.toData(): TdApi.InternalLinkTypePrivacyAndSecuritySettings = TdApi.InternalLinkTypePrivacyAndSecuritySettings(
)

fun InternalLinkTypeProxy.toData(): TdApi.InternalLinkTypeProxy = TdApi.InternalLinkTypeProxy(
    this.server,
    this.port,
    this.type.toData()
)

fun InternalLinkTypePublicChat.toData(): TdApi.InternalLinkTypePublicChat = TdApi.InternalLinkTypePublicChat(
    this.chatUsername,
    this.draftText,
    this.openProfile
)

fun InternalLinkTypeQrCodeAuthentication.toData(): TdApi.InternalLinkTypeQrCodeAuthentication = TdApi.InternalLinkTypeQrCodeAuthentication(
)

fun InternalLinkTypeRestorePurchases.toData(): TdApi.InternalLinkTypeRestorePurchases = TdApi.InternalLinkTypeRestorePurchases(
)

fun InternalLinkTypeSettings.toData(): TdApi.InternalLinkTypeSettings = TdApi.InternalLinkTypeSettings(
)

fun InternalLinkTypeStickerSet.toData(): TdApi.InternalLinkTypeStickerSet = TdApi.InternalLinkTypeStickerSet(
    this.stickerSetName,
    this.expectCustomEmoji
)

fun InternalLinkTypeStory.toData(): TdApi.InternalLinkTypeStory = TdApi.InternalLinkTypeStory(
    this.storyPosterUsername,
    this.storyId
)

fun InternalLinkTypeStoryAlbum.toData(): TdApi.InternalLinkTypeStoryAlbum = TdApi.InternalLinkTypeStoryAlbum(
    this.storyAlbumOwnerUsername,
    this.storyAlbumId
)

fun InternalLinkTypeTheme.toData(): TdApi.InternalLinkTypeTheme = TdApi.InternalLinkTypeTheme(
    this.themeName
)

fun InternalLinkTypeThemeSettings.toData(): TdApi.InternalLinkTypeThemeSettings = TdApi.InternalLinkTypeThemeSettings(
)

fun InternalLinkTypeUnknownDeepLink.toData(): TdApi.InternalLinkTypeUnknownDeepLink = TdApi.InternalLinkTypeUnknownDeepLink(
    this.link
)

fun InternalLinkTypeUnsupportedProxy.toData(): TdApi.InternalLinkTypeUnsupportedProxy = TdApi.InternalLinkTypeUnsupportedProxy(
)

fun InternalLinkTypeUpgradedGift.toData(): TdApi.InternalLinkTypeUpgradedGift = TdApi.InternalLinkTypeUpgradedGift(
    this.name
)

fun InternalLinkTypeUserPhoneNumber.toData(): TdApi.InternalLinkTypeUserPhoneNumber = TdApi.InternalLinkTypeUserPhoneNumber(
    this.phoneNumber,
    this.draftText,
    this.openProfile
)

fun InternalLinkTypeUserToken.toData(): TdApi.InternalLinkTypeUserToken = TdApi.InternalLinkTypeUserToken(
    this.token
)

fun InternalLinkTypeVideoChat.toData(): TdApi.InternalLinkTypeVideoChat = TdApi.InternalLinkTypeVideoChat(
    this.chatUsername,
    this.inviteHash,
    this.isLiveStream
)

fun InternalLinkTypeWebApp.toData(): TdApi.InternalLinkTypeWebApp = TdApi.InternalLinkTypeWebApp(
    this.botUsername,
    this.webAppShortName,
    this.startParameter,
    this.mode.toData()
)

