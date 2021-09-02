package com.example.kotlin_startup.repository

import com.example.kotlin_startup.api.UserRetrofitBuilder

object UserRepository {
    suspend fun getAllUserList(id: Int) = UserRetrofitBuilder.userApiService.getUserList(id)


}