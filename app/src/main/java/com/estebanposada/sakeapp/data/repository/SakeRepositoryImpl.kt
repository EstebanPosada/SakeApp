package com.estebanposada.sakeapp.data.repository

import com.estebanposada.sakeapp.data.data_source.SakeDao
import com.estebanposada.sakeapp.domain.model.Sake
import com.estebanposada.sakeapp.domain.repository.SakeRepository
import com.estebanposada.sakeapp.mock.SakeApiService
import com.estebanposada.sakeapp.mock.toDomainSake
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class SakeRepositoryImpl(private val dao: SakeDao, private val api: SakeApiService) :
    SakeRepository {
    override fun getSakeItems(): Flow<List<Sake>> = dao.getAllSakeItems()

    override suspend fun getMockSakeItems(): Flow<List<Sake>> {
        val response = api.getMockSakeList().map { it.toDomainSake() }
        dao.insertAll(response)
        val data = dao.getAllSakeItems()
        return data
    }

    override suspend fun getSakeById(id: Int): Sake? = dao.getSakeById(id)
}