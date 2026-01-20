package com.xxcactussell.domain

data class ChatEventIsAllHistoryAvailableToggled(
    val isAllHistoryAvailable: Boolean
) : ChatEventAction
