package com.rockar.android.network.interceptors

import io.mockk.mockk
import io.mockk.verify
import okhttp3.Interceptor
import org.junit.Test

class AuthenticationInterceptorTest {

    private val mockChain = mockk<Interceptor.Chain>(relaxed = true)
    private val systemUnderTest = AuthenticationInterceptor()

    @Test
    fun `verify authentication interceptor is intercepting the retrofit call`() {
        systemUnderTest.intercept(mockChain)
        verify(exactly = 1) {
            mockChain.proceed(mockChain.request())
        }
    }
}
