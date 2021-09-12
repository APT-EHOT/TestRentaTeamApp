package com.artemiymatchin.testrentateamapp.api

import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitApi {

    companion object {
        const val BASE_URL = "https://reqres.in/"
    }

    @GET("api/users")
    suspend fun getUsers(
        @Query("page") page: Int
    ): RetrofitResponse
}