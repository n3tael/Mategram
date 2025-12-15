package com.xxcactussell.data

import android.content.Context
import android.content.res.Resources
import android.os.Build
import android.util.Base64
import androidx.core.content.edit
import com.xxcactussell.mategram.data.BuildConfig
import java.security.SecureRandom

class TelegramCredentials {
    companion object {
        const val USE_FILE_DATABASE = true
        const val USE_CHAT_INFO_DATABASE = true
        const val USE_TEST_DC = false
        const val USE_MESSAGE_DATABASE = true
        const val USE_SECRET_CHATS = true
        const val APPLICATION_VERSION = BuildConfig.APP_VERSION_STRING
        const val API_ID = BuildConfig.TELEGRAM_API_ID
        const val API_HASH = BuildConfig.TELEGRAM_API_HASH
        val systemLanguageCode: String = Resources.getSystem().configuration.locales[0].toString()
        val deviceModel: String = "${Build.MANUFACTURER} ${Build.MODEL}"
        val systemVersion: String = Build.VERSION.RELEASE

        lateinit var encryptionKey: ByteArray
            private set
        var databaseDirectory: String? = null
            private set
        var filesDirectory: String? = null
            private set

        fun initialize(context: Context) {
            if (this::encryptionKey.isInitialized) return

            databaseDirectory = context.filesDir.absolutePath
            filesDirectory = context.getExternalFilesDir(null)?.absolutePath

            encryptionKey = loadEncryptionKey(context) ?: run {
                val newKey = ByteArray(32).apply { SecureRandom().nextBytes(this) }
                saveEncryptionKey(context, newKey)
                newKey
            }
        }

        private fun saveEncryptionKey(context: Context, key: ByteArray) {
            val prefs = context.getSharedPreferences("tdlib_prefs", Context.MODE_PRIVATE)
            val encodedKey = Base64.encodeToString(key, Base64.DEFAULT)
            prefs.edit { putString("database_encryption_key", encodedKey) }
        }

        private fun loadEncryptionKey(context: Context): ByteArray? {
            val prefs = context.getSharedPreferences("tdlib_prefs", Context.MODE_PRIVATE)
            val encodedKey = prefs.getString("database_encryption_key", null) ?: return null
            return Base64.decode(encodedKey, Base64.DEFAULT)
        }
    }
}