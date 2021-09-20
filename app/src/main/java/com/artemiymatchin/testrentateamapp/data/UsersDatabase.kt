package com.artemiymatchin.testrentateamapp.data

import androidx.room.Database
import androidx.room.RoomDatabase
import javax.inject.Inject
import javax.inject.Provider

@Database(entities = [User::class], version = 1)
abstract class UsersDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

    class Callback @Inject constructor(
        private val database: Provider<UsersDatabase>,
    ) : RoomDatabase.Callback()
}