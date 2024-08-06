package com.dirzaaulia.youranimelist.core.network

import javax.inject.Qualifier
import kotlin.annotation.AnnotationRetention.RUNTIME

@Qualifier
@Retention(RUNTIME)
annotation class Dispatcher(val yalDispatchers: YALDispatchers)

enum class YALDispatchers {
    Default,
    IO,
}