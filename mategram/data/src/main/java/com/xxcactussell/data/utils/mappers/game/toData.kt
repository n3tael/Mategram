package com.xxcactussell.data.utils.mappers.game

import com.xxcactussell.data.utils.mappers.animation.toData
import com.xxcactussell.data.utils.mappers.formatted.toData
import com.xxcactussell.data.utils.mappers.photo.toData
import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: Domain -> TdApi

fun Game.toData(): TdApi.Game = TdApi.Game(
    this.id,
    this.shortName,
    this.title,
    this.text.toData(),
    this.description,
    this.photo.toData(),
    this.animation?.toData()
)

fun GameHighScore.toData(): TdApi.GameHighScore = TdApi.GameHighScore(
    this.position,
    this.userId,
    this.score
)

fun GameHighScores.toData(): TdApi.GameHighScores = TdApi.GameHighScores(
    this.scores.map { it.toData() }.toTypedArray()
)

