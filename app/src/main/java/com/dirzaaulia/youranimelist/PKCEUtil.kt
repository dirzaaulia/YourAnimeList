package com.dirzaaulia.youranimelist

import java.security.MessageDigest
import java.util.*

object PKCEUtil {

    fun generateCodeVerifier(): String {
        // Generate a random string of 43-128 characters
        val random = Random()
        val length = random.nextInt(85) + 43
        val codeVerifier = ByteArray(length)
        random.nextBytes(codeVerifier)

        // Encode the string using Base64 without padding or line breaks
        return Base64.getUrlEncoder().withoutPadding().encodeToString(codeVerifier)
    }

    fun generateCodeChallenge(codeVerifier: String): String {
        // Hash the code verifier using SHA-256
        val bytes = codeVerifier.toByteArray(Charsets.US_ASCII)
        val md = MessageDigest.getInstance("SHA-256")
        val hash = md.digest(bytes)

        // Encode the hash using Base64 without padding or line breaks
        return Base64.getUrlEncoder().withoutPadding().encodeToString(hash)
    }
}