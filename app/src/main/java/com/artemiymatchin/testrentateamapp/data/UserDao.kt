package com.artemiymatchin.testrentateamapp.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Observable

@Dao
interface UserDao {

    @Query("SELECT * FROM user_table")
    fun getUsers(): Observable<List<User>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: User)

}