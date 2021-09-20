package com.artemiymatchin.testrentateamapp.ui.homescreen

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.artemiymatchin.testrentateamapp.data.User
import com.artemiymatchin.testrentateamapp.databinding.UserShortCardBinding


class UsersAdapter :
    ListAdapter<User, UsersAdapter.UsersViewHolder>(DiffCallback()) {

    var onItemClick: ((User) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersViewHolder {
        val binding =
            UserShortCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return UsersViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UsersViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.bind(currentItem)
    }

    inner class UsersViewHolder(private val binding: UserShortCardBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(getItem(adapterPosition))
            }
        }

        fun bind(user: User) {
            binding.apply {
                firstNameField.text = user.first_name
                lastNameField.text = user.last_name
            }

        }
    }


    class DiffCallback : DiffUtil.ItemCallback<User>() {

        override fun areItemsTheSame(oldItem: User, newItem: User) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: User, newItem: User) =
            oldItem == newItem
    }
}