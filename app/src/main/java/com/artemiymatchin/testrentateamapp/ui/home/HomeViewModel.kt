package com.artemiymatchin.testrentateamapp.ui.home

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.artemiymatchin.testrentateamapp.data.User
import com.artemiymatchin.testrentateamapp.data.UserRepository
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

class HomeViewModel @ViewModelInject constructor(
    private val repository: UserRepository
) : ViewModel() {

    val users = repository.getUsers()

}