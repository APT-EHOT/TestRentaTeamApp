package com.artemiymatchin.testrentateamapp.api

import com.artemiymatchin.testrentateamapp.data.User
import retrofit2.http.GET
import retrofit2.http.Query
import io.reactivex.Observable

interface RetrofitApi {

    companion object {
        const val BASE_URL = "https://reqres.in/"
    }

    @GET("api/users")
    fun getUsersFromRemote(
        @Query("page") page: Int
    ): Observable<RetrofitResponse>

    @GET("api/users")
    fun getUserByIdFromRemote(
        @Query("id") id: Int
    ): Observable<User>
}