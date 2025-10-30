package com.xxcactussell.data.utils.todomain

import com.xxcactussell.data.utils.toAlternativeDomain
import com.xxcactussell.data.utils.toDomain
import com.xxcactussell.domain.messages.model.MessageContent
import org.drinkless.tdlib.TdApi

fun TdApi.MessageContent.toDomain() : MessageContent {
    return when(this.constructor) {
        TdApi.MessageText.CONSTRUCTOR -> {
            val content = this as TdApi.MessageText
            MessageContent.Text(
                text = content.text.toDomain(),
                linkPreview = content.linkPreview?.toDomain(),
                linkOption = content.linkPreviewOptions?.toDomain()
            )
        }
        TdApi.MessagePhoto.CONSTRUCTOR -> {
            val content = this as TdApi.MessagePhoto
            MessageContent.MessagePhoto(
                photo = content.photo.toDomain(),
                caption = content.caption.toDomain(),
                showCaptionAboveMedia = content.showCaptionAboveMedia,
                hasSpoiler = content.hasSpoiler,
                isSecret = content.isSecret
            )
        }

        TdApi.MessageVideo.CONSTRUCTOR -> {
            val content = this as TdApi.MessageVideo
            MessageContent.MessageVideo(
                video = content.video.toDomain(),
                alternativeVideo = content.alternativeVideos.map { it.toAlternativeDomain() },
                cover = content.cover?.toDomain(),
                startTime = content.startTimestamp,
                caption = content.caption.toDomain(),
                showCaptionAboveMedia = content.showCaptionAboveMedia,
                hasSpoiler = content.hasSpoiler,
                isSecret = content.isSecret
            )
        }

        TdApi.MessageAudio.CONSTRUCTOR -> {
            val content = this as TdApi.MessageAudio
            MessageContent.MessageAudio(
                audio = content.audio.toDomain(),
                caption = content.caption.toDomain()
            )
        }

        TdApi.MessageDocument.CONSTRUCTOR -> {
            val content = this as TdApi.MessageDocument
            MessageContent.MessageDocument(
                document = content.document.toDomain(),
                caption = content.caption.toDomain()
            )
        }

        TdApi.MessageAnimatedEmoji.CONSTRUCTOR -> {
            val content = this as TdApi.MessageAnimatedEmoji
            MessageContent.MessageAnimatedEmoji(
                animatedEmoji = content.animatedEmoji.toDomain(),
                emoji = content.emoji
            )
        }

        TdApi.MessageDice.CONSTRUCTOR -> {
            val content = this as TdApi.MessageDice
            MessageContent.MessageDice(
                initialState = content.initialState?.toDomain(),
                finalState = content.finalState?.toDomain(),
                value = content.value,
                emoji = content.emoji,
                successAnimationFrameNumber = content.successAnimationFrameNumber
            )
        }

        TdApi.MessageSticker.CONSTRUCTOR -> {
            val content = this as TdApi.MessageSticker
            MessageContent.MessageSticker(
                sticker = content.sticker.toDomain(),
                isPremium = content.isPremium
            )
        }

        TdApi.MessageAnimation.CONSTRUCTOR -> {
            val content = this as TdApi.MessageAnimation
            MessageContent.MessageAnimation(
                fileId = content.animation.animation.id.toString()
            )
        }

        TdApi.MessageLocation.CONSTRUCTOR -> {
            val content = this as TdApi.MessageLocation
            MessageContent.MessageLocation(
                latitude = content.location.latitude,
                longitude = content.location.longitude
            )
        }

        TdApi.MessageContact.CONSTRUCTOR -> {
            val content = this as TdApi.MessageContact
            MessageContent.MessageContact(
                name = "${content.contact.firstName} ${content.contact.lastName}",
                phoneNumber = content.contact.phoneNumber
            )
        }

        TdApi.MessagePoll.CONSTRUCTOR -> {
            val content = this as TdApi.MessagePoll
            MessageContent.MessagePoll(
                question = content.poll.question.text
            )
        }

        TdApi.MessageGame.CONSTRUCTOR -> {
            val content = this as TdApi.MessageGame
            MessageContent.MessageGame(
                title = content.game.title
            )
        }

        //TODO

        TdApi.MessageChatAddMembers.CONSTRUCTOR, TdApi.MessageChatJoinByLink.CONSTRUCTOR -> {
            MessageContent.MemberJoined(memberId = 0L)
        }

        TdApi.MessageChatDeleteMember.CONSTRUCTOR -> {
            MessageContent.MemberLeft(memberId = 0L)
        }

        TdApi.MessageChatChangeTitle.CONSTRUCTOR -> {
            val content = this as TdApi.MessageChatChangeTitle
            MessageContent.ChatTitleChanged(newTitle = content.title)
        }

        TdApi.MessagePinMessage.CONSTRUCTOR -> MessageContent.MessagePinned(messageId = 0L)

        TdApi.MessageScreenshotTaken.CONSTRUCTOR -> MessageContent.ScreenshotTaken(userNames = emptyList())

        TdApi.MessageVideoChatEnded.CONSTRUCTOR -> {
            val content = this as TdApi.MessageVideoChatEnded
            MessageContent.CallEnded(duration = content.duration)
        }

        TdApi.MessageVideoChatStarted.CONSTRUCTOR -> MessageContent.CallStarted

        TdApi.MessageVideoChatScheduled.CONSTRUCTOR -> {
            val content = this as TdApi.MessageVideoChatScheduled
            MessageContent.CallScheduled(time = content.startDate)
        }

        TdApi.MessagePaymentSuccessful.CONSTRUCTOR, TdApi.MessagePaymentSuccessfulBot.CONSTRUCTOR -> {
            MessageContent.PaymentSuccessful(invoice = "Payment Details")
        }

        TdApi.MessageGiftedPremium.CONSTRUCTOR -> MessageContent.GiftedPremium(currency = "USD")

        TdApi.MessageGiveaway.CONSTRUCTOR, TdApi.MessageGiveawayCompleted.CONSTRUCTOR -> MessageContent.Giveaway(details = "Giveaway Status")

        TdApi.MessageExpiredPhoto.CONSTRUCTOR, TdApi.MessageExpiredVideo.CONSTRUCTOR,
        TdApi.MessageExpiredVideoNote.CONSTRUCTOR, TdApi.MessageExpiredVoiceNote.CONSTRUCTOR -> MessageContent.Expired

        TdApi.MessageUnsupported.CONSTRUCTOR -> MessageContent.Unsupported

        else -> MessageContent.Unsupported
    }
}