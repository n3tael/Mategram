package com.xxcactussell.domain

data class TermsOfService(
    val text: FormattedText,
    val minUserAge: Int,
    val showPopup: Boolean
) : Object
