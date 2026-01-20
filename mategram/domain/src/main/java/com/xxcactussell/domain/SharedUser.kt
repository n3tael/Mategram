package com.xxcactussell.domain

data class SharedUser(
    val userId: Long,
    val firstName: String,
    val lastName: String,
    val username: String,
    val photo: Photo? = null
) : Object
