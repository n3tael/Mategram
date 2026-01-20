package com.xxcactussell.domain

data class NotificationSound(
    val id: Long,
    val duration: Int,
    val date: Int,
    val title: String,
    val data: String,
    val sound: File
) : Object
