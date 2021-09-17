package com.artemiymatchin.testrentateamapp.ui.homescreen

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.artemiymatchin.testrentateamapp.data.UserRepository

class HomeViewModel @ViewModelInject constructor(
    private val repository: UserRepository
) : ViewModel() {

    val users = repository.getUsers()

}