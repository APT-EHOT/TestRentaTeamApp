package com.artemiymatchin.testrentateamapp.ui.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.artemiymatchin.testrentateamapp.R
import androidx.fragment.app.viewModels
import com.artemiymatchin.testrentateamapp.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {

    private val viewModel: HomeViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentHomeBinding.bind(view)


    }
}