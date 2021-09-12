package com.artemiymatchin.testrentateamapp.data

import com.artemiymatchin.testrentateamapp.api.RetrofitApi
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository @Inject constructor(
    private val retrofitApi: RetrofitApi
) {

}