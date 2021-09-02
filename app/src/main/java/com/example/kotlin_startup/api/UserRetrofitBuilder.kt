package com.example.kotlin_startup.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object UserRetrofitBuilder {

    const val BASE_URL: String = "https://jsonplaceholder.typicode.com/"

    val retrofitBuilder: Retrofit.Builder by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
    }

    val userApiService: UserApiService by lazy {
        retrofitBuilder.build().create(UserApiService::class.java)
    }
}