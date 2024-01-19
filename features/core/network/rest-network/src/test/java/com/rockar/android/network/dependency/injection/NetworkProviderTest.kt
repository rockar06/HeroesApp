package com.rockar.android.network.dependency.injection

import com.google.common.truth.Truth.assertThat
import com.rockar.android.network.interceptors.AuthenticationInterceptor
import io.mockk.mockk
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class NetworkProviderTest {

    @Test
    fun `verify providesMoshiConverter method returns a Moshi Converter Factory`() {
        val converterFactory = NetworkProvider.providesMoshiConverter()
        assertThat(converterFactory).isInstanceOf(MoshiConverterFactory::class.java)
    }

    @Test
    fun `verify providesRetrofitInstance method returns a valid Retrofit instance`() {
        val retrofitInstance = NetworkProvider.providesRetrofitInstance(mockk(), mockk())
        assertThat(retrofitInstance).isInstanceOf(Retrofit::class.java)
    }

    @Test
    fun `verify baseUrl returned by retrofit instance`() {
        val baseUrl = NetworkProvider.providesRetrofitInstance(mockk(), mockk()).baseUrl().toUrl().toString()
        assertThat(baseUrl).isEqualTo("https://gateway.marvel.com/")
    }

    @Test
    fun `verify converter factory list contains only one item`() {
        val converterList =
            NetworkProvider.providesRetrofitInstance(mockk(), mockk()).converterFactories()
        assertThat(converterList.size).isEqualTo(3)
    }

    @Test
    fun `verify converter factory list contains moshi converter`() {
        val mockMoshiConverter = mockk<MoshiConverterFactory>()
        val converterList = NetworkProvider.providesRetrofitInstance(mockk(), mockMoshiConverter)
            .converterFactories()
        assertThat(converterList[1]).isSameInstanceAs(mockMoshiConverter)
    }

    @Test
    fun `verify client is an instance of OkHttpClient`() {
        val mockOkHttpClient = mockk<OkHttpClient>()
        val callFactory =
            NetworkProvider.providesRetrofitInstance(mockOkHttpClient, mockk()).callFactory()
        assertThat(callFactory).isSameInstanceAs(mockOkHttpClient)
    }

    @Test
    fun `verify providesOkHttpClient is an instance of OkHttpClient`() {
        val okHttpClient = NetworkProvider.providesOkHttpClient(mockk(), mockk())
        assertThat(okHttpClient).isInstanceOf(OkHttpClient::class.java)
    }

    @Test
    fun `verify okHttpClient contains the auth interceptor`() {
        val authInterceptor = mockk<AuthenticationInterceptor>()
        val okHttpClient = NetworkProvider.providesOkHttpClient(authInterceptor, mockk())
        assertThat(okHttpClient.interceptors[1]).isSameInstanceAs(authInterceptor)
    }

    @Test
    fun `verify okHttpClient contains the logger interceptor`() {
        val loggerInterceptor = mockk<HttpLoggingInterceptor>()
        val okHttpClient = NetworkProvider.providesOkHttpClient(mockk(), loggerInterceptor)
        assertThat(okHttpClient.interceptors[0]).isSameInstanceAs(loggerInterceptor)
    }

    @Test
    fun `verify providesLoggerInterceptor has an instance of HttpLoggingInterceptor`() {
        val loggerInterceptor = NetworkProvider.providesLoggerInterceptor()
        assertThat(loggerInterceptor).isInstanceOf(HttpLoggingInterceptor::class.java)
    }
}
