package com.xxcactussell.data.utils.mappers.poll

import com.xxcactussell.data.utils.mappers.formatted.toDomain
import com.xxcactussell.data.utils.mappers.message.toDomain
import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: TdApi -> Domain

fun TdApi.Poll.toDomain(): Poll = Poll(
    id = this.id,
    question = this.question.toDomain(),
    options = this.options.map { it.toDomain() },
    totalVoterCount = this.totalVoterCount,
    recentVoterIds = this.recentVoterIds.map { it.toDomain() },
    isAnonymous = this.isAnonymous,
    type = this.type.toDomain(),
    openPeriod = this.openPeriod,
    closeDate = this.closeDate,
    isClosed = this.isClosed
)

fun TdApi.PollOption.toDomain(): PollOption = PollOption(
    text = this.text.toDomain(),
    voterCount = this.voterCount,
    votePercentage = this.votePercentage,
    isChosen = this.isChosen,
    isBeingChosen = this.isBeingChosen
)

fun TdApi.PollType.toDomain(): PollType = when(this) {
    is TdApi.PollTypeRegular -> this.toDomain()
    is TdApi.PollTypeQuiz -> this.toDomain()
    else -> throw IllegalArgumentException("Unknown type: ${this.javaClass}")
}

fun TdApi.PollTypeQuiz.toDomain(): PollTypeQuiz = PollTypeQuiz(
    correctOptionId = this.correctOptionId,
    explanation = this.explanation.toDomain()
)

fun TdApi.PollTypeRegular.toDomain(): PollTypeRegular = PollTypeRegular(
    allowMultipleAnswers = this.allowMultipleAnswers
)

