package com.xxcactussell.domain

data class RemoteFile(
    val id: String,
    val uniqueId: String,
    val isUploadingActive: Boolean,
    val isUploadingCompleted: Boolean,
    val uploadedSize: Long
) : Object
