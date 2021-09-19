package com.artemiymatchin.testrentateamapp.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Observable

@Dao
interface UserDao {

    @Query("SELECT * FROM user_table")
    fun loadUsersFromDB(): Observable<List<User>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: User)

    @Query("SELECT * FROM user_table WHERE id = :id")
    fun loadUserFromDB(id: Int)
}