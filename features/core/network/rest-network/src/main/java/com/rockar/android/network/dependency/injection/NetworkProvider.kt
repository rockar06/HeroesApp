package com.rockar.android.network.dependency.injection

import com.rockar.android.network.interceptors.loggingLevel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object NetworkProvider {

    @Provides
    fun providesMoshiConverter(): Converter.Factory {
        return MoshiConverterFactory.create()
    }

    @Provides
    fun providesRetrofitInstance(
        okHttpClient: OkHttpClient,
        converterFactory: Converter.Factory,
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://gateway.marvel.com/")
            .addConverterFactory(converterFactory)
            .client(okHttpClient)
            .build()
    }

    @Provides
    fun providesOkHttpClient(
        @AuthInterceptor authInterceptor: Interceptor,
        @LoggerInterceptor loggerInterceptor: Interceptor,
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(loggerInterceptor)
            .addInterceptor(authInterceptor)
            .build()
    }

    @Provides
    @LoggerInterceptor
    fun providesLoggerInterceptor(): Interceptor {
        return HttpLoggingInterceptor().setLevel(loggingLevel)
    }
}
