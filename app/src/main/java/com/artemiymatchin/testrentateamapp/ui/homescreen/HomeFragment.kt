package com.artemiymatchin.testrentateamapp.ui.homescreen

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.artemiymatchin.testrentateamapp.R
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.artemiymatchin.testrentateamapp.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {

    private val viewModel: HomeViewModel by viewModels()
    private lateinit var binding: FragmentHomeBinding
    private lateinit var state: HomeFragmentState

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeBinding.bind(view)

        state = HomeFragmentState.LOADING

        val usersAdapter = UsersAdapter()

        updateUI()

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
                state = if (usersAdapter.itemCount == 0)
                    HomeFragmentState.EMPTYCACHE
                else
                    HomeFragmentState.SUCCESS
            }
            .observeOn(AndroidSchedulers.mainThread())
            .doAfterNext {
                updateUI()
            }
            .subscribe()
    }

    private fun updateUI() {
        binding.apply {
            when (state) {
                HomeFragmentState.LOADING -> {
                    progressBar.isVisible = true
                    textViewEmpty.isVisible = false
                    recyclerView.isVisible = false
                }
                HomeFragmentState.EMPTYCACHE -> {
                    progressBar.isVisible = false
                    textViewEmpty.isVisible = true
                    recyclerView.isVisible = false
                }
                HomeFragmentState.SUCCESS -> {
                    progressBar.isVisible = false
                    textViewEmpty.isVisible = false
                    recyclerView.isVisible = true
                }
            }
        }
    }
}