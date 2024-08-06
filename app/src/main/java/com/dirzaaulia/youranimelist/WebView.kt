package com.dirzaaulia.youranimelist

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.util.Log
import android.webkit.CookieManager
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebView
import androidx.compose.animation.AnimatedVisibility
import com.dirzaaulia.youranimelist.core.designsystem.component.WebView as WebView
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.dirzaaulia.youranimelist.core.designsystem.component.AccompanistWebViewClient
import com.dirzaaulia.youranimelist.core.designsystem.component.LoadingState
import com.dirzaaulia.youranimelist.core.designsystem.component.rememberWebViewNavigator
import com.dirzaaulia.youranimelist.core.designsystem.component.rememberWebViewState


//TODO Move to feature module later
@SuppressLint("SetJavaScriptEnabled")
@Composable
fun WebView(
    url: String,
    errorCallback: () -> Unit
) {

    val state = rememberWebViewState(url = url)
    val navigator = rememberWebViewNavigator()

    val loadingState = state.loadingState

    // A custom WebViewClient and WebChromeClient can be provided via subclassing
    val webClient = remember {
        object : AccompanistWebViewClient() {

            override fun onPageStarted(
                view: WebView,
                url: String?,
                favicon: Bitmap?
            ) {
                super.onPageStarted(view, url, favicon)
                Log.d("Accompanist WebView", "Page started loading for $url")
            }

            override fun shouldOverrideUrlLoading(
                view: WebView?,
                request: WebResourceRequest?
            ): Boolean {
                val urlValue = request?.url.toString()
                Log.d("TAG_DIRZA", "shouldOverrideUrlLoading: $urlValue")
                val code = request?.url?.getQueryParameter("code")
                val error = request?.url?.getQueryParameter("error")
                val hint = request?.url?.getQueryParameter("hint")

                if (urlValue.contains(MyAnimeListUtil.CALLBACK_URL)) {
                    if (!error.isNullOrBlank()) {
                        when (error) {
                            "access_denied" -> {
                                if (hint.equals("The user denied the request")) {
                                    Log.d("TAG_DIRZA", "User Denied Request")
                                    errorCallback.invoke()
                                }
                                Log.d("TAG_DIRZA", "Access Denied")
                            }
                            "offline_access" -> {
                                Log.d("TAG_DIRZA", "Login Failed")
                            }
                        }
                        return true
                    }

//                    return true
                }
                return false
            }

            override fun onReceivedError(
                view: WebView,
                request: WebResourceRequest?,
                error: WebResourceError?
            ) {
                Log.d("TAG_DIRZA", "${error?.errorCode} | ${error?.description}")
                super.onReceivedError(view, request, error)
            }
        }
    }

    Scaffold(
        topBar = {
            if (loadingState is LoadingState.Loading) {
                AnimatedVisibility(true) {
                    LinearProgressIndicator(
                        progress = { loadingState.progress },
                        modifier = Modifier.fillMaxWidth(),
                    )
                }
            }
        }
    ) { innerPadding ->
        WebView(
            modifier = Modifier.padding(innerPadding),
            state = state,
            navigator = navigator,
            onCreated = { webView ->
                webView.settings.javaScriptEnabled = true
                webView.clearCache(true)
                webView.clearFormData()
                webView.clearHistory()
                webView.clearSslPreferences()
                CookieManager.getInstance().removeAllCookies(null)
                CookieManager.getInstance().flush()
            },
            client = webClient
        )

        navigator.canGoForward
    }
}