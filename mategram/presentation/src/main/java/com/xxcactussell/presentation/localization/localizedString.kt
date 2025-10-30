package com.xxcactussell.presentation.localization

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.xxcactussell.presentation.LocalLocalizationManager

@SuppressLint("LocalContextResourcesRead", "DiscouragedApi")
@Composable
fun localizedString(key: String, quantity: Long = 1, vararg args: Any): String {
    val context = LocalContext.current
    val manager = LocalLocalizationManager.current

    val translated = manager.getString(context = context, key = key, quantity = quantity, args = args)

    return if (translated == key) {
        try {
            val resId = context.resources.getIdentifier(key, "string", context.packageName)
            if (resId != 0) context.getString(resId, *args) else key
        } catch (e: Exception) {
            key
        }
    } else {
        translated
    }
}