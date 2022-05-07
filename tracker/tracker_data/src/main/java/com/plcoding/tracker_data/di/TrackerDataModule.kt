package com.plcoding.tracker_data.di

import android.app.Application
import androidx.room.Room
import com.plcoding.tracker_data.local.TrackerDatabase
import com.plcoding.tracker_data.local.entity.TrackerDao
import com.plcoding.tracker_data.remote.OpenFoodApi
import com.plcoding.tracker_data.remote.OpenFoodApi.Companion.BASE_URL
import com.plcoding.tracker_data.repository.TrackerRepositoryImpl
import com.plcoding.tracker_domain.repository.TrackerRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

/**
 * contains all the dependencies for the tracker module
 */
@Module
@InstallIn(SingletonComponent::class)
object TrackerDataModule {

    @Provides
    @Singleton //creates an interceptor to help with debugging
    fun provideOkHttpClient():OkHttpClient {
        return  OkHttpClient.Builder()
            .addInterceptor(
                HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                }
            )
            .build()
    }

    @Provides
    @Singleton //used to make the network calls
    fun provideOpenFoodApi(client: OkHttpClient): OpenFoodApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .client(client)
            .build()
            .create(OpenFoodApi::class.java)
    }

    @Provides
    @Singleton //provides room db
    fun provideTrackerDatabase(app: Application): TrackerDatabase{
        return Room.databaseBuilder(
            app,
            TrackerDatabase::class.java,
            "tracker_db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideTrackerRepository(
        api: OpenFoodApi,
        db: TrackerDatabase, //db instance instead of dao for testing purposes with room
    ): TrackerRepository = TrackerRepositoryImpl(dao = db.dao, api = api)
}