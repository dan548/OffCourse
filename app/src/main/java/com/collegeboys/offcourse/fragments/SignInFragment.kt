package com.collegeboys.offcourse.fragments

import com.collegeboys.offcourse.viewmodel.SignInViewModel
import com.collegeboys.offcourse.database.entity.UserSession
import com.collegeboys.offcourse.R

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
    private val signInViewModel: SignInViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_sign_in, container, false)

        val signInButton = view.findViewById<Button>(R.id.sign_in_button)
        signInButton.setOnClickListener {
            if (signIn(view)) {
                val action = SignInFragmentDirections.actionSignInFragmentToBlankFragment()
                Navigation.findNavController(view)
                    .navigate(R.id.action_sign_in_fragment_to_blankFragment)
            }
        }
        return view
    }

    fun signIn(view: View): Boolean {
        val username = view.findViewById<EditText>(R.id.sign_in_username)
            .text
            .toString()
        val password = view.findViewById<EditText>(R.id.sign_in_password)
            .text
            .toString()

        val user = signInViewModel.getUserByName(username)
        if (user != null) {
            if (user.password == password) {
                signInViewModel.createNewSession(
                    UserSession(userId = user.userId, loginDate = LocalDateTime.now())
                )
                Toast.makeText(context, "SIGNED", Toast.LENGTH_LONG).show()
            }
            return true
        }
        return false
    }
}