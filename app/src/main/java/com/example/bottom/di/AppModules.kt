package com.example.bottom.di

import com.example.bottom.commitList.CommitListRepository
import com.example.bottom.networking.ApiInterface
import com.example.bottom.dashboard.DashboardRepository
import com.example.bottom.profile.ProfileRepository
import com.example.bottom.utilities.Const
import com.example.bottom.utilities.jsonDefaultInstance
import com.haroldadmin.cnradapter.NetworkResponseAdapterFactory
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.ExperimentalSerializationApi
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModules {

    @Provides
    fun providesLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    @Provides
    fun provideOkHttpClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        val okHttpClient = OkHttpClient().newBuilder()

        okHttpClient.callTimeout(40, TimeUnit.SECONDS)
        okHttpClient.connectTimeout(40, TimeUnit.SECONDS)
        okHttpClient.readTimeout(40, TimeUnit.SECONDS)
        okHttpClient.writeTimeout(40, TimeUnit.SECONDS)
        okHttpClient.addInterceptor(loggingInterceptor)
        okHttpClient.build()
        return okHttpClient.build()
    }

    @OptIn(ExperimentalSerializationApi::class)
    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        val contentType = "application/json".toMediaType()
        return Retrofit.Builder().baseUrl(Const.BASE_URL)
            .addConverterFactory(jsonDefaultInstance.asConverterFactory(contentType))
            .addCallAdapterFactory(NetworkResponseAdapterFactory())
            .client(okHttpClient)
            .build()

    }

    @Singleton
    @Provides
    fun provideApiInterface(retrofit: Retrofit): ApiInterface {
        return retrofit.create(ApiInterface::class.java)
    }


    @Singleton
    @Provides
    fun provideCommitListRepository(newsInterface: ApiInterface): CommitListRepository {
        return CommitListRepository(newsInterface)
    }

    @Singleton
    @Provides
    fun provideProfileRepository(newsInterface: ApiInterface): ProfileRepository {
        return ProfileRepository(newsInterface)
    }
    @Singleton
    @Provides
    fun provideDashboardRepository(newsInterface: ApiInterface): DashboardRepository {
        return DashboardRepository(newsInterface)
    }

}