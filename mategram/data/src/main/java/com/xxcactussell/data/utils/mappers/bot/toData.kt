package com.xxcactussell.data.utils.mappers.bot

import com.xxcactussell.data.utils.mappers.affiliate.toData
import com.xxcactussell.data.utils.mappers.animation.toData
import com.xxcactussell.data.utils.mappers.bots.toData
import com.xxcactussell.data.utils.mappers.chat.toData
import com.xxcactussell.data.utils.mappers.formatted.toData
import com.xxcactussell.data.utils.mappers.internal.toData
import com.xxcactussell.data.utils.mappers.photo.toData
import com.xxcactussell.data.utils.mappers.stories.toData
import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: Domain -> TdApi

fun BotInfo.toData(): TdApi.BotInfo = TdApi.BotInfo(
    this.shortDescription,
    this.description,
    this.photo?.toData(),
    this.animation?.toData(),
    this.menuButton?.toData(),
    this.commands.map { it.toData() }.toTypedArray(),
    this.privacyPolicyUrl,
    this.defaultGroupAdministratorRights?.toData(),
    this.defaultChannelAdministratorRights?.toData(),
    this.affiliateProgram?.toData(),
    this.webAppBackgroundLightColor,
    this.webAppBackgroundDarkColor,
    this.webAppHeaderLightColor,
    this.webAppHeaderDarkColor,
    this.verificationParameters?.toData(),
    this.canGetRevenueStatistics,
    this.canManageEmojiStatus,
    this.hasMediaPreviews,
    this.editCommandsLink?.toData(),
    this.editDescriptionLink?.toData(),
    this.editDescriptionMediaLink?.toData(),
    this.editSettingsLink?.toData()
)

fun BotMediaPreview.toData(): TdApi.BotMediaPreview = TdApi.BotMediaPreview(
    this.date,
    this.content.toData()
)

fun BotMediaPreviewInfo.toData(): TdApi.BotMediaPreviewInfo = TdApi.BotMediaPreviewInfo(
    this.previews.map { it.toData() }.toTypedArray(),
    this.languageCodes.toTypedArray()
)

fun BotMediaPreviews.toData(): TdApi.BotMediaPreviews = TdApi.BotMediaPreviews(
    this.previews.map { it.toData() }.toTypedArray()
)

fun BotVerification.toData(): TdApi.BotVerification = TdApi.BotVerification(
    this.botUserId,
    this.iconCustomEmojiId,
    this.customDescription.toData()
)

fun BotVerificationParameters.toData(): TdApi.BotVerificationParameters = TdApi.BotVerificationParameters(
    this.iconCustomEmojiId,
    this.organizationName,
    this.defaultCustomDescription?.toData(),
    this.canSetCustomDescription
)

fun BotWriteAccessAllowReason.toData(): TdApi.BotWriteAccessAllowReason = when(this) {
    is BotWriteAccessAllowReasonConnectedWebsite -> this.toData()
    is BotWriteAccessAllowReasonAddedToAttachmentMenu -> this.toData()
    is BotWriteAccessAllowReasonLaunchedWebApp -> this.toData()
    is BotWriteAccessAllowReasonAcceptedRequest -> this.toData()
}

fun BotWriteAccessAllowReasonAcceptedRequest.toData(): TdApi.BotWriteAccessAllowReasonAcceptedRequest = TdApi.BotWriteAccessAllowReasonAcceptedRequest(
)

fun BotWriteAccessAllowReasonAddedToAttachmentMenu.toData(): TdApi.BotWriteAccessAllowReasonAddedToAttachmentMenu = TdApi.BotWriteAccessAllowReasonAddedToAttachmentMenu(
)

fun BotWriteAccessAllowReasonConnectedWebsite.toData(): TdApi.BotWriteAccessAllowReasonConnectedWebsite = TdApi.BotWriteAccessAllowReasonConnectedWebsite(
    this.domainName
)

fun BotWriteAccessAllowReasonLaunchedWebApp.toData(): TdApi.BotWriteAccessAllowReasonLaunchedWebApp = TdApi.BotWriteAccessAllowReasonLaunchedWebApp(
    this.webApp.toData()
)

