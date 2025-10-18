package com.example.calendario.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private val logging = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val client = OkHttpClient.Builder()
        .addInterceptor(logging)
        .build()

    val api: NagerApiService by lazy {
        Retrofit.Builder()
            .baseUrl("https://date.nager.at/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NagerApiService::class.java)
    }
}
