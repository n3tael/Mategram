package com.xxcactussell.presentation.localization

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import com.xxcactussell.repositories.localization.model.LanguagePack
import com.xxcactussell.repositories.localization.model.TranslationValue
import com.xxcactussell.repositories.localization.repository.GetAvailableLanguagePacksUseCase
import com.xxcactussell.repositories.localization.repository.GetLanguageIdUseCase
import com.xxcactussell.repositories.localization.repository.GetLanguageStringsUseCase
import com.xxcactussell.repositories.localization.repository.SaveLanguageIdUseCase
import com.xxcactussell.repositories.localization.repository.SynchronizeLanguageStringsUseCase
import dagger.hilt.android.qualifiers.ApplicationContext
import jakarta.inject.Inject
import jakarta.inject.Singleton
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

@Singleton
class LocalizationManager @Inject constructor(
    @param:ApplicationContext private val context: Context,
    private val getLanguageStringsUseCase: GetLanguageStringsUseCase,
    private val getAvailableLanguagePacksUseCase: GetAvailableLanguagePacksUseCase,
    private val synchronizeLanguageStringsUseCase: SynchronizeLanguageStringsUseCase,
    private val getLanguageIdUseCase: GetLanguageIdUseCase,
    private val saveLanguageIdUseCase: SaveLanguageIdUseCase
) {
    private val _translationMap = MutableStateFlow<Map<String, TranslationValue>>(emptyMap())
    val translationMap: StateFlow<Map<String, TranslationValue>> = _translationMap.asStateFlow()

    init {
        instance = this
    }

    companion object {
        lateinit var instance: LocalizationManager
            private set

        fun getStringGlobal(key: String, quantity: Long = 1, vararg args: Any): String {
            if (!::instance.isInitialized) return key
            return instance.getString(key, quantity, *args)
        }
    }

    fun initialize(context: Context, scope: CoroutineScope) {
        scope.launch(Dispatchers.IO) {
            val languageId = getLanguageIdUseCase(context)
            _translationMap.value = getLanguageStringsUseCase(languageId).first().associate { it.key to it.value }
            synchronizeLanguageStringsUseCase(languageId)
        }
    }

    fun saveLanguageId(languageId: String, context: Context) {
        saveLanguageIdUseCase(languageId, context)
    }

    @SuppressLint("DiscouragedApi")
    fun getString(key: String, quantity: Long = 1, vararg args: Any): String {
        val strings = _translationMap.value
        val translationValue = strings[key]

        val translated = when (translationValue) {
            is TranslationValue.Ordinary -> try {
                String.format(translationValue.value, *args)
            } catch (e: Exception) {
                translationValue.value
            }
            is TranslationValue.Pluralized -> {
                val pluralForm = PluralRulesResolver.getPluralForm(context, quantity, translationValue)
                try {
                    String.format(pluralForm, *args)
                } catch (e: Exception) {
                    pluralForm
                }
            }
            TranslationValue.Deleted, null -> null
        }

        if (translated == null) {
            return try {
                val resId = context.resources.getIdentifier(key, "string", context.packageName)
                if (resId != 0) {
                    context.getString(resId, *args)
                } else {
                    key
                }
            } catch (e: Exception) {
                key
            }
        }

        return translated
    }

    suspend fun fetchLanguagePacks() : List<LanguagePack> {
        return getAvailableLanguagePacksUseCase().first()
    }
}