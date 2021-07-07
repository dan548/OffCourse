package com.collegeboys.offcourse.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.Navigation
import com.collegeboys.offcourse.R

class AddContactFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_add_contact, container, false)

        val btnShowQrCode = view.findViewById<Button>(R.id.button_show_qr_code)
        btnShowQrCode.setOnClickListener {
            val action = AddContactFragmentDirections.actionAddContactFragmentToQrDisplayerFragment()
            Navigation
                .findNavController(view)
                .navigate(R.id.action_add_contact_fragment_to_qr_displayer_fragment)
        }

        val btnScanQrCode = view.findViewById<Button>(R.id.button_scan_qr_code)
        btnScanQrCode.setOnClickListener {
            val action = AddContactFragmentDirections.actionAddContactFragmentToQrScannerFragment()
            Navigation
                .findNavController(view)
                .navigate(R.id.action_add_contact_fragment_to_qr_scanner_fragment)
        }

        val prefs = activity?.getPreferences(Context.MODE_PRIVATE)
        with (prefs!!.edit()) {
            putBoolean(getString(R.string.shared_pref_qr_showed), false)
            putBoolean(getString(R.string.shared_pref_qr_scanned), false)
            apply()
        }
        return view
    }
}