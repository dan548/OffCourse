package com.collegeboys.offcourse.fragments

import com.collegeboys.offcourse.viewmodel.SignInViewModel
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
import com.collegeboys.offcourse.database.entity.UserSession
import org.koin.android.viewmodel.ext.android.viewModel

class SignInFragment : Fragment() {
    private val signInViewModel: SignInViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_sign_in, container, false)

        val signInButton = view.findViewById<Button>(R.id.button_sign_in)
        signInButton.setOnClickListener {
            signIn(view)
            val action = SignInFragmentDirections.actionSignInFragmentToBlankFragment()
            Navigation.findNavController(view).navigate(R.id.action_sign_in_fragment_to_blankFragment)
        }
        return view
    }

    fun signIn(view: View) {
        val password = view.findViewById<EditText>(R.id.editTextEnterPassword)
            .text
            .toString()

        val user = signInViewModel.getUser()
        if (user.password == password) {
            signInViewModel.createNewSession(UserSession(userId = user.userId))

            Toast.makeText(context, "SIGNED", Toast.LENGTH_LONG).show()
        }
    }
}