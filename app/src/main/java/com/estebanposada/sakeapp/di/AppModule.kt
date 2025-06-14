package com.estebanposada.sakeapp.di

import android.app.Application
import androidx.room.Room
import com.estebanposada.sakeapp.data.data_source.SakeDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(app: Application): Any =
        Room.databaseBuilder(app, SakeDatabase::class.java, SakeDatabase.DATABASE_NAME).build()

/*
    @Provides
    fun provideLoggingInterceptor(): HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    @Provides
    fun provideOkHttpClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()*/


    /*@Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
//            .baseUrl()
//            .baseUrl(BuildConfig.URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()*/

    /*@Provides
    fun provideSearchService(retrofit: Retrofit): SakeAPI =
        retrofit.create(SakeAPI::class.java)*/
}