package com.example.activity_fragment.fragment

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.activity_fragment.R

class FirstFragment : DialogFragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        Log.d("VDung-Fragment", "Fragment 1 - onCreateView")

        val view = inflater.inflate(R.layout.fragment_first, container, false)
        val btn_SendMessage = view.findViewById<Button>(R.id.sendToFragment2)
        var bundle = arguments
        val message = bundle?.getString("message")

        view.findViewById<TextView>(R.id.txt_messageFragment1).text = message.toString().trim()

        btn_SendMessage.setOnClickListener {
            val fragment2 = SecondFragment()
            fragment2.arguments = bundle
            activity?.supportFragmentManager?.beginTransaction()
                ?.setReorderingAllowed(true)
                ?.addToBackStack(null)
                ?.replace(R.id.framelayout_2,fragment2)
                ?.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                ?.commit()
        }

        return view
    }

    override fun onAttach(context: Context) {
        Log.d("VDung-Fragment", "Fragment 1 - onAttach")
        super.onAttach(context)
    }

    override fun onStart() {
        Log.d("VDung-Fragment", "Fragment 1 - onStart")
        super.onStart()
    }

    override fun onResume() {
        Log.d("VDung-Fragment", "Fragment 1 - onResume")
        super.onResume()
    }

    override fun onPause() {
        Log.d("VDung-Fragment", "Fragment 1 - onPause")
        super.onPause()
    }

    override fun onStop() {
        Log.d("VDung-Fragment", "Fragment 1 - onStop")
        super.onStop()
    }

    override fun onDestroyView() {
        Log.d("VDung-Fragment", "Fragment 1 - onDestroyView")
        super.onDestroyView()
    }


    override fun onDestroy() {
        Log.d("VDung-Fragment", "Fragment 1 - onDestroy")
        super.onDestroy()
    }


}