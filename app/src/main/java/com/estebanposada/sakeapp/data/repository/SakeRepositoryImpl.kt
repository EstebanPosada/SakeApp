package com.estebanposada.sakeapp.data.repository

import com.estebanposada.sakeapp.data.data_source.SakeDao
import com.estebanposada.sakeapp.data.mappers.toDomainSake
import com.estebanposada.sakeapp.data.remote.SakeApiService
import com.estebanposada.sakeapp.domain.model.Sake
import com.estebanposada.sakeapp.domain.repository.SakeRepository
import com.estebanposada.sakeapp.domain.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class SakeRepositoryImpl(private val dao: SakeDao, private val api: SakeApiService) :
    SakeRepository {

    override suspend fun getMockSakeItems(): Flow<Resource<List<Sake>>> = flow {
        try {
            val response = api.getMockSakeList()
            if (response is Resource.Success) {
                response.data?.let { sakeList ->
                    val sakes = sakeList.map { it.toDomainSake() }
                    dao.insertAll(sakes)
                    emit(Resource.Success(sakes))
                }
            } else {
                val sakes = dao.getAllSakeItems()
                if (sakes.isEmpty()) emit(Resource.Error("Error fetching data"))
                else emit(Resource.Success(sakes))
            }
        } catch (e: Exception){
            val sakes = dao.getAllSakeItems()
            if (sakes.isEmpty()) emit(Resource.Error("Error fetching data"))
            else emit(Resource.Success(sakes))
        }

    }

    override suspend fun getSakeById(id: Int): Sake? = dao.getSakeById(id)
}