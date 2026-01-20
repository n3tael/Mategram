package com.xxcactussell.domain

data class PersonalDocument(
    val files: List<DatedFile>,
    val translation: List<DatedFile>
) : Object
