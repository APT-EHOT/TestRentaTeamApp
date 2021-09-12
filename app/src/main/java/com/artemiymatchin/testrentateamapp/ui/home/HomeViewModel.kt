package com.artemiymatchin.testrentateamapp.ui.home

import androidx.hilt.Assisted
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.artemiymatchin.testrentateamapp.data.UserRepository
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val repository: UserRepository
) : ViewModel() {


    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text
}