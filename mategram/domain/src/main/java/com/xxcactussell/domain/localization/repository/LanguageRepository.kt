package com.xxcactussell.domain.localization.repository

import android.content.Context
import com.xxcactussell.domain.localization.model.LanguagePack
import com.xxcactussell.domain.localization.model.LocalizedResource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface LanguageRepository {
    fun getLanguageStrings(languageId: String): Flow<List<LocalizedResource>>
    fun getAvailableLanguages(): Flow<List<LanguagePack>>
    fun synchronizeLanguagePack(languageId: String)
    fun saveLanguageId(id: String,context: Context)
    fun getLanguageId(context: Context) : String
}

class GetLanguageStringsUseCase @Inject constructor(private val repository: LanguageRepository) {
    operator fun invoke(languageId: String) : Flow<List<LocalizedResource>> = repository.getLanguageStrings(languageId)
}

class SynchronizeLanguageStringsUseCase @Inject constructor(private val repository: LanguageRepository) {
    operator fun invoke(languageId: String) = repository.synchronizeLanguagePack(languageId)
}

class GetAvailableLanguagePacksUseCase @Inject constructor(private val repository: LanguageRepository) {
    operator fun invoke(): Flow<List<LanguagePack>> = repository.getAvailableLanguages()
}

class SaveLanguageIdUseCase @Inject constructor(private val repository: LanguageRepository) {
    operator fun invoke(id: String, context: Context) = repository.saveLanguageId(id, context)
}

class GetLanguageIdUseCase @Inject constructor(private val repository: LanguageRepository) {
    operator fun invoke(context: Context) : String = repository.getLanguageId(context)
}