package com.example.activity_fragment.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.activity_fragment.R

class SecondFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        Log.d("VDung", "Fragment 2 - onCreateView")
        val view = inflater.inflate(R.layout.fragment_second, container, false)
        val btn_close = view.findViewById<Button>(R.id.btn_CloseFragment)

        var bundle = arguments
        val message = bundle?.getString("message")

        view.findViewById<TextView>(R.id.txt_messageFragment2).text = message.toString().trim()

        btn_close.setOnClickListener {
            activity?.supportFragmentManager
                ?.beginTransaction()
                ?.remove(this)
                ?.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                ?.commit()
            activity?.supportFragmentManager?.popBackStack()
        }

        return view
    }


    override fun onStart() {
        Log.d("VDung-Fragment", "Fragment 2 - onStart")
        super.onStart()
    }

    override fun onResume() {
        Log.d("VDung-Fragment", "Fragment 2 - onResume")
        super.onResume()
    }

    override fun onPause() {
        Log.d("VDung-Fragment", "Fragment 2 - onPause")
        super.onPause()
    }

    override fun onStop() {
        Log.d("VDung-Fragment", "Fragment 2 - onStop")
        super.onStop()
    }

    override fun onDestroyView() {
        Log.d("VDung-Fragment", "Fragment 2 - onDestroyView")
        super.onDestroyView()
    }


    override fun onDestroy() {
        Log.d("VDung-Fragment", "Fragment 2 - onDestroy")
        super.onDestroy()
    }

}