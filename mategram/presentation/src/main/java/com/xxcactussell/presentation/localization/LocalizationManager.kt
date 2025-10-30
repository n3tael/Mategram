package com.xxcactussell.presentation.localization

import android.content.Context
import android.util.Log
import com.xxcactussell.domain.localization.model.LanguagePack
import com.xxcactussell.domain.localization.model.LocalizedResource
import com.xxcactussell.domain.localization.model.TranslationValue
import com.xxcactussell.domain.localization.repository.GetAvailableLanguagePacksUseCase
import com.xxcactussell.domain.localization.repository.GetLanguageIdUseCase
import com.xxcactussell.domain.localization.repository.GetLanguageStringsUseCase
import com.xxcactussell.domain.localization.repository.SaveLanguageIdUseCase
import com.xxcactussell.domain.localization.repository.SynchronizeLanguageStringsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import jakarta.inject.Singleton
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import org.intellij.lang.annotations.Language

@Singleton
class LocalizationManager @Inject constructor(
    private val getLanguageStringsUseCase: GetLanguageStringsUseCase,
    private val getAvailableLanguagePacksUseCase: GetAvailableLanguagePacksUseCase,
    private val synchronizeLanguageStringsUseCase: SynchronizeLanguageStringsUseCase,
    private val getLanguageIdUseCase: GetLanguageIdUseCase,
    private val saveLanguageIdUseCase: SaveLanguageIdUseCase
) {
    private val _translationMap = MutableStateFlow<Map<String, TranslationValue>>(emptyMap())
    val translationMap: StateFlow<Map<String, TranslationValue>> = _translationMap.asStateFlow()

    fun initialize(context: Context, scope: CoroutineScope) {
        Log.d("LANGINIT", "INIT")
        scope.launch(Dispatchers.IO) {
            val languageId = getLanguageIdUseCase(context)
            Log.d("LANGINIT", languageId)
            _translationMap.value = getLanguageStringsUseCase(languageId).first().associate { it.key to it.value }
            Log.d("LANGINIT",  "${_translationMap.value}")
            synchronizeLanguageStringsUseCase(languageId)
        }
    }

    fun saveLanguageId(languageId: String, context: Context) {
        saveLanguageIdUseCase(languageId, context)
    }

    fun getString(context: Context, key: String, quantity: Long = 1, vararg args: Any): String {
        val strings = _translationMap.value
        val translationValue = strings[key]

        return when (translationValue) {
            is TranslationValue.Ordinary -> String.format(translationValue.value, *args)
            is TranslationValue.Pluralized -> {
                val pluralForm = PluralRulesResolver.getPluralForm(context, quantity, translationValue)
                String.format(pluralForm, *args)
            }
            TranslationValue.Deleted, null -> key
        }
    }

    suspend fun fetchLanguagePacks() : List<LanguagePack> {
        return getAvailableLanguagePacksUseCase().first()
    }
}