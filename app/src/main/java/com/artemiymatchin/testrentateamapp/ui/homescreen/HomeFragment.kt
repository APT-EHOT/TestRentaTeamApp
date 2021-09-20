package com.artemiymatchin.testrentateamapp.ui.homescreen

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.artemiymatchin.testrentateamapp.R
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.artemiymatchin.testrentateamapp.data.FragmentDataState
import com.artemiymatchin.testrentateamapp.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit


@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {

    private val viewModel: HomeViewModel by viewModels()
    private lateinit var binding: FragmentHomeBinding
    private lateinit var dataState: FragmentDataState

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeBinding.bind(view)

        dataState = FragmentDataState.LOADING

        val usersAdapter = UsersAdapter()

        // Handling onItemClick for recyclerView Item
        usersAdapter.onItemClick = { thisItemUser ->
            val action = HomeFragmentDirections.actionHomeToUserInfo(thisItemUser)
            findNavController().navigate(action)
        }

        binding.apply {
            recyclerView.apply {
                adapter = usersAdapter
                layoutManager = LinearLayoutManager(requireContext())
                setHasFixedSize(true)
            }
        }

        updateUI()

        viewModel.users
            .subscribeOn(Schedulers.io())
            .doOnNext {
                usersAdapter.submitList(it)
            }
            .delay(250, TimeUnit.MILLISECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .doAfterNext {
                dataState = if (usersAdapter.itemCount == 0)
                    FragmentDataState.EMPTYCACHE
                else
                    FragmentDataState.SUCCESS
                updateUI()
            }
            .subscribe()
    }

    private fun updateUI() {
        binding.apply {
            when (dataState) {
                FragmentDataState.LOADING -> {
                    progressBar.isVisible = true
                    textViewEmpty.isVisible = false
                    recyclerView.isVisible = false
                }
                FragmentDataState.EMPTYCACHE -> {
                    progressBar.isVisible = false
                    textViewEmpty.isVisible = true
                    recyclerView.isVisible = false
                }
                FragmentDataState.SUCCESS -> {
                    progressBar.isVisible = false
                    textViewEmpty.isVisible = false
                    recyclerView.isVisible = true
                }
            }
        }
    }
}