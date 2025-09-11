package com.estebanposada.sakeapp.di

import android.app.Application
import androidx.room.Room
import com.estebanposada.sakeapp.data.data_source.SakeDao
import com.estebanposada.sakeapp.data.data_source.SakeDatabase
import com.estebanposada.sakeapp.data.data_source.SakeDatabaseCallback
import com.estebanposada.sakeapp.data.repository.SakeRepositoryImpl
import com.estebanposada.sakeapp.domain.repository.SakeRepository
import com.estebanposada.sakeapp.domain.use_case.GetSakeByIdUseCase
import com.estebanposada.sakeapp.domain.use_case.GetSakeItemsUseCase
import com.estebanposada.sakeapp.mock.MockNetworkInterceptor
import com.estebanposada.sakeapp.mock.SakeApiService
import com.estebanposada.sakeapp.mock.mockSakeList
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Provider
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(app: Application, dataProvider: Provider<SakeDao>) =
        Room.databaseBuilder(app, SakeDatabase::class.java, SakeDatabase.DATABASE_NAME)
            .addCallback(SakeDatabaseCallback(app, dataProvider)).build()

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
    fun provideSakeRepository(dao: SakeDao, api: SakeApiService): SakeRepository = SakeRepositoryImpl(dao, api)

    @Provides
    @Singleton
    fun provideMockNetworkInterceptor() = MockNetworkInterceptor().mock(
        "http://localhost/sake-list",
        { Gson().toJson(mockSakeList) },
        200, 1000
    )

    @Provides
    @Singleton
    fun provideClient(interceptor: MockNetworkInterceptor) =
        OkHttpClient.Builder().addInterceptor(interceptor).build()

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder().baseUrl("http://localhost/").client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create()).build()

    @Provides
    @Singleton
    fun provideSakeApi(retrofit: Retrofit): SakeApiService =
        retrofit.create(SakeApiService::class.java)
}