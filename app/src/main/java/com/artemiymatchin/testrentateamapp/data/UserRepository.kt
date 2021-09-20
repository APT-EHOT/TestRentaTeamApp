package com.artemiymatchin.testrentateamapp.data

import android.annotation.SuppressLint
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
        tryToFetchUsers()
        return userDao.loadUsersFromDB()
    }


    private fun tryToFetchUsers() {

        /*
        This method can be better, but in my opinion this is acceptable in a short test app,
        because it works

        First query is for numbers of pages, other is for data
        */

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
                        .subscribe({}, { e -> e.printStackTrace() })
                }
            }
            .subscribe({}, { e -> e.printStackTrace() })
    }


    fun getUserByID(id: Int): Observable<User> {
        tryToFetchUserByID(id)
        return userDao.loadUserFromDB(id)
    }


    private fun tryToFetchUserByID(id: Int) {
        retrofitApi.getUserByIdFromRemote(id)
            .subscribeOn(Schedulers.io())
            .doOnNext {
                userDao.insert(it)
            }
            .subscribe({}, { e -> e.printStackTrace() })
    }
}