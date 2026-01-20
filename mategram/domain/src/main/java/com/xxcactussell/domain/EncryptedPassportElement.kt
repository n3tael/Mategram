package com.xxcactussell.domain

data class EncryptedPassportElement(
    val type: PassportElementType,
    val data: ByteArray,
    val frontSide: DatedFile,
    val reverseSide: DatedFile? = null,
    val selfie: DatedFile? = null,
    val translation: List<DatedFile>,
    val files: List<DatedFile>,
    val value: String,
    val hash: String
) : Object
