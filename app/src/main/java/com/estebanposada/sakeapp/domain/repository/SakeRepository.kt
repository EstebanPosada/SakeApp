package com.estebanposada.sakeapp.domain.repository

import com.estebanposada.sakeapp.domain.model.Sake
import kotlinx.coroutines.flow.Flow

interface SakeRepository {
    fun getSakeItems(): Flow<List<Sake>>
    suspend fun getMockSakeItems(): Flow<List<Sake>>
    suspend fun getSakeById(id: Int): Sake?
}