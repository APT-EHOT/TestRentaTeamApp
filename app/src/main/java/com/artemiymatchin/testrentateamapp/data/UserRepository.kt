package com.artemiymatchin.testrentateamapp.data

import com.artemiymatchin.testrentateamapp.api.RetrofitApi
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository @Inject constructor(
    private val retrofitApi: RetrofitApi,
    private val userDao: UserDao
) {

    fun getUsers(): Observable<List<User>> {
        refreshUser() // TODO: add no network handling
        return userDao.loadUsers()
    }

    private fun refreshUser() {
        var page = 1
        var maxPages = 1
        do {
            retrofitApi.getUsers(page)
                .subscribeOn(Schedulers.io())
                .doOnNext {
                    for (user in it.data) {
                        userDao.insert(user)
                    }
                    maxPages = it.total_pages
                }
                .subscribe()
            page++
        } while (page <= maxPages)
    }
}