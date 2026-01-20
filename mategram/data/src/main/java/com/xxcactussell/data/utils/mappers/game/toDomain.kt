package com.xxcactussell.data.utils.mappers.game

import com.xxcactussell.data.utils.mappers.animation.toDomain
import com.xxcactussell.data.utils.mappers.formatted.toDomain
import com.xxcactussell.data.utils.mappers.photo.toDomain
import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: TdApi -> Domain

fun TdApi.Game.toDomain(): Game = Game(
    id = this.id,
    shortName = this.shortName,
    title = this.title,
    text = this.text.toDomain(),
    description = this.description,
    photo = this.photo.toDomain(),
    animation = this.animation?.toDomain()
)

fun TdApi.GameHighScore.toDomain(): GameHighScore = GameHighScore(
    position = this.position,
    userId = this.userId,
    score = this.score
)

fun TdApi.GameHighScores.toDomain(): GameHighScores = GameHighScores(
    scores = this.scores.map { it.toDomain() }
)

