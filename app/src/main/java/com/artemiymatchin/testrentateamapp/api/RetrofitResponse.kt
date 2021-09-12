package com.artemiymatchin.testrentateamapp.api

import android.os.Parcelable
import com.artemiymatchin.testrentateamapp.data.User
import kotlinx.android.parcel.Parcelize


@Parcelize
data class RetrofitResponse(
    val page: Int,
    val per_page: Int,
    val total: Int,
    val total_pages: Int,
    val data: List<User>
) : Parcelable
