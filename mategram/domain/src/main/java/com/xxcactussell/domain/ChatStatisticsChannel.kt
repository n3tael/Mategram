package com.xxcactussell.domain

data class ChatStatisticsChannel(
    val period: DateRange,
    val memberCount: StatisticalValue,
    val meanMessageViewCount: StatisticalValue,
    val meanMessageShareCount: StatisticalValue,
    val meanMessageReactionCount: StatisticalValue,
    val meanStoryViewCount: StatisticalValue,
    val meanStoryShareCount: StatisticalValue,
    val meanStoryReactionCount: StatisticalValue,
    val enabledNotificationsPercentage: Double,
    val memberCountGraph: StatisticalGraph,
    val joinGraph: StatisticalGraph,
    val muteGraph: StatisticalGraph,
    val viewCountByHourGraph: StatisticalGraph,
    val viewCountBySourceGraph: StatisticalGraph,
    val joinBySourceGraph: StatisticalGraph,
    val languageGraph: StatisticalGraph,
    val messageInteractionGraph: StatisticalGraph,
    val messageReactionGraph: StatisticalGraph,
    val storyInteractionGraph: StatisticalGraph,
    val storyReactionGraph: StatisticalGraph,
    val instantViewInteractionGraph: StatisticalGraph,
    val recentInteractions: List<ChatStatisticsInteractionInfo>
) : ChatStatistics
