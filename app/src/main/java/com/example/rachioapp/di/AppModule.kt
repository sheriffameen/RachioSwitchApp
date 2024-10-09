package com.example.rachioapp.di

import com.example.rachioapp.base.api.APIService
import com.example.rachioapp.base.api.interceptor.AuthInterceptor
import com.example.rachioapp.base.utils.Constants
import com.example.rachioapp.ui.overview.domain.OverviewRepository
import com.example.rachioapp.ui.overview.domain.OverviewRepositoryImpl
import com.example.switchapp.BuildConfig
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideBaseUrl() = Constants.BASE_URL

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient = if (BuildConfig.DEBUG){
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor(AuthInterceptor())
            .build()
    }else {
        OkHttpClient.Builder().build()
    }

    @Singleton
    @Provides
    fun provideMoshi(): Moshi = Moshi.Builder().build()

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient, BASE_URL: String, moshi: Moshi): Retrofit =
        Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .build()

    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): APIService = retrofit.create(APIService::class.java)

    @Provides
    fun provideMatchRepository(
        apiService: APIService,
    ): OverviewRepository =
        OverviewRepositoryImpl(apiService)
}