package com.richard.githubapp.core.data.local

import android.content.Context
import com.richard.githubapp.util.GithubTheme.SYSTEM
import javax.inject.Inject

class GithubPreferencesManager @Inject constructor(
    context: Context
) {
    /*private val masterKey = MasterKey.Builder(context)
        .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
        .build()*/

    private val sharedPreferences = context.getSharedPreferences("shared_prefs", Context.MODE_PRIVATE)

    /*private val encryptedSharedPreferences: SharedPreferences = EncryptedSharedPreferences.create(
        context,
        "secret_shared_prefs",
        masterKey,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )

    fun saveToken(key: String, token: String) {
        encryptedSharedPreferences.edit()
            .putString(key, token)
            .apply()
    }

    fun getToken(key: String) = encryptedSharedPreferences.getString(key, null)*/

    fun saveString(key: String, value: String) {
        sharedPreferences.edit()
            .putString(key, value)
            .apply()
    }

    fun getString(key: String) = sharedPreferences.getString(key, SYSTEM.name)

}