package com.collegeboys.offcourse.fragments

import android.content.Context
import com.collegeboys.offcourse.database.entity.User
import com.collegeboys.offcourse.database.entity.UserSession
import com.collegeboys.offcourse.viewmodel.CreateAccountViewModel
import com.collegeboys.offcourse.R

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import org.koin.android.viewmodel.ext.android.viewModel
import java.time.LocalDateTime

class CreateAccountFragment : Fragment() {
    private val createAccountViewModel: CreateAccountViewModel by viewModel()

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_create_account, container, false)

        val signUpButton = view.findViewById<Button>(R.id.button_sign_up)
        signUpButton.setOnClickListener {
            createAccount(view)
            val action = CreateAccountFragmentDirections.actionCreateAccountFragmentToSignInFragment()
            Navigation
                .findNavController(view)
                .navigate(R.id.action_create_account_fragment_to_sign_in_fragment)
        }
        return view
    }

    private fun createAccount(view: View) {
        val username = view.findViewById<EditText>(R.id.sign_up_username)
                .text
                .toString()
        val password = view.findViewById<EditText>(R.id.sign_up_password)
                .text
                .toString()
        val confirmedPassword = view.findViewById<EditText>(R.id.sign_up_confirm_password)
                .text
                .toString()

        if (password == confirmedPassword) {
            val user = User(name = username, password = password)
            val session = UserSession(userId = user.userId, loginDate = LocalDateTime.now())
            createAccountViewModel.createAccount(user, session)

            val sharedPreference = activity?.getPreferences(Context.MODE_PRIVATE) ?: return
            with (sharedPreference.edit()) {
                putString(getString(R.string.shared_pref_username_key), username)
                putString(getString(R.string.shared_pref_user_id_key), user.userId)
                apply()
            }
        }
    }
}