package com.dirzaaulia.youranimelist.core.network.ktor

import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.dirzaaulia.youranimelist.core.network.BuildConfig
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.auth.Auth
import io.ktor.client.plugins.auth.providers.bearer
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.ANDROID
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.resources.Resources
import io.ktor.client.plugins.resources.get
import io.ktor.client.request.setBody
import io.ktor.http.URLProtocol
import io.ktor.http.appendPathSegments
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class KtorClient @Inject constructor(
    chuckerInterceptor: ChuckerInterceptor
) {

    private val client = initializeKtor(chuckerInterceptor)

    internal suspend inline fun <reified Z : Any, reified T> getRequestApiWithQuery(
        resources: Z,
        query: Map<String, String>,
        path: String? = null,
        body: Z? = null,
    ): T {
        return client
            .get(resources) {
                url {
                    query.forEach { item ->
                        parameters.append(item.key, item.value)
                    }
                    path?.let {
                        appendPathSegments(it)
                    }
                    body?.let {
                        setBody(body)
                    }
                }
            }.body()
    }

    companion object {
        private const val TIMEOUT = 60L

        private fun initializeKtor(
            chuckerInterceptor: ChuckerInterceptor
        ) = HttpClient(OkHttp) {

            engine {
                addInterceptor(chuckerInterceptor)
                config {
                    connectTimeout(timeout = TIMEOUT, unit = TimeUnit.SECONDS)
                    writeTimeout(timeout = TIMEOUT, unit = TimeUnit.SECONDS)
                    readTimeout(timeout = TIMEOUT, unit = TimeUnit.SECONDS)
                }
            }

            install(ContentNegotiation) {
                json(Json {
                    prettyPrint = true
                    isLenient = true
                })
            }

            install(Logging) {
                logger = Logger.ANDROID
                level = LogLevel.BODY
            }

            install(Auth) {
                bearer {
                    // Configure bearer authentication
                }
            }

            install(Resources)
            defaultRequest {
                url {
                    protocol = URLProtocol.HTTPS
                    host = BuildConfig.BACKEND_URL
                }
            }
        }
    }
}