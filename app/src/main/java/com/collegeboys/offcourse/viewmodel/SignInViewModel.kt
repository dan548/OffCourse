package com.collegeboys.offcourse.viewmodel

import com.collegeboys.offcourse.repository.UserRepository
import com.collegeboys.offcourse.database.entity.UserSession

import androidx.lifecycle.ViewModel

class SignInViewModel(private val userRepository: UserRepository) : ViewModel() {
    fun getUser() = userRepository.getAll()[0]

    fun createNewSession(session: UserSession) = userRepository.newSession(session)
}