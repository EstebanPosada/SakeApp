package com.estebanposada.sakeapp.data.data_source

import android.content.Context
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import javax.inject.Provider

class SakeDatabaseCallback(
    private val context: Context,
    private val sakeProvider: Provider<SakeDao>
) : RoomDatabase.Callback() {
    private val scope = CoroutineScope(SupervisorJob())

    override fun onCreate(db: SupportSQLiteDatabase) {
        super.onCreate(db)
        scope.launch {
            val sakeList = loadSakeListFromJson(context)
            sakeProvider.get().insertAll(sakeList)
        }
    }
}