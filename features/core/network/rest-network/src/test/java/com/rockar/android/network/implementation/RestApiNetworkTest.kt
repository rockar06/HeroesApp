package com.rockar.android.network.implementation

import com.rockar.android.network.service.MarvelService
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Test
import retrofit2.Retrofit

class RestApiNetworkTest {

    private val mockRetrofit = mockk<Retrofit>(relaxed = true) {
        every { create<MarvelService>(any()) } returns mockk()
    }
    private val systemUnderTest = RestApiNetwork(mockRetrofit)

    @Test
    fun `validate service is creating an instance of MarvelService`() {
        systemUnderTest.service()
        verify(exactly = 1) {
            mockRetrofit.create(MarvelService::class.java)
        }
    }
}
