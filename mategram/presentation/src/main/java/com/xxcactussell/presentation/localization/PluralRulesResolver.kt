package com.xxcactussell.presentation.localization

import android.content.Context
import android.icu.text.PluralRules
import com.xxcactussell.repositories.localization.model.TranslationValue

object PluralRulesResolver {
    fun getPluralForm(
        context: Context,
        quantity: Long,
        pluralizedValue: TranslationValue.Pluralized
    ): String {
        val currentLocale = context.resources.configuration.locales.get(0)
        val rule = PluralRules.forLocale(currentLocale)
        val keyword = rule.select(quantity.toDouble()).toString()
        return when (keyword) {
            "zero" -> pluralizedValue.zero ?: pluralizedValue.other
            "one" -> pluralizedValue.one ?: pluralizedValue.other
            "two" -> pluralizedValue.two ?: pluralizedValue.other
            "few" -> pluralizedValue.few ?: pluralizedValue.other
            "many" -> pluralizedValue.many ?: pluralizedValue.other
            else -> pluralizedValue.other
        }
    }
}