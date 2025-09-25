package com.estebanposada.sakeapp.domain.repository

import com.estebanposada.sakeapp.domain.model.Sake
import com.estebanposada.sakeapp.domain.util.Resource
import kotlinx.coroutines.flow.Flow

interface SakeRepository {
    suspend fun getMockSakeItems(): Flow<Resource<List<Sake>>>
    suspend fun getSakeById(id: Int): Sake?
}