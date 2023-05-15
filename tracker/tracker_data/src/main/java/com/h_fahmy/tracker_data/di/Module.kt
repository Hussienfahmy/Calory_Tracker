package com.h_fahmy.tracker_data.di

import android.content.Context
import androidx.room.Room
import com.h_fahmy.tracker_data.local.TrackerDatabase
import com.h_fahmy.tracker_data.remote.OpenFoodAPI
import com.h_fahmy.tracker_data.repository.TrackerRepositoryImpl
import com.h_fahmy.tracker_domain.repository.TrackerRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object Module {

    @Provides
    @Singleton
    fun provideOkHttpClient() = OkHttpClient.Builder()
        .addInterceptor(
            HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }
        )
        .build()

    @Provides
    @Singleton
    fun provideOpenFoodAPI(client: OkHttpClient): OpenFoodAPI = Retrofit.Builder()
        .baseUrl(OpenFoodAPI.BASE_URL)
        .client(client)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()
        .create(OpenFoodAPI::class.java)

    @Provides
    @Singleton
    fun provideTrackerDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(
        context,
        TrackerDatabase::class.java,
        "tracker_db"
    ).build()

    @Provides
    @Singleton
    fun provideRepository(
        db: TrackerDatabase,
        api: OpenFoodAPI,
    ): TrackerRepository = TrackerRepositoryImpl(
        dao = db.dao,
        api = api,
    )
}