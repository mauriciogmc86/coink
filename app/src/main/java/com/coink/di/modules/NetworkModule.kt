package com.coink.di.modules

import com.coink.BuildConfig
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.Reusable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
object NetworkModule {

    private const val URL: String = "https://api.bancoink.biz/qa/"
    private const val DEFAULT_TIME_OUT: Long = 3000

    @Provides
    @Reusable
    fun gson(): Gson = GsonBuilder().create()

    @Provides
    @Singleton
    fun getOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder().apply {
            readTimeout(DEFAULT_TIME_OUT, TimeUnit.MILLISECONDS)
            connectTimeout(DEFAULT_TIME_OUT, TimeUnit.MILLISECONDS)
            val httpLoggingInterceptor =
                HttpLoggingInterceptor(HttpLoggingInterceptor.Logger.DEFAULT)
            if (BuildConfig.DEBUG) {
                httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
                addInterceptor(httpLoggingInterceptor)
            }
        }.build()
    }

    @Provides
    @Singleton
    fun getServicesClient(httpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .client(httpClient)
            .baseUrl(URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }
}