package com.xxcactussell.domain

data class BankCardInfo(
    val title: String,
    val actions: List<BankCardActionOpenUrl>
) : Object
