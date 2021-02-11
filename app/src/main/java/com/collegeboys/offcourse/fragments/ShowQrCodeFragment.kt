package com.collegeboys.offcourse.fragments

import com.collegeboys.offcourse.R

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult

class ShowQrCodeFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_show_qr_code, container, false)
        view.findViewById<Button>(R.id.button).setOnClickListener {
            onClick(view)
        }
        return view
    }

    private fun onClick(view: View) {
        val port = view.findViewById<EditText>(R.id.editTextTextPersonName2).text.toString()

        val myParamsBundle = bundleOf("port" to port)
        setFragmentResult(getString(R.string.my_message_params), myParamsBundle)
    }
}