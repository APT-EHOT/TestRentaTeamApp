package com.artemiymatchin.testrentateamapp.ui.userscreen

import android.graphics.drawable.Drawable
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
import androidx.navigation.fragment.navArgs
import com.artemiymatchin.testrentateamapp.R
import com.artemiymatchin.testrentateamapp.data.FragmentDataState
import com.artemiymatchin.testrentateamapp.databinding.FragmentUserScreenBinding
import com.artemiymatchin.testrentateamapp.ui.homescreen.UsersAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class UserScreenFragment : Fragment(R.layout.fragment_user_screen) {

    private lateinit var binding: FragmentUserScreenBinding

    private val args by navArgs<UserScreenFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentUserScreenBinding.bind(view)

        val thisUser = args.user

        binding.apply {
            firstNameField.text = thisUser.first_name
            secondNameField.text = thisUser.last_name
            emailField.text = thisUser.email

            Glide.with(this@UserScreenFragment)
                .load(thisUser.avatar)
                .error(R.drawable.ic_launcher_background)
                .listener(object : RequestListener<Drawable> {
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Drawable>?,
                        isFirstResource: Boolean
                    ): Boolean {

                        return false
                    }

                    override fun onResourceReady(
                        resource: Drawable?,
                        model: Any?,
                        target: Target<Drawable>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {

                        return false
                    }
                })
                .into(userImage)
        }
    }
}