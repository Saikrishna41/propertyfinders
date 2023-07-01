package com.techfortyone.propertyfinders.di

import android.util.Config
import com.techfortyone.propertyfinders.data.remote.PropertyService
import com.techfortyone.propertyfinders.data.remote.PropertyServiceHelper
import com.techfortyone.propertyfinders.data.remote.PropertyServiceImpl
import com.techfortyone.propertyfinders.utils.BASE_WEB_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideBaseUrl() = BASE_WEB_URL

    @Provides
    @Singleton
    fun provideOkhttpClient() = if (Config.DEBUG) {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        OkHttpClient.Builder().addInterceptor(loggingInterceptor).build()
    } else {
        OkHttpClient.Builder().build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(baseUrl: String, okHttpClient: OkHttpClient) =
        Retrofit.Builder().client(okHttpClient).addConverterFactory(GsonConverterFactory.create())
            .baseUrl(baseUrl).build()

    @Provides
    @Singleton
    fun propertyService(retrofit: Retrofit) = retrofit.create(PropertyService::class.java)

    @Provides
    @Singleton
    fun providePropertyApiServiceHelper(propertyApiServiceHelper: PropertyServiceImpl): PropertyServiceHelper =
        propertyApiServiceHelper

}