package com.collegeboys.offcourse.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.collegeboys.offcourse.R

class GettingStartedFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_getting_started, container, false)

        val btnSignIn = view.findViewById<Button>(R.id.button_goto_sign_in)
        val btnSignUp = view.findViewById<Button>(R.id.button_goto_sign_up)

        btnSignIn.setOnClickListener {
            val action = GettingStartedFragmentDirections.actionGettingStartedFragmentToSignInFragment()
            Navigation.findNavController(view).navigate(R.id.action_getting_started_fragment_to_sign_in_fragment)
        }

        btnSignUp.setOnClickListener {
            val action = GettingStartedFragmentDirections.actionGettingStartedFragmentToCreateAccountFragment()
            Navigation.findNavController(view).navigate(R.id.action_getting_started_fragment_to_create_account_fragment)
        }
        return view
    }
}