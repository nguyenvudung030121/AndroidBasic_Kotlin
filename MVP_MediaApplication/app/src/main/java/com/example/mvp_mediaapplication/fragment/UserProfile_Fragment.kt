package com.example.mvp_mediaapplication.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.mvp_mediaapplication.R
import com.example.mvp_mediaapplication.dataSource.User
import com.example.mvp_mediaapplication.databinding.FragmentUserProfileBinding


class UserProfile_Fragment : Fragment() {

    lateinit var binding:FragmentUserProfileBinding
    lateinit var user: User
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentUserProfileBinding.inflate(inflater)

        getBundle()

        binding.activity = user


        return binding.root
    }


    fun getBundle(){
        val bundle = arguments
        user = bundle?.getParcelable("data")!!
    }

    fun onLogout(){
        activity?.finish()
    }

}