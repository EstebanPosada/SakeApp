package com.estebanposada.sakeapp.data.repository

import com.estebanposada.sakeapp.data.data_source.SakeDao
import com.estebanposada.sakeapp.domain.model.Sake
import com.estebanposada.sakeapp.domain.repository.SakeRepository
import kotlinx.coroutines.flow.Flow

class SakeRepositoryImpl(private val dao: SakeDao): SakeRepository {
    override fun getSakeItems(): Flow<List<Sake>> = dao.getAllSakeItems()

    override suspend fun getSakeById(id: Int): Sake? = dao.getSakeById(id)
}