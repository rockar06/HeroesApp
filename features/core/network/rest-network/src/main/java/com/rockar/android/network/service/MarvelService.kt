package com.rockar.android.network.service

import retrofit2.http.GET

interface MarvelService {

    @GET("/v1/public/characters")
    suspend fun getAllCharacters()
}
