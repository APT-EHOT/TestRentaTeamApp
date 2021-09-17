package com.artemiymatchin.testrentateamapp.api

import retrofit2.http.GET
import retrofit2.http.Query
import io.reactivex.Observable

interface RetrofitApi {

    companion object {
        const val BASE_URL = "https://reqres.in/"
    }

    @GET("api/users")
    fun getUsers(
        @Query("page") page: Int
    ): Observable<RetrofitResponse>
}