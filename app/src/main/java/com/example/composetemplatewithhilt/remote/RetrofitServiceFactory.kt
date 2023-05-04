package com.example.composetemplatewithhilt.remote

import com.example.composetemplatewithhilt.BuildConfig
import com.example.composetemplatewithhilt.core.di.Qualifiers.AuthInterceptorOkHttpClient
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

class RetrofitServiceFactory @Inject constructor(
    @AuthInterceptorOkHttpClient okHttpClient: OkHttpClient
) {
    private val retrofit: Retrofit
    init {
        retrofit = makeRetrofit(
            if (BuildConfig.DEBUG) "https://eyenak.in/" else "",
            okHttpClient
        )
    }

    private fun makeRetrofit(baseUrl: String, okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun <T> prepareService(service: Class<T>): T {
        return retrofit.create(service)
    }
}