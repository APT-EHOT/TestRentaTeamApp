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

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home),
    UsersAdapter.OnItemClickListener {

    private val viewModel: HomeViewModel by viewModels()
    private lateinit var binding: FragmentHomeBinding
    private lateinit var dataState: FragmentDataState

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeBinding.bind(view)

        dataState = FragmentDataState.LOADING

        val usersAdapter = UsersAdapter()
        usersAdapter.onItemClick = {
            val action = HomeFragmentDirections.actionHomeToUserInfo(it.id)
            findNavController().navigate(action)
        }

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
                dataState = if (usersAdapter.itemCount == 0)
                    FragmentDataState.EMPTYCACHE
                else
                    FragmentDataState.SUCCESS
            }
            .observeOn(AndroidSchedulers.mainThread())
            .doAfterNext {
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

    override fun onItemClick(id: Int) {

    }
}