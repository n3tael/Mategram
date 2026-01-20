package com.xxcactussell.data.utils.mappers.reaction

import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: Domain -> TdApi

fun ReactionNotificationSettings.toData(): TdApi.ReactionNotificationSettings = TdApi.ReactionNotificationSettings(
    this.messageReactionSource.toData(),
    this.storyReactionSource.toData(),
    this.soundId,
    this.showPreview
)

fun ReactionNotificationSource.toData(): TdApi.ReactionNotificationSource = when(this) {
    is ReactionNotificationSourceNone -> this.toData()
    is ReactionNotificationSourceContacts -> this.toData()
    is ReactionNotificationSourceAll -> this.toData()
}

fun ReactionNotificationSourceAll.toData(): TdApi.ReactionNotificationSourceAll = TdApi.ReactionNotificationSourceAll(
)

fun ReactionNotificationSourceContacts.toData(): TdApi.ReactionNotificationSourceContacts = TdApi.ReactionNotificationSourceContacts(
)

fun ReactionNotificationSourceNone.toData(): TdApi.ReactionNotificationSourceNone = TdApi.ReactionNotificationSourceNone(
)

fun ReactionType.toData(): TdApi.ReactionType = when(this) {
    is ReactionTypeEmoji -> this.toData()
    is ReactionTypeCustomEmoji -> this.toData()
    is ReactionTypePaid -> this.toData()
}

fun ReactionTypeCustomEmoji.toData(): TdApi.ReactionTypeCustomEmoji = TdApi.ReactionTypeCustomEmoji(
    this.customEmojiId
)

fun ReactionTypeEmoji.toData(): TdApi.ReactionTypeEmoji = TdApi.ReactionTypeEmoji(
    this.emoji
)

fun ReactionTypePaid.toData(): TdApi.ReactionTypePaid = TdApi.ReactionTypePaid(
)

fun ReactionUnavailabilityReason.toData(): TdApi.ReactionUnavailabilityReason = when(this) {
    is ReactionUnavailabilityReasonAnonymousAdministrator -> this.toData()
    is ReactionUnavailabilityReasonGuest -> this.toData()
}

fun ReactionUnavailabilityReasonAnonymousAdministrator.toData(): TdApi.ReactionUnavailabilityReasonAnonymousAdministrator = TdApi.ReactionUnavailabilityReasonAnonymousAdministrator(
)

fun ReactionUnavailabilityReasonGuest.toData(): TdApi.ReactionUnavailabilityReasonGuest = TdApi.ReactionUnavailabilityReasonGuest(
)

