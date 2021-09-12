package com.artemiymatchin.testrentateamapp.data

import com.artemiymatchin.testrentateamapp.api.RetrofitApi
import io.reactivex.Flowable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository @Inject constructor(
    private val retrofitApi: RetrofitApi,
    private val userDao: UserDao
) {

    fun getUsers(): Flowable<List<User>> {
        refreshUser() // TODO: add no network handling
        return userDao.getUsers()
    }

    private fun refreshUser() {
        var page = 1
        do {
            val response = retrofitApi.getUsers(page)
            for (user in response.data)
                userDao.insert(user)
            page++
        } while (page <= response.total_pages)
    }
}