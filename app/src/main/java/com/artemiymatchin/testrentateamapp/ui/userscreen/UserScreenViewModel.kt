package com.artemiymatchin.testrentateamapp.ui.userscreen

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.artemiymatchin.testrentateamapp.data.UserRepository

class UserScreenViewModel @ViewModelInject constructor(
    private val repository: UserRepository
) : ViewModel() {

    private var id = 0

    private val thisUser = repository.getUserByID(id)

    fun setID(id: Int) {
        this.id = id
    }
}