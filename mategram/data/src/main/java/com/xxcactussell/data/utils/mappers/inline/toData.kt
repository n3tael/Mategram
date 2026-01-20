package com.xxcactussell.data.utils.mappers.inline

import com.xxcactussell.data.utils.mappers.animation.toData
import com.xxcactussell.data.utils.mappers.file.toData
import com.xxcactussell.data.utils.mappers.game.toData
import com.xxcactussell.data.utils.mappers.location.toData
import com.xxcactussell.data.utils.mappers.photo.toData
import com.xxcactussell.data.utils.mappers.sticker.toData
import com.xxcactussell.data.utils.mappers.user.toData
import com.xxcactussell.data.utils.mappers.venue.toData
import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: Domain -> TdApi

fun InlineQueryResult.toData(): TdApi.InlineQueryResult = when(this) {
    is InlineQueryResultArticle -> this.toData()
    is InlineQueryResultContact -> this.toData()
    is InlineQueryResultLocation -> this.toData()
    is InlineQueryResultVenue -> this.toData()
    is InlineQueryResultGame -> this.toData()
    is InlineQueryResultAnimation -> this.toData()
    is InlineQueryResultAudio -> this.toData()
    is InlineQueryResultDocument -> this.toData()
    is InlineQueryResultPhoto -> this.toData()
    is InlineQueryResultSticker -> this.toData()
    is InlineQueryResultVideo -> this.toData()
    is InlineQueryResultVoiceNote -> this.toData()
}

fun InlineQueryResultAnimation.toData(): TdApi.InlineQueryResultAnimation = TdApi.InlineQueryResultAnimation(
    this.id,
    this.animation.toData(),
    this.title
)

fun InlineQueryResultArticle.toData(): TdApi.InlineQueryResultArticle = TdApi.InlineQueryResultArticle(
    this.id,
    this.url,
    this.title,
    this.description,
    this.thumbnail?.toData()
)

fun InlineQueryResultAudio.toData(): TdApi.InlineQueryResultAudio = TdApi.InlineQueryResultAudio(
    this.id,
    this.audio.toData()
)

fun InlineQueryResultContact.toData(): TdApi.InlineQueryResultContact = TdApi.InlineQueryResultContact(
    this.id,
    this.contact.toData(),
    this.thumbnail?.toData()
)

fun InlineQueryResultDocument.toData(): TdApi.InlineQueryResultDocument = TdApi.InlineQueryResultDocument(
    this.id,
    this.document.toData(),
    this.title,
    this.description
)

fun InlineQueryResultGame.toData(): TdApi.InlineQueryResultGame = TdApi.InlineQueryResultGame(
    this.id,
    this.game.toData()
)

fun InlineQueryResultLocation.toData(): TdApi.InlineQueryResultLocation = TdApi.InlineQueryResultLocation(
    this.id,
    this.location.toData(),
    this.title,
    this.thumbnail?.toData()
)

fun InlineQueryResultPhoto.toData(): TdApi.InlineQueryResultPhoto = TdApi.InlineQueryResultPhoto(
    this.id,
    this.photo.toData(),
    this.title,
    this.description
)

fun InlineQueryResultSticker.toData(): TdApi.InlineQueryResultSticker = TdApi.InlineQueryResultSticker(
    this.id,
    this.sticker.toData()
)

fun InlineQueryResultVenue.toData(): TdApi.InlineQueryResultVenue = TdApi.InlineQueryResultVenue(
    this.id,
    this.venue.toData(),
    this.thumbnail?.toData()
)

fun InlineQueryResultVideo.toData(): TdApi.InlineQueryResultVideo = TdApi.InlineQueryResultVideo(
    this.id,
    this.video.toData(),
    this.title,
    this.description
)

fun InlineQueryResultVoiceNote.toData(): TdApi.InlineQueryResultVoiceNote = TdApi.InlineQueryResultVoiceNote(
    this.id,
    this.voiceNote.toData(),
    this.title
)

fun InlineQueryResults.toData(): TdApi.InlineQueryResults = TdApi.InlineQueryResults(
    this.inlineQueryId,
    this.button?.toData(),
    this.results.map { it.toData() }.toTypedArray(),
    this.nextOffset
)

fun InlineQueryResultsButton.toData(): TdApi.InlineQueryResultsButton = TdApi.InlineQueryResultsButton(
    this.text,
    this.type.toData()
)

fun InlineQueryResultsButtonType.toData(): TdApi.InlineQueryResultsButtonType = when(this) {
    is InlineQueryResultsButtonTypeStartBot -> this.toData()
    is InlineQueryResultsButtonTypeWebApp -> this.toData()
}

fun InlineQueryResultsButtonTypeStartBot.toData(): TdApi.InlineQueryResultsButtonTypeStartBot = TdApi.InlineQueryResultsButtonTypeStartBot(
    this.parameter
)

fun InlineQueryResultsButtonTypeWebApp.toData(): TdApi.InlineQueryResultsButtonTypeWebApp = TdApi.InlineQueryResultsButtonTypeWebApp(
    this.url
)

