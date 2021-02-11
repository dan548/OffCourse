package com.collegeboys.offcourse.fragments

import com.collegeboys.offcourse.R
import com.collegeboys.offcourse.repository.ContactRepository

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.setFragmentResult
import androidx.navigation.Navigation
import org.koin.android.ext.android.inject

class ScanQrCodeFragment : Fragment() {
    private lateinit var addressElement: EditText
    private lateinit var portElement: EditText
    private val contactRepository by inject<ContactRepository>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_scan_qr_code, container, false)
        addressElement = view.findViewById(R.id.editTextTextPersonName)
        portElement = view.findViewById(R.id.editTextTextPersonName3)

        view.findViewById<Button>(R.id.button2).setOnClickListener {
            onClick()
            val action = ScanQrCodeFragmentDirections.actionScanQrCodeFragmentToChatFragment()
            Navigation
                .findNavController(view)
                .navigate(R.id.chat_fragment)
        }
        return view
    }

    private fun onClick() {
        val address = addressElement.text.toString()
        val port = portElement.text.toString()

        val otherUserParamsBundle = Bundle()
        otherUserParamsBundle.putString("ip_address", address)
        otherUserParamsBundle.putString("port", port)
        setFragmentResult(getString(R.string.companion_message_params), otherUserParamsBundle)
    }
}