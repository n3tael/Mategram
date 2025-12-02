package com.xxcactussell.presentation.localization

import android.annotation.SuppressLint
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
import dagger.hilt.android.qualifiers.ApplicationContext
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