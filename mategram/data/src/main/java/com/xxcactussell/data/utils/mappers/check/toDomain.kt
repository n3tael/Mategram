package com.xxcactussell.data.utils.mappers.check

import com.xxcactussell.data.utils.mappers.email.toDomain
import com.xxcactussell.data.utils.mappers.public.toDomain
import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: TdApi -> Domain

fun TdApi.CheckAuthenticationEmailCode.toDomain(): CheckAuthenticationEmailCode = CheckAuthenticationEmailCode(
    code = this.code.toDomain()
)

fun TdApi.CheckAuthenticationPremiumPurchase.toDomain(): CheckAuthenticationPremiumPurchase = CheckAuthenticationPremiumPurchase(
    currency = this.currency,
    amount = this.amount
)

fun TdApi.CheckChatInviteLink.toDomain(): CheckChatInviteLink = CheckChatInviteLink(
    inviteLink = this.inviteLink
)

fun TdApi.CheckChatUsername.toDomain(): CheckChatUsername = CheckChatUsername(
    chatId = this.chatId,
    username = this.username
)

fun TdApi.CheckChatUsernameResult.toDomain(): CheckChatUsernameResult = when(this) {
    is TdApi.CheckChatUsernameResultOk -> this.toDomain()
    is TdApi.CheckChatUsernameResultUsernameInvalid -> this.toDomain()
    is TdApi.CheckChatUsernameResultUsernameOccupied -> this.toDomain()
    is TdApi.CheckChatUsernameResultUsernamePurchasable -> this.toDomain()
    is TdApi.CheckChatUsernameResultPublicChatsTooMany -> this.toDomain()
    is TdApi.CheckChatUsernameResultPublicGroupsUnavailable -> this.toDomain()
    else -> throw IllegalArgumentException("Unknown type: ${this.javaClass}")
}

fun TdApi.CheckChatUsernameResultOk.toDomain(): CheckChatUsernameResultOk = CheckChatUsernameResultOk

fun TdApi.CheckChatUsernameResultPublicChatsTooMany.toDomain(): CheckChatUsernameResultPublicChatsTooMany = CheckChatUsernameResultPublicChatsTooMany

fun TdApi.CheckChatUsernameResultPublicGroupsUnavailable.toDomain(): CheckChatUsernameResultPublicGroupsUnavailable = CheckChatUsernameResultPublicGroupsUnavailable

fun TdApi.CheckChatUsernameResultUsernameInvalid.toDomain(): CheckChatUsernameResultUsernameInvalid = CheckChatUsernameResultUsernameInvalid

fun TdApi.CheckChatUsernameResultUsernameOccupied.toDomain(): CheckChatUsernameResultUsernameOccupied = CheckChatUsernameResultUsernameOccupied

fun TdApi.CheckChatUsernameResultUsernamePurchasable.toDomain(): CheckChatUsernameResultUsernamePurchasable = CheckChatUsernameResultUsernamePurchasable

fun TdApi.CheckCreatedPublicChatsLimit.toDomain(): CheckCreatedPublicChatsLimit = CheckCreatedPublicChatsLimit(
    type = this.type.toDomain()
)

fun TdApi.CheckEmailAddressVerificationCode.toDomain(): CheckEmailAddressVerificationCode = CheckEmailAddressVerificationCode(
    code = this.code
)

fun TdApi.CheckLoginEmailAddressCode.toDomain(): CheckLoginEmailAddressCode = CheckLoginEmailAddressCode(
    code = this.code.toDomain()
)

fun TdApi.CheckPasswordRecoveryCode.toDomain(): CheckPasswordRecoveryCode = CheckPasswordRecoveryCode(
    recoveryCode = this.recoveryCode
)

fun TdApi.CheckPhoneNumberCode.toDomain(): CheckPhoneNumberCode = CheckPhoneNumberCode(
    code = this.code
)

fun TdApi.CheckPremiumGiftCode.toDomain(): CheckPremiumGiftCode = CheckPremiumGiftCode(
    code = this.code
)

fun TdApi.CheckQuickReplyShortcutName.toDomain(): CheckQuickReplyShortcutName = CheckQuickReplyShortcutName(
    name = this.name
)

fun TdApi.CheckRecoveryEmailAddressCode.toDomain(): CheckRecoveryEmailAddressCode = CheckRecoveryEmailAddressCode(
    code = this.code
)

fun TdApi.CheckStickerSetName.toDomain(): CheckStickerSetName = CheckStickerSetName(
    name = this.name
)

fun TdApi.CheckStickerSetNameResult.toDomain(): CheckStickerSetNameResult = when(this) {
    is TdApi.CheckStickerSetNameResultOk -> this.toDomain()
    is TdApi.CheckStickerSetNameResultNameInvalid -> this.toDomain()
    is TdApi.CheckStickerSetNameResultNameOccupied -> this.toDomain()
    else -> throw IllegalArgumentException("Unknown type: ${this.javaClass}")
}

fun TdApi.CheckStickerSetNameResultNameInvalid.toDomain(): CheckStickerSetNameResultNameInvalid = CheckStickerSetNameResultNameInvalid

fun TdApi.CheckStickerSetNameResultNameOccupied.toDomain(): CheckStickerSetNameResultNameOccupied = CheckStickerSetNameResultNameOccupied

fun TdApi.CheckStickerSetNameResultOk.toDomain(): CheckStickerSetNameResultOk = CheckStickerSetNameResultOk

fun TdApi.CheckWebAppFileDownload.toDomain(): CheckWebAppFileDownload = CheckWebAppFileDownload(
    botUserId = this.botUserId,
    fileName = this.fileName,
    url = this.url
)

