package com.xxcactussell.domain

data class InputIdentityDocument(
    val number: String,
    val expirationDate: Date,
    val frontSide: InputFile,
    val reverseSide: InputFile,
    val selfie: InputFile,
    val translation: List<InputFile>
) : Object
