package com.example.asynctask

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.asynctask.databinding.FragmentCountdownBinding


class CountdownFragment : Fragment() {
    lateinit var binding:FragmentCountdownBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCountdownBinding.inflate(inflater,container,false)

        binding.btnCount.setOnClickListener{
                doCountDown()
        }

        return binding.root
    }

    private fun doCountDown() {

    }

}