package com.collegeboys.offcourse.fragments

import android.content.Context
import android.content.SharedPreferences
import com.collegeboys.offcourse.utils.NetworkUtils

import android.graphics.Bitmap
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.navigation.Navigation
import com.collegeboys.offcourse.R
import com.google.zxing.BarcodeFormat
import com.google.zxing.MultiFormatWriter
import com.journeyapps.barcodescanner.BarcodeEncoder
import org.json.JSONObject

class QrDisplayerFragment : Fragment() {
    private val QR_CODE_IMAGE_LENGTH = 1080
    private val MAIN_PORT = 8888
    private val SECOND_PORT = 8889
    private var scannedOtherQr = false
    private lateinit var prefs: SharedPreferences

    private var myPort = MAIN_PORT

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_qr_displayer, container, false)

        prefs = activity?.getPreferences(Context.MODE_PRIVATE)!!
        scannedOtherQr = prefs.getBoolean(getString(R.string.shared_pref_qr_scanned), false)
        myPort = if (scannedOtherQr) {
            SECOND_PORT
        } else {
            MAIN_PORT
        }
        val myParamsBundle = bundleOf(getString(R.string.connection_port) to myPort.toString())
        setFragmentResult(getString(R.string.my_message_params), myParamsBundle)

        val myConnectionData = generateConnectionData()
        val qrCodeBitmap = generateQrCode(myConnectionData)
        view.findViewById<ImageView>(R.id.qr_code_view).setImageBitmap(qrCodeBitmap)

        view.findViewById<Button>(R.id.qr_show_next).setOnClickListener {
            onClick(it)
        }
        return view
    }

    fun onClick(view: View) {
        if (scannedOtherQr) {
            with(prefs.edit()) {
                putBoolean(getString(R.string.shared_pref_qr_showed), true)
                putBoolean(getString(R.string.shared_pref_qr_scanned), true)
                apply()
            }
            Navigation.findNavController(view).navigate(R.id.chat_fragment)
        } else {
            with(prefs.edit()) {
                putBoolean(getString(R.string.shared_pref_qr_showed), true)
                putBoolean(getString(R.string.shared_pref_qr_scanned), false)
                apply()
            }
            Navigation.findNavController(view).navigate(R.id.qr_scanner_fragment)
        }
    }

    private fun generateConnectionData(): String {
        val sharedPreference = activity?.getPreferences(Context.MODE_PRIVATE)
        val userId = sharedPreference
            ?.getString(getString(R.string.shared_pref_user_id_key), "")
        val username = sharedPreference
            ?.getString(getString(R.string.shared_pref_username_key), "")

        val jsonObject = JSONObject()
        jsonObject.put(getString(R.string.connection_user_id), userId)
        jsonObject.put(getString(R.string.connection_username), username)
        jsonObject.put(getString(R.string.connection_address), NetworkUtils.getIpAddress())
        jsonObject.put(getString(R.string.connection_port), myPort)
        return jsonObject.toString()
    }

    private fun generateQrCode(data: String): Bitmap {
        val multiFormatWriter = MultiFormatWriter()
        val bitMatrix = multiFormatWriter.encode(
            data, BarcodeFormat.QR_CODE,
            QR_CODE_IMAGE_LENGTH, QR_CODE_IMAGE_LENGTH
        )
        return BarcodeEncoder().createBitmap(bitMatrix)
    }
}