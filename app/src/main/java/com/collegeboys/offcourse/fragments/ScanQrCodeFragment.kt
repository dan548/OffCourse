package com.collegeboys.offcourse.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import com.collegeboys.offcourse.R
import com.collegeboys.offcourse.connection.socket.MessageSenderThread

class ScanQrCodeFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_scan_qr_code, container, false)
        view.findViewById<Button>(R.id.button2).setOnClickListener {
            onClick(view)
        }
        return view
    }

    private fun onClick(view: View) {
        val address = view.findViewById<EditText>(R.id.editTextTextPersonName).text.toString()
        val port = view.findViewById<EditText>(R.id.editTextTextPersonName3).text.toString()

        val clientClass = MessageSenderThread(address, port.toInt())
        clientClass.start()
    }
}