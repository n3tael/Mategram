package com.xxcactussell.data.utils.mappers.reaction

import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: TdApi -> Domain

fun TdApi.ReactionNotificationSettings.toDomain(): ReactionNotificationSettings = ReactionNotificationSettings(
    messageReactionSource = this.messageReactionSource.toDomain(),
    storyReactionSource = this.storyReactionSource.toDomain(),
    soundId = this.soundId,
    showPreview = this.showPreview
)

fun TdApi.ReactionNotificationSource.toDomain(): ReactionNotificationSource = when(this) {
    is TdApi.ReactionNotificationSourceNone -> this.toDomain()
    is TdApi.ReactionNotificationSourceContacts -> this.toDomain()
    is TdApi.ReactionNotificationSourceAll -> this.toDomain()
    else -> throw IllegalArgumentException("Unknown type: ${this.javaClass}")
}

fun TdApi.ReactionNotificationSourceAll.toDomain(): ReactionNotificationSourceAll = ReactionNotificationSourceAll

fun TdApi.ReactionNotificationSourceContacts.toDomain(): ReactionNotificationSourceContacts = ReactionNotificationSourceContacts

fun TdApi.ReactionNotificationSourceNone.toDomain(): ReactionNotificationSourceNone = ReactionNotificationSourceNone

fun TdApi.ReactionType.toDomain(): ReactionType = when(this) {
    is TdApi.ReactionTypeEmoji -> this.toDomain()
    is TdApi.ReactionTypeCustomEmoji -> this.toDomain()
    is TdApi.ReactionTypePaid -> this.toDomain()
    else -> throw IllegalArgumentException("Unknown type: ${this.javaClass}")
}

fun TdApi.ReactionTypeCustomEmoji.toDomain(): ReactionTypeCustomEmoji = ReactionTypeCustomEmoji(
    customEmojiId = this.customEmojiId
)

fun TdApi.ReactionTypeEmoji.toDomain(): ReactionTypeEmoji = ReactionTypeEmoji(
    emoji = this.emoji
)

fun TdApi.ReactionTypePaid.toDomain(): ReactionTypePaid = ReactionTypePaid

fun TdApi.ReactionUnavailabilityReason.toDomain(): ReactionUnavailabilityReason = when(this) {
    is TdApi.ReactionUnavailabilityReasonAnonymousAdministrator -> this.toDomain()
    is TdApi.ReactionUnavailabilityReasonGuest -> this.toDomain()
    else -> throw IllegalArgumentException("Unknown type: ${this.javaClass}")
}

fun TdApi.ReactionUnavailabilityReasonAnonymousAdministrator.toDomain(): ReactionUnavailabilityReasonAnonymousAdministrator = ReactionUnavailabilityReasonAnonymousAdministrator

fun TdApi.ReactionUnavailabilityReasonGuest.toDomain(): ReactionUnavailabilityReasonGuest = ReactionUnavailabilityReasonGuest

