package com.collegeboys.offcourse.fragments

import com.collegeboys.offcourse.R

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.google.android.material.floatingactionbutton.FloatingActionButton

class AllMessagesFragment : Fragment() {
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_all_messages, container, false)
        val addContactButton = view.findViewById<FloatingActionButton>(R.id.add_contact_button)
        addContactButton.setOnClickListener {
//            val action = AllMessagesFragmentDirections.actionAllMessagesFragmentToAddContactFragment()
//            Navigation
//                    .findNavController(view)
//                    .navigate(R.id.action_all_messages_fragment_to_add_contact_fragment)
        }
        return view
    }
}