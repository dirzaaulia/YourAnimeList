package com.dirzaaulia.youranimelist.core.network.di

import com.dirzaaulia.youranimelist.core.network.Dispatcher
import com.dirzaaulia.youranimelist.core.network.YALDispatchers.IO
import com.dirzaaulia.youranimelist.core.network.YALDispatchers.Default
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

@Module
@InstallIn(SingletonComponent::class)
object DispatchersModule {
    @Provides
    @Dispatcher(IO)
    fun providesIODispatcher(): CoroutineDispatcher = Dispatchers.IO

    @Provides
    @Dispatcher(Default)
    fun providesDefaultDispatcher(): CoroutineDispatcher = Dispatchers.Default
}