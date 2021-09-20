package com.artemiymatchin.testrentateamapp.data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "user_table")
@Parcelize
data class User(
    @PrimaryKey val id: Int,
    val email: String,
    val first_name: String,
    val last_name: String,
    val avatar: String
) : Parcelable
