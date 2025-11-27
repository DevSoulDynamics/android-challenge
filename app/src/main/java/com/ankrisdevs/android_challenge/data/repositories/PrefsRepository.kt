package com.ankrisdevs.android_challenge.data.repositories

import android.content.Context
import androidx.core.content.edit
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class PrefsRepository @Inject constructor(
    @ApplicationContext private val context: Context
) {
    private val prefs = context.getSharedPreferences("Devsoulify_Prefs", Context.MODE_PRIVATE)

    fun saveCodeAuth(code: String) {
        prefs.edit { putString("CODE", code) }
    }

    fun loadCodeAuth(): String? {
        return prefs.getString("CODE", null)
    }

    fun cleanCodeAuth() {
        prefs.edit { remove("CODE") }
    }

    fun saveCodeVerifier(verifier: String) {
        prefs.edit { putString("CODE_VERIFIER", verifier) }
    }

    fun loadCodeVerifier(): String? {
        return prefs.getString("CODE_VERIFIER", null)
    }

    fun cleanCodeVerifier() {
        prefs.edit { remove("CODE_VERIFIER") }
    }

    fun saveToken(token: String) {
        prefs.edit { putString("TOKEN", token) }
    }

    fun loadToken(): String? {
        return prefs.getString("TOKEN", null)
    }

    fun cleanToken() {
        prefs.edit { remove("TOKEN") }
    }

    fun saveRefreshToken(refresh: String) {
        prefs.edit { putString("REFRESH_TOKEN", refresh) }
    }

    fun loadRefreshToken(): String? {
        return prefs.getString("REFRESH_TOKEN", null)
    }

    fun cleanRefreshToken() {
        prefs.edit { remove("REFRESH_TOKEN") }
    }
}