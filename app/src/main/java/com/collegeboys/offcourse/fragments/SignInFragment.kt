package com.collegeboys.offcourse.fragments

import com.collegeboys.offcourse.viewmodel.SignInViewModel
import com.collegeboys.offcourse.database.entity.UserSession
import com.collegeboys.offcourse.R

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import org.koin.android.viewmodel.ext.android.viewModel
import java.time.LocalDateTime

class SignInFragment : Fragment() {
    private lateinit var usernameElement: EditText
    private lateinit var passwordElement: EditText
    private val signInViewModel: SignInViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_sign_in, container, false)
        usernameElement = view.findViewById(R.id.sign_in_username)
        passwordElement = view.findViewById(R.id.sign_in_password)

        val preference = activity?.getPreferences(Context.MODE_PRIVATE)
        val defaultUsername: String? = preference?.getString(
            getString(R.string.shared_pref_username_key), null
        )
        if (defaultUsername != null) {
            view.findViewById<EditText>(R.id.sign_in_username).setText(defaultUsername)
        }

        val signInButton = view.findViewById<Button>(R.id.sign_in_button)
        signInButton.setOnClickListener {
            if (signIn()) {
                Navigation
                    .findNavController(view)
                    .navigate(R.id.action_sign_in_fragment_to_add_contact_fragment)
            }
        }
        return view
    }

    fun signIn(): Boolean {
        val username = usernameElement.text.toString()
        val password = passwordElement.text.toString()

        val user = signInViewModel.getUserByName(username)
        if (user != null) {
            if (user.password == password) {
                signInViewModel.createNewSession(
                    UserSession(userId = user.userId, loginDate = LocalDateTime.now())
                )
                Toast.makeText(context, "Signed!", Toast.LENGTH_LONG).show()
            }
            return true
        }
        return false
    }
}