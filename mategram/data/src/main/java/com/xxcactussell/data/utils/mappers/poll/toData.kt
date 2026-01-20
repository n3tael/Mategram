package com.xxcactussell.data.utils.mappers.poll

import com.xxcactussell.data.utils.mappers.formatted.toData
import com.xxcactussell.data.utils.mappers.message.toData
import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: Domain -> TdApi

fun Poll.toData(): TdApi.Poll = TdApi.Poll(
    this.id,
    this.question.toData(),
    this.options.map { it.toData() }.toTypedArray(),
    this.totalVoterCount,
    this.recentVoterIds.map { it.toData() }.toTypedArray(),
    this.isAnonymous,
    this.type.toData(),
    this.openPeriod,
    this.closeDate,
    this.isClosed
)

fun PollOption.toData(): TdApi.PollOption = TdApi.PollOption(
    this.text.toData(),
    this.voterCount,
    this.votePercentage,
    this.isChosen,
    this.isBeingChosen
)

fun PollType.toData(): TdApi.PollType = when(this) {
    is PollTypeRegular -> this.toData()
    is PollTypeQuiz -> this.toData()
}

fun PollTypeQuiz.toData(): TdApi.PollTypeQuiz = TdApi.PollTypeQuiz(
    this.correctOptionId,
    this.explanation.toData()
)

fun PollTypeRegular.toData(): TdApi.PollTypeRegular = TdApi.PollTypeRegular(
    this.allowMultipleAnswers
)

