package com.xxcactussell.domain

data class SearchMessages(
    val chatList: ChatList,
    val query: String,
    val offset: String,
    val limit: Int,
    val filter: SearchMessagesFilter,
    val chatTypeFilter: SearchMessagesChatTypeFilter,
    val minDate: Int,
    val maxDate: Int
) : Function
