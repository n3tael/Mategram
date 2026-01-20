package com.xxcactussell.data.utils.mappers.bot

import com.xxcactussell.data.utils.mappers.affiliate.toDomain
import com.xxcactussell.data.utils.mappers.animation.toDomain
import com.xxcactussell.data.utils.mappers.bots.toDomain
import com.xxcactussell.data.utils.mappers.chat.toDomain
import com.xxcactussell.data.utils.mappers.formatted.toDomain
import com.xxcactussell.data.utils.mappers.internal.toDomain
import com.xxcactussell.data.utils.mappers.photo.toDomain
import com.xxcactussell.data.utils.mappers.stories.toDomain
import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: TdApi -> Domain

fun TdApi.BotInfo.toDomain(): BotInfo = BotInfo(
    shortDescription = this.shortDescription,
    description = this.description,
    photo = this.photo?.toDomain(),
    animation = this.animation?.toDomain(),
    menuButton = this.menuButton?.toDomain(),
    commands = this.commands.map { it.toDomain() },
    privacyPolicyUrl = this.privacyPolicyUrl,
    defaultGroupAdministratorRights = this.defaultGroupAdministratorRights?.toDomain(),
    defaultChannelAdministratorRights = this.defaultChannelAdministratorRights?.toDomain(),
    affiliateProgram = this.affiliateProgram?.toDomain(),
    webAppBackgroundLightColor = this.webAppBackgroundLightColor,
    webAppBackgroundDarkColor = this.webAppBackgroundDarkColor,
    webAppHeaderLightColor = this.webAppHeaderLightColor,
    webAppHeaderDarkColor = this.webAppHeaderDarkColor,
    verificationParameters = this.verificationParameters?.toDomain(),
    canGetRevenueStatistics = this.canGetRevenueStatistics,
    canManageEmojiStatus = this.canManageEmojiStatus,
    hasMediaPreviews = this.hasMediaPreviews,
    editCommandsLink = this.editCommandsLink?.toDomain(),
    editDescriptionLink = this.editDescriptionLink?.toDomain(),
    editDescriptionMediaLink = this.editDescriptionMediaLink?.toDomain(),
    editSettingsLink = this.editSettingsLink?.toDomain()
)

fun TdApi.BotMediaPreview.toDomain(): BotMediaPreview = BotMediaPreview(
    date = this.date,
    content = this.content.toDomain()
)

fun TdApi.BotMediaPreviewInfo.toDomain(): BotMediaPreviewInfo = BotMediaPreviewInfo(
    previews = this.previews.map { it.toDomain() },
    languageCodes = this.languageCodes.toList()
)

fun TdApi.BotMediaPreviews.toDomain(): BotMediaPreviews = BotMediaPreviews(
    previews = this.previews.map { it.toDomain() }
)

fun TdApi.BotVerification.toDomain(): BotVerification = BotVerification(
    botUserId = this.botUserId,
    iconCustomEmojiId = this.iconCustomEmojiId,
    customDescription = this.customDescription.toDomain()
)

fun TdApi.BotVerificationParameters.toDomain(): BotVerificationParameters = BotVerificationParameters(
    iconCustomEmojiId = this.iconCustomEmojiId,
    organizationName = this.organizationName,
    defaultCustomDescription = this.defaultCustomDescription?.toDomain(),
    canSetCustomDescription = this.canSetCustomDescription
)

fun TdApi.BotWriteAccessAllowReason.toDomain(): BotWriteAccessAllowReason = when(this) {
    is TdApi.BotWriteAccessAllowReasonConnectedWebsite -> this.toDomain()
    is TdApi.BotWriteAccessAllowReasonAddedToAttachmentMenu -> this.toDomain()
    is TdApi.BotWriteAccessAllowReasonLaunchedWebApp -> this.toDomain()
    is TdApi.BotWriteAccessAllowReasonAcceptedRequest -> this.toDomain()
    else -> throw IllegalArgumentException("Unknown type: ${this.javaClass}")
}

fun TdApi.BotWriteAccessAllowReasonAcceptedRequest.toDomain(): BotWriteAccessAllowReasonAcceptedRequest = BotWriteAccessAllowReasonAcceptedRequest

fun TdApi.BotWriteAccessAllowReasonAddedToAttachmentMenu.toDomain(): BotWriteAccessAllowReasonAddedToAttachmentMenu = BotWriteAccessAllowReasonAddedToAttachmentMenu

fun TdApi.BotWriteAccessAllowReasonConnectedWebsite.toDomain(): BotWriteAccessAllowReasonConnectedWebsite = BotWriteAccessAllowReasonConnectedWebsite(
    domainName = this.domainName
)

fun TdApi.BotWriteAccessAllowReasonLaunchedWebApp.toDomain(): BotWriteAccessAllowReasonLaunchedWebApp = BotWriteAccessAllowReasonLaunchedWebApp(
    webApp = this.webApp.toDomain()
)

