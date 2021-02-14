package com.collegeboys.offcourse.fragments

import android.Manifest
import android.app.Dialog
import android.content.Context
import android.content.SharedPreferences
import android.content.pm.PackageManager
import com.collegeboys.offcourse.repository.ContactRepository

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.setFragmentResult
import androidx.navigation.Navigation
import com.collegeboys.offcourse.R
import com.google.zxing.BarcodeFormat

import com.google.zxing.ResultPoint
import com.journeyapps.barcodescanner.BarcodeCallback
import com.journeyapps.barcodescanner.BarcodeResult
import com.journeyapps.barcodescanner.DecoratedBarcodeView
import com.journeyapps.barcodescanner.DefaultDecoderFactory
import org.json.JSONException
import org.json.JSONObject
import org.koin.android.ext.android.inject
import java.util.*

class QrScannerFragment : Fragment(), BarcodeCallback {
    private val contactRepository: ContactRepository by inject()
    private lateinit var barcodeView: DecoratedBarcodeView
    private var myQrShowed = false
    private lateinit var prefs: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_qr_scanner, container, false)

        val hasCameraPermission = ContextCompat.checkSelfPermission(
            requireContext(),
            Manifest.permission.CAMERA
        ) == PackageManager.PERMISSION_GRANTED

        if (!hasCameraPermission) {
            activity?.let {
                ActivityCompat.requestPermissions(
                    it, arrayOf(Manifest.permission.CAMERA), 1
                )
            }
        }
        initCamera(view)

        prefs = activity?.getPreferences(Context.MODE_PRIVATE)!!
        myQrShowed = prefs.getBoolean(getString(R.string.shared_pref_qr_showed), false)
        return view
    }

    private fun initCamera(view: View) {
        barcodeView = view.findViewById(R.id.barcodeScannerView)
        val formats = Collections.singletonList(BarcodeFormat.QR_CODE)
        barcodeView.barcodeView.decoderFactory = DefaultDecoderFactory(formats)
        barcodeView.decodeContinuous(this)
        barcodeView.resume()
    }

    override fun onPause() {
        super.onPause()
        barcodeView.pause()
    }

    override fun onResume() {
        super.onResume()
        barcodeView.resume()
    }

    private fun addNewContact(scannedData: String) {
        val jsonObject = JSONObject(scannedData)
        val contactId = jsonObject.getString(getString(R.string.connection_user_id))
        val username = jsonObject.getString(getString(R.string.connection_username))
        val address = jsonObject.getString(getString(R.string.connection_address))
        val port = jsonObject.getInt(getString(R.string.connection_port)).toString()

        val otherUserParamsBundle = Bundle()
        otherUserParamsBundle.putString(getString(R.string.connection_user_id), contactId)
        otherUserParamsBundle.putString(getString(R.string.connection_username), username)
        otherUserParamsBundle.putString(getString(R.string.connection_address), address)
        otherUserParamsBundle.putString(getString(R.string.connection_port), port)
        setFragmentResult(getString(R.string.companion_message_params), otherUserParamsBundle)
    }

    private fun showConfirmDialog() {
        val dialog = Dialog(requireContext())
        dialog.setContentView(R.layout.dialog_confirm_new_contact)
        val okButton = dialog.findViewById<Button>(R.id.confirm_contact_ok)
        val cancelButton = dialog.findViewById<Button>(R.id.confirm_contact_cancel)

        okButton.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.chat_fragment)
        }

        cancelButton.setOnClickListener {
            dialog.cancel()
            barcodeView.resume()
        }
        dialog.show()
    }

    override fun barcodeResult(result: BarcodeResult?) {
        barcodeView.pause()
        try {
            val scannedData: String? = result?.text
            addNewContact(scannedData!!)

            view?.let {
                onScanned(it)
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }
    }

    private fun onScanned(view: View) {
        if (myQrShowed) {
            with(prefs.edit()) {
                putBoolean(getString(R.string.shared_pref_qr_showed), true)
                putBoolean(getString(R.string.shared_pref_qr_scanned), true)
                apply()
            }
            Navigation.findNavController(view).navigate(R.id.chat_fragment)
        } else {
            with(prefs.edit()) {
                putBoolean(getString(R.string.shared_pref_qr_showed), false)
                putBoolean(getString(R.string.shared_pref_qr_scanned), true)
                apply()
            }
            Navigation.findNavController(view).navigate(R.id.qr_displayer_fragment)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            view?.let { initCamera(it) }
        } else {
            Toast.makeText(context, "Camera permission needed", Toast.LENGTH_LONG).show()
        }
    }

    override fun possibleResultPoints(resultPoints: MutableList<ResultPoint>?) {
        // ignore this
    }
}