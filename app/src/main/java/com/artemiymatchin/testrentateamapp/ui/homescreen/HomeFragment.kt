package com.artemiymatchin.testrentateamapp.ui.homescreen

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.artemiymatchin.testrentateamapp.R
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.artemiymatchin.testrentateamapp.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.schedulers.Schedulers

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {

    private val viewModel: HomeViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentHomeBinding.bind(view)

        val usersAdapter = UsersAdapter()

        binding.apply {
            recyclerView.apply {
                adapter = usersAdapter
                layoutManager = LinearLayoutManager(requireContext())
                setHasFixedSize(true)
            }
        }

        viewModel.users
            .subscribeOn(Schedulers.io())
            .doOnNext {
                usersAdapter.submitList(it)
            }
            .subscribe()
    }
}