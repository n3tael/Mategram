package com.xxcactussell.domain

data class ChatStatisticsSupergroup(
    val period: DateRange,
    val memberCount: StatisticalValue,
    val messageCount: StatisticalValue,
    val viewerCount: StatisticalValue,
    val senderCount: StatisticalValue,
    val memberCountGraph: StatisticalGraph,
    val joinGraph: StatisticalGraph,
    val joinBySourceGraph: StatisticalGraph,
    val languageGraph: StatisticalGraph,
    val messageContentGraph: StatisticalGraph,
    val actionGraph: StatisticalGraph,
    val dayGraph: StatisticalGraph,
    val weekGraph: StatisticalGraph,
    val topSenders: List<ChatStatisticsMessageSenderInfo>,
    val topAdministrators: List<ChatStatisticsAdministratorActionsInfo>,
    val topInviters: List<ChatStatisticsInviterInfo>
) : ChatStatistics
