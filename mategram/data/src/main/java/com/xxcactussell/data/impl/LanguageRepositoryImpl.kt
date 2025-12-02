package com.xxcactussell.data.impl

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import com.xxcactussell.data.TdClientManager
import com.xxcactussell.domain.localization.model.LanguagePack
import com.xxcactussell.domain.localization.model.LocalizedResource
import com.xxcactussell.domain.localization.model.TranslationValue
import com.xxcactussell.domain.localization.repository.LanguageRepository
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import org.drinkless.tdlib.TdApi
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LanguageRepositoryImpl @Inject constructor(
    private val clientManager: TdClientManager
) : LanguageRepository {

    private val prefsName = "app_language_settings"
    private val langKey = "language_id"
    private val langDefault = "en"

    private fun TdApi.LanguagePackString.toDomain(): LocalizedResource {
        val value = when(val string = this.value) {
            is TdApi.LanguagePackStringValueOrdinary -> TranslationValue.Ordinary(string.value)
            is TdApi.LanguagePackStringValuePluralized -> TranslationValue.Pluralized(
                zero = string.zeroValue,
                one = string.oneValue,
                two = string.twoValue,
                few = string.fewValue,
                many = string.manyValue,
                other = string.otherValue
            )
            else -> TranslationValue.Ordinary(this.key)
        }
        return LocalizedResource(key = this.key, value = value)
    }

    override fun getLanguageStrings(languageId: String): Flow<List<LocalizedResource>> = callbackFlow {
        fun requestStrings(isRetry: Boolean = false) {
            clientManager.send(TdApi.GetLanguagePackStrings(languageId, arrayOf())) { result ->
                when (result.constructor) {
                    TdApi.LanguagePackStrings.CONSTRUCTOR -> {
                        val tdStrings = (result as TdApi.LanguagePackStrings).strings.map { it.toDomain() }
                        trySend(tdStrings)
                        close()
                    }

                    TdApi.Error.CONSTRUCTOR -> {
                        val error = result as TdApi.Error
                        if (error.message.contains("localization_target") && !isRetry) {
                            clientManager.send(TdApi.SetOption("localization_target", TdApi.OptionValueString("android"))) { optionResult ->
                                if (optionResult.constructor == TdApi.Ok.CONSTRUCTOR) {
                                    requestStrings(isRetry = true)
                                } else {
                                    close(Exception("Failed to recover from error. Could not set localization_target."))
                                }
                            }
                        } else {
                            close(Exception("TDLib error: ${error.message}"))
                        }
                    }
                    else -> {
                        close(Exception("Unexpected result: $result"))
                    }
                }
            }
        }
        requestStrings()
        awaitClose()
    }

    private fun TdApi.LanguagePackInfo.toLanguagePack() : LanguagePack {
        return LanguagePack(
            id = this.id,
            baseLanguagePackId = this.baseLanguagePackId,
            name = this.name,
            nativeName = this.nativeName,
            pluralCode = this.pluralCode,
            isOfficial = this.isOfficial,
            isRtl = this.isRtl,
            isBeta = this.isBeta,
            isInstalled = this.isInstalled,
            totalStringCount = this.totalStringCount,
            translatedStringCount = this.translatedStringCount,
            localStringCount = this.localStringCount,
            translationUrl = this.translationUrl
        )
    }

    override fun getAvailableLanguages(): Flow<List<LanguagePack>> = callbackFlow {
        clientManager.send(TdApi.GetLocalizationTargetInfo(false)) { result ->
            when (result.constructor) {

                TdApi.LocalizationTargetInfo.CONSTRUCTOR -> {
                    val languagePacks = (result as TdApi.LocalizationTargetInfo).languagePacks.map { it.toLanguagePack() }
                    trySend(languagePacks)
                    close()
                }
                TdApi.Error.CONSTRUCTOR -> {
                    val error = result as TdApi.Error
                    close(Exception("TDLib error: ${error.message}"))
                }
                else -> {
                    close(Exception("Unexpected result: $result"))
                }
            }
        }
        awaitClose()
    }

    override fun synchronizeLanguagePack(languageId: String) {
        clientManager.send(TdApi.SynchronizeLanguagePack(languageId))
    }

    override fun saveLanguageId(id: String, context: Context) {
        val prefs: SharedPreferences = context.getSharedPreferences(prefsName, Context.MODE_PRIVATE)
        prefs.edit { putString(langKey, id) }
    }

    override fun getLanguageId(context: Context): String {
        val prefs: SharedPreferences = context.getSharedPreferences(prefsName, Context.MODE_PRIVATE)
        return prefs.getString(langKey, langDefault) ?: langDefault
    }
}