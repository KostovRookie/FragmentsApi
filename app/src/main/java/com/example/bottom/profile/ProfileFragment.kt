package com.example.bottom.profile

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.bottom.R
import com.example.bottom.databinding.FragmentProfileBinding
import com.example.bottom.models.UserModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : Fragment(R.layout.fragment_profile) {
    private lateinit var binding: FragmentProfileBinding
    private val viewModel: ProfileViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentProfileBinding.bind(view)


        viewModel.profileDataResponse
            .observe(viewLifecycleOwner) {
                if (it.first != null) {
                    val list = it.first
                    list?.let { it1 -> setData(it1) }
                } else {
                    Toast.makeText(context, "Error : ${it.second?.message}", Toast.LENGTH_LONG)
                        .show()
                }
            }


    }

    private fun setData(model: UserModel) {
        binding.name.text = model.name
        binding.bio.text = "Bio : ${model.id}"
        binding.username.text = "@${model.login}"
        binding.publicGistsCountTv.text = model.url.toString()
        binding.publicRepoCountTv.text = model.html_url.toString()

        Glide.with(this)
            .load(model.avatar_url.toString())
            .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
            .into(binding.profilePic)
    }
}