package com.estebanposada.sakeapp.mock

import retrofit2.http.GET

interface SakeApiService {

    @GET("sake-list")
    suspend fun getMockSakeList(): List<Sake>
}

