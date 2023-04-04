package com.example.activity_fragment.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.activity_fragment.R

class SecondFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        Log.d("VDung", "Fragment 2 - onCreateView")
        return inflater.inflate(R.layout.fragment_second, container, false)
    }



    override fun onStop() {
        Log.d("VDung", "Fragment 2 - onStop")
        super.onStop()
    }

    override fun onDestroy() {
        Log.d("VDung", "Fragment 1 - onDestroy")
        super.onDestroy()
    }

}