package com.xxcactussell.presentation.localization

fun localizedString(key: String, quantity: Long = 1, vararg args: Any): String {
    return LocalizationManager.getStringGlobal(key, quantity, *args)
}

fun localizedString(key: String) : String {
    return LocalizationManager.getStringGlobal(key)
}

fun localizedString(key: String, quantity: Int = 1, vararg args: Any): String {
    return LocalizationManager.getStringGlobal(key, quantity.toLong(), *args)
}

fun String.localized(quantity: Long = 1, vararg args: Any): String {
    return LocalizationManager.getStringGlobal(this, quantity, *args)
}