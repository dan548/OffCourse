package com.collegeboys.offcourse.viewmodel

import com.collegeboys.offcourse.repository.UserRepository
import com.collegeboys.offcourse.database.entity.UserSession

import androidx.lifecycle.ViewModel

class SignInViewModel(private val userRepository: UserRepository) : ViewModel() {
    fun getUserByName(username: String) = userRepository.getByName(username)

    fun createNewSession(session: UserSession) = userRepository.newSession(session)
}