package com.benshapiro.sacp.module

import android.net.Uri
import com.benshapiro.sacp.data.UriOrException
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    fun provideDataOrException(): UriOrException<List<Uri>, Exception> = UriOrException()
}