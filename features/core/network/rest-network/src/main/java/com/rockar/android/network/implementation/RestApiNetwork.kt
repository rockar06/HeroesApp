package com.rockar.android.network.implementation

import com.rockar.android.network.api.Network
import com.rockar.android.network.service.MarvelService
import retrofit2.Retrofit
import javax.inject.Inject

class RestApiNetwork @Inject constructor(
    private val retrofitInstance: Retrofit,
) : Network<MarvelService> {

    override fun service(): MarvelService {
        return retrofitInstance.create(MarvelService::class.java)
    }
}
