package com.xxcactussell.domain

data class BotInfo(
    val shortDescription: String,
    val description: String,
    val photo: Photo? = null,
    val animation: Animation? = null,
    val menuButton: BotMenuButton? = null,
    val commands: List<BotCommand>,
    val privacyPolicyUrl: String,
    val defaultGroupAdministratorRights: ChatAdministratorRights? = null,
    val defaultChannelAdministratorRights: ChatAdministratorRights? = null,
    val affiliateProgram: AffiliateProgramInfo? = null,
    val webAppBackgroundLightColor: Int,
    val webAppBackgroundDarkColor: Int,
    val webAppHeaderLightColor: Int,
    val webAppHeaderDarkColor: Int,
    val verificationParameters: BotVerificationParameters? = null,
    val canGetRevenueStatistics: Boolean,
    val canManageEmojiStatus: Boolean,
    val hasMediaPreviews: Boolean,
    val editCommandsLink: InternalLinkType? = null,
    val editDescriptionLink: InternalLinkType? = null,
    val editDescriptionMediaLink: InternalLinkType? = null,
    val editSettingsLink: InternalLinkType? = null
) : Object
