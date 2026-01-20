package com.xxcactussell.domain

data class IdentityDocument(
    val number: String,
    val expirationDate: Date? = null,
    val frontSide: DatedFile,
    val reverseSide: DatedFile? = null,
    val selfie: DatedFile? = null,
    val translation: List<DatedFile>
) : Object
