package com.artemiymatchin.testrentateamapp.data

import com.artemiymatchin.testrentateamapp.api.RetrofitApi
import com.artemiymatchin.testrentateamapp.api.RetrofitResponse
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


        // TODO: Look for other way to do this

        retrofitApi.getUsersFromRemote(1)
            .subscribeOn(Schedulers.io())
            .doOnNext {
                for (page in 1..it.total_pages) {

                    retrofitApi.getUsersFromRemote(page)
                        .subscribeOn(Schedulers.io())
                        .doOnNext { response ->
                            for (user in response.data) {
                                userDao.insert(user)
                            }
                        }
                        .subscribe()
                }
            }
            .subscribe()
    }
}