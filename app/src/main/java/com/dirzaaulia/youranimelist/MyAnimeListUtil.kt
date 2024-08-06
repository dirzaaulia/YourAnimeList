package com.dirzaaulia.youranimelist

import android.net.Uri
import android.util.Log
import com.dirzaaulia.youranimelist.Constant.HTTPS
import com.google.api.Http

object Constant {
    val HTTPS = "https"
}

object MyAnimeListUtil {
    const val HOST = "myanimelist.net"
    const val AUTHENTICATE_PATH = "v1/oauth2/authorize"
    const val CALLBACK_URL = "https://dirzaaulia.com/youranimelist/callback"
    private fun getAuthenticateQuery(): Map<String, String> {
        val codeVerifier = PKCEUtil.generateCodeVerifier()
        val codeChallenge = PKCEUtil.generateCodeChallenge(codeVerifier)
        return mapOf(
            "response_type" to "code",
            "client_id" to "a3035259cd3e1f31aad635efd1d9862f",
            "state" to "LOGIN",
            "redirect_uri" to "https://dirzaaulia.com/youranimelist/callback",
            "code_challenge" to codeChallenge,
            "code_challenge_method" to "plain"
        )
    }

    fun getAuthenticateUrl(
        queryMap: Map<String, String> = getAuthenticateQuery()
    ) {
        val url = Uri.Builder().apply {
            scheme(HTTPS)
            authority(HOST)
            appendPath(AUTHENTICATE_PATH)
            queryMap.forEach { (key, value) ->
                appendQueryParameter(key, value)
            }
        }.build()
        Log.d("TAG_DIRZA", "getAuthenticateUrl: $url")
    }
}