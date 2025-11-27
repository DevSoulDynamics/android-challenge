package com.ankrisdevs.android_challenge.data.auth

import android.util.Base64
import java.security.MessageDigest
import java.security.SecureRandom

object PKCEUtils {
    fun generateCodeVerifier() : String {
        val secureRandom = SecureRandom()
        val code = ByteArray(64) // longitud entre 43 y 128
        secureRandom.nextBytes(code)
        // URL-safe base64 sin padding
        return Base64.encodeToString(code, Base64.URL_SAFE or Base64.NO_WRAP or Base64.NO_PADDING)
    }

    fun generateCodeChallenge(verifier: String) : String {
        val bytes = verifier.toByteArray(Charsets.US_ASCII)
        val messageDigest = MessageDigest.getInstance("SHA-256")
        val digest = messageDigest.digest(bytes)
        return Base64.encodeToString(digest, Base64.URL_SAFE or Base64.NO_WRAP or Base64.NO_PADDING)
    }
}