package com.xxcactussell.domain

data class CancelDownloadFile(
    val fileId: Int,
    val onlyIfPending: Boolean
) : Function
