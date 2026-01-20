package com.xxcactussell.domain

data class MessageStatistics(
    val messageInteractionGraph: StatisticalGraph,
    val messageReactionGraph: StatisticalGraph
) : Object
