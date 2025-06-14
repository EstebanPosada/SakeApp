package com.estebanposada.sakeapp.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.estebanposada.sakeapp.domain.model.Sake

@Database(entities = [Sake::class], version = 1, exportSchema = false)
@TypeConverters(Converter::class)
abstract class SakeDatabase : RoomDatabase(){
    abstract fun sakeDao(): SakeDao

    companion object {
        const val DATABASE_NAME = "sake_database"
    }

}