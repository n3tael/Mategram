package com.xxcactussell.data.utils.mappers.check

import com.xxcactussell.data.utils.mappers.email.toData
import com.xxcactussell.data.utils.mappers.public.toData
import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: Domain -> TdApi

fun CheckAuthenticationEmailCode.toData(): TdApi.CheckAuthenticationEmailCode = TdApi.CheckAuthenticationEmailCode(
    this.code.toData()
)

fun CheckAuthenticationPremiumPurchase.toData(): TdApi.CheckAuthenticationPremiumPurchase = TdApi.CheckAuthenticationPremiumPurchase(
    this.currency,
    this.amount
)

fun CheckChatInviteLink.toData(): TdApi.CheckChatInviteLink = TdApi.CheckChatInviteLink(
    this.inviteLink
)

fun CheckChatUsername.toData(): TdApi.CheckChatUsername = TdApi.CheckChatUsername(
    this.chatId,
    this.username
)

fun CheckChatUsernameResult.toData(): TdApi.CheckChatUsernameResult = when(this) {
    is CheckChatUsernameResultOk -> this.toData()
    is CheckChatUsernameResultUsernameInvalid -> this.toData()
    is CheckChatUsernameResultUsernameOccupied -> this.toData()
    is CheckChatUsernameResultUsernamePurchasable -> this.toData()
    is CheckChatUsernameResultPublicChatsTooMany -> this.toData()
    is CheckChatUsernameResultPublicGroupsUnavailable -> this.toData()
}

fun CheckChatUsernameResultOk.toData(): TdApi.CheckChatUsernameResultOk = TdApi.CheckChatUsernameResultOk(
)

fun CheckChatUsernameResultPublicChatsTooMany.toData(): TdApi.CheckChatUsernameResultPublicChatsTooMany = TdApi.CheckChatUsernameResultPublicChatsTooMany(
)

fun CheckChatUsernameResultPublicGroupsUnavailable.toData(): TdApi.CheckChatUsernameResultPublicGroupsUnavailable = TdApi.CheckChatUsernameResultPublicGroupsUnavailable(
)

fun CheckChatUsernameResultUsernameInvalid.toData(): TdApi.CheckChatUsernameResultUsernameInvalid = TdApi.CheckChatUsernameResultUsernameInvalid(
)

fun CheckChatUsernameResultUsernameOccupied.toData(): TdApi.CheckChatUsernameResultUsernameOccupied = TdApi.CheckChatUsernameResultUsernameOccupied(
)

fun CheckChatUsernameResultUsernamePurchasable.toData(): TdApi.CheckChatUsernameResultUsernamePurchasable = TdApi.CheckChatUsernameResultUsernamePurchasable(
)

fun CheckCreatedPublicChatsLimit.toData(): TdApi.CheckCreatedPublicChatsLimit = TdApi.CheckCreatedPublicChatsLimit(
    this.type.toData()
)

fun CheckEmailAddressVerificationCode.toData(): TdApi.CheckEmailAddressVerificationCode = TdApi.CheckEmailAddressVerificationCode(
    this.code
)

fun CheckLoginEmailAddressCode.toData(): TdApi.CheckLoginEmailAddressCode = TdApi.CheckLoginEmailAddressCode(
    this.code.toData()
)

fun CheckPasswordRecoveryCode.toData(): TdApi.CheckPasswordRecoveryCode = TdApi.CheckPasswordRecoveryCode(
    this.recoveryCode
)

fun CheckPhoneNumberCode.toData(): TdApi.CheckPhoneNumberCode = TdApi.CheckPhoneNumberCode(
    this.code
)

fun CheckPremiumGiftCode.toData(): TdApi.CheckPremiumGiftCode = TdApi.CheckPremiumGiftCode(
    this.code
)

fun CheckQuickReplyShortcutName.toData(): TdApi.CheckQuickReplyShortcutName = TdApi.CheckQuickReplyShortcutName(
    this.name
)

fun CheckRecoveryEmailAddressCode.toData(): TdApi.CheckRecoveryEmailAddressCode = TdApi.CheckRecoveryEmailAddressCode(
    this.code
)

fun CheckStickerSetName.toData(): TdApi.CheckStickerSetName = TdApi.CheckStickerSetName(
    this.name
)

fun CheckStickerSetNameResult.toData(): TdApi.CheckStickerSetNameResult = when(this) {
    is CheckStickerSetNameResultOk -> this.toData()
    is CheckStickerSetNameResultNameInvalid -> this.toData()
    is CheckStickerSetNameResultNameOccupied -> this.toData()
}

fun CheckStickerSetNameResultNameInvalid.toData(): TdApi.CheckStickerSetNameResultNameInvalid = TdApi.CheckStickerSetNameResultNameInvalid(
)

fun CheckStickerSetNameResultNameOccupied.toData(): TdApi.CheckStickerSetNameResultNameOccupied = TdApi.CheckStickerSetNameResultNameOccupied(
)

fun CheckStickerSetNameResultOk.toData(): TdApi.CheckStickerSetNameResultOk = TdApi.CheckStickerSetNameResultOk(
)

fun CheckWebAppFileDownload.toData(): TdApi.CheckWebAppFileDownload = TdApi.CheckWebAppFileDownload(
    this.botUserId,
    this.fileName,
    this.url
)

