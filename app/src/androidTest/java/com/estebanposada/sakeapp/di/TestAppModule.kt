package com.estebanposada.sakeapp.di

import android.app.Application
import androidx.room.Room
import com.estebanposada.sakeapp.data.data_source.SakeDao
import com.estebanposada.sakeapp.data.data_source.SakeDatabase
import com.estebanposada.sakeapp.data.repository.SakeRepositoryImpl
import com.estebanposada.sakeapp.domain.repository.SakeRepository
import com.estebanposada.sakeapp.domain.use_case.GetSakeByIdUseCase
import com.estebanposada.sakeapp.domain.use_case.GetSakeItemsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import javax.inject.Provider
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TestAppModule {

    @Provides
    @Singleton
    fun provideDatabase(app: Application, dataProvider: Provider<SakeDao>) =
        Room.inMemoryDatabaseBuilder(app, SakeDatabase::class.java).build()

    @Provides
    @Singleton
    fun provideSakeDao(db: SakeDatabase) = db.sakeDao()

    @Provides
    @Singleton
    fun provideApplicationScope() = CoroutineScope(SupervisorJob())

    @Provides
    @Singleton
    fun provideGetSakeItemsUseCase(repository: SakeRepository) = GetSakeItemsUseCase(repository)

    @Provides
    @Singleton
    fun provideGetSakeByIdUseCase(repository: SakeRepository) = GetSakeByIdUseCase(repository)

    @Provides
    @Singleton
    fun provideSakeRepository(dao: SakeDao): SakeRepository = SakeRepositoryImpl(dao)
}