package com.artemiymatchin.testrentateamapp.ui.userscreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.artemiymatchin.testrentateamapp.R
import com.artemiymatchin.testrentateamapp.data.FragmentDataState
import com.artemiymatchin.testrentateamapp.databinding.FragmentUserScreenBinding

class UserScreenFragment : Fragment() {

    private val userScreenViewModel: UserScreenViewModel by viewModels()
    private lateinit var binding: FragmentUserScreenBinding
    private lateinit var state: FragmentDataState


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentUserScreenBinding.bind(view)

        state = FragmentDataState.LOADING
    }
}