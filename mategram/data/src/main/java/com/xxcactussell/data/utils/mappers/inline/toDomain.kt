package com.xxcactussell.data.utils.mappers.inline

import com.xxcactussell.data.utils.mappers.animation.toDomain
import com.xxcactussell.data.utils.mappers.file.toDomain
import com.xxcactussell.data.utils.mappers.game.toDomain
import com.xxcactussell.data.utils.mappers.location.toDomain
import com.xxcactussell.data.utils.mappers.photo.toDomain
import com.xxcactussell.data.utils.mappers.sticker.toDomain
import com.xxcactussell.data.utils.mappers.user.toDomain
import com.xxcactussell.data.utils.mappers.venue.toDomain
import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: TdApi -> Domain

fun TdApi.InlineQueryResult.toDomain(): InlineQueryResult = when(this) {
    is TdApi.InlineQueryResultArticle -> this.toDomain()
    is TdApi.InlineQueryResultContact -> this.toDomain()
    is TdApi.InlineQueryResultLocation -> this.toDomain()
    is TdApi.InlineQueryResultVenue -> this.toDomain()
    is TdApi.InlineQueryResultGame -> this.toDomain()
    is TdApi.InlineQueryResultAnimation -> this.toDomain()
    is TdApi.InlineQueryResultAudio -> this.toDomain()
    is TdApi.InlineQueryResultDocument -> this.toDomain()
    is TdApi.InlineQueryResultPhoto -> this.toDomain()
    is TdApi.InlineQueryResultSticker -> this.toDomain()
    is TdApi.InlineQueryResultVideo -> this.toDomain()
    is TdApi.InlineQueryResultVoiceNote -> this.toDomain()
    else -> throw IllegalArgumentException("Unknown type: ${this.javaClass}")
}

fun TdApi.InlineQueryResultAnimation.toDomain(): InlineQueryResultAnimation = InlineQueryResultAnimation(
    id = this.id,
    animation = this.animation.toDomain(),
    title = this.title
)

fun TdApi.InlineQueryResultArticle.toDomain(): InlineQueryResultArticle = InlineQueryResultArticle(
    id = this.id,
    url = this.url,
    title = this.title,
    description = this.description,
    thumbnail = this.thumbnail?.toDomain()
)

fun TdApi.InlineQueryResultAudio.toDomain(): InlineQueryResultAudio = InlineQueryResultAudio(
    id = this.id,
    audio = this.audio.toDomain()
)

fun TdApi.InlineQueryResultContact.toDomain(): InlineQueryResultContact = InlineQueryResultContact(
    id = this.id,
    contact = this.contact.toDomain(),
    thumbnail = this.thumbnail?.toDomain()
)

fun TdApi.InlineQueryResultDocument.toDomain(): InlineQueryResultDocument = InlineQueryResultDocument(
    id = this.id,
    document = this.document.toDomain(),
    title = this.title,
    description = this.description
)

fun TdApi.InlineQueryResultGame.toDomain(): InlineQueryResultGame = InlineQueryResultGame(
    id = this.id,
    game = this.game.toDomain()
)

fun TdApi.InlineQueryResultLocation.toDomain(): InlineQueryResultLocation = InlineQueryResultLocation(
    id = this.id,
    location = this.location.toDomain(),
    title = this.title,
    thumbnail = this.thumbnail?.toDomain()
)

fun TdApi.InlineQueryResultPhoto.toDomain(): InlineQueryResultPhoto = InlineQueryResultPhoto(
    id = this.id,
    photo = this.photo.toDomain(),
    title = this.title,
    description = this.description
)

fun TdApi.InlineQueryResultSticker.toDomain(): InlineQueryResultSticker = InlineQueryResultSticker(
    id = this.id,
    sticker = this.sticker.toDomain()
)

fun TdApi.InlineQueryResultVenue.toDomain(): InlineQueryResultVenue = InlineQueryResultVenue(
    id = this.id,
    venue = this.venue.toDomain(),
    thumbnail = this.thumbnail?.toDomain()
)

fun TdApi.InlineQueryResultVideo.toDomain(): InlineQueryResultVideo = InlineQueryResultVideo(
    id = this.id,
    video = this.video.toDomain(),
    title = this.title,
    description = this.description
)

fun TdApi.InlineQueryResultVoiceNote.toDomain(): InlineQueryResultVoiceNote = InlineQueryResultVoiceNote(
    id = this.id,
    voiceNote = this.voiceNote.toDomain(),
    title = this.title
)

fun TdApi.InlineQueryResults.toDomain(): InlineQueryResults = InlineQueryResults(
    inlineQueryId = this.inlineQueryId,
    button = this.button?.toDomain(),
    results = this.results.map { it.toDomain() },
    nextOffset = this.nextOffset
)

fun TdApi.InlineQueryResultsButton.toDomain(): InlineQueryResultsButton = InlineQueryResultsButton(
    text = this.text,
    type = this.type.toDomain()
)

fun TdApi.InlineQueryResultsButtonType.toDomain(): InlineQueryResultsButtonType = when(this) {
    is TdApi.InlineQueryResultsButtonTypeStartBot -> this.toDomain()
    is TdApi.InlineQueryResultsButtonTypeWebApp -> this.toDomain()
    else -> throw IllegalArgumentException("Unknown type: ${this.javaClass}")
}

fun TdApi.InlineQueryResultsButtonTypeStartBot.toDomain(): InlineQueryResultsButtonTypeStartBot = InlineQueryResultsButtonTypeStartBot(
    parameter = this.parameter
)

fun TdApi.InlineQueryResultsButtonTypeWebApp.toDomain(): InlineQueryResultsButtonTypeWebApp = InlineQueryResultsButtonTypeWebApp(
    url = this.url
)

