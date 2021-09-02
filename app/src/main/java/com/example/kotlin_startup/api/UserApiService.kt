package com.example.kotlin_startup.api


import com.example.kotlin_startup.model.User
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface UserApiService {
    @GET("/albums/{id}/photos")
    suspend fun getUserList(
        @Path("id") albumId: Int
    ): Response<List<User>>
}