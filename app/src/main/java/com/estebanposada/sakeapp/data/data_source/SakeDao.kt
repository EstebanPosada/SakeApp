package com.estebanposada.sakeapp.data.data_source

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.estebanposada.sakeapp.domain.model.Sake

@Dao
interface SakeDao {
    @Query("SELECT * FROM Sake")
    fun getAllSakeItems(): List<Sake>

    @Query("SELECT * FROM Sake WHERE id = :id")
    suspend fun getSakeById(id: Int): Sake?

    @Insert
    suspend fun insertSake(sake: Sake)

    @Insert
    suspend fun insertAll(sakes: List<Sake>)

    @Query("DELETE FROM Sake WHERE id = :id")
    suspend fun deleteSakeById(id: Int)
}