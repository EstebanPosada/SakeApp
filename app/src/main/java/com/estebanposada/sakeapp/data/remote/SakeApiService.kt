package com.estebanposada.sakeapp.data.remote

import com.estebanposada.sakeapp.data.remote.mock.Sake
import com.estebanposada.sakeapp.domain.util.Resource
import retrofit2.http.GET

interface SakeApiService {

    @GET("sake-list")
    suspend fun getMockSakeList(): Resource<List<Sake>>
}

