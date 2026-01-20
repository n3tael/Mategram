package com.xxcactussell.domain

data class InputPersonalDocument(
    val files: List<InputFile>,
    val translation: List<InputFile>
) : Object
