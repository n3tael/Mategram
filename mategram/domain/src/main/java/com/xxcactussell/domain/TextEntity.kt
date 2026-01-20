package com.xxcactussell.domain

data class TextEntity(
    val offset: Int,
    val length: Int,
    val type: TextEntityType
) : Object
