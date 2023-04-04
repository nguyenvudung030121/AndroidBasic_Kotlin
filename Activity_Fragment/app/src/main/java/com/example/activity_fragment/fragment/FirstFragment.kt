package com.example.activity_fragment.fragment

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Button
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.example.activity_fragment.R

class FirstFragment : DialogFragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_first, container, false)
        val btn_SendMessage = view.findViewById<Button>(R.id.sendToFragment2)

        btn_SendMessage.setOnClickListener{
            /*val fragment2 = SecondFragment()
            activity?.supportFragmentManager?.beginTransaction()
                ?.setReorderingAllowed(true)
                ?.add(R.id.framelayout_2,fragment2)
                ?.commit()*/
            dialogFragment(container)
        }

        return view
    }

    private fun dialogFragment(container: ViewGroup?) {
        val dialog = container?.let { Dialog(it.context) }
        dialog?.setContentView(R.layout.fragment_second)
        val window = dialog?.window ?: return
        window.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )

        val btn_cancel = dialog.findViewById<Button>(R.id.btn_CloseFragment)

        btn_cancel.setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()
    }
    override fun onStop() {
        Log.d("VDung", "Fragment 1 - onStop")
        super.onStop()
    }

    override fun onPause() {
        Log.d("VDung", "Fragment 1 - onStop")
        super.onPause()
    }
}