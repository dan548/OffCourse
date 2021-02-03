package com.collegeboys.offcourse.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.collegeboys.offcourse.R

class AddContactFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_add_contact, container, false)

        val btnShowQrCode = view.findViewById<Button>(R.id.button_show_qr_code)
        btnShowQrCode.setOnClickListener {

        }

        val btnScanQrCode = view.findViewById<Button>(R.id.button_scan_qr_code)
        btnScanQrCode.setOnClickListener {

        }
        return view
    }
}