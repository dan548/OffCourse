package com.collegeboys.offcourse.viewmodel

import com.collegeboys.offcourse.database.entity.User
import com.collegeboys.offcourse.database.entity.UserSession
import com.collegeboys.offcourse.repository.UserRepository

import androidx.lifecycle.ViewModel

class CreateAccountViewModel(private val userRepository: UserRepository) : ViewModel() {
    fun createAccount(user: User, session: UserSession) = userRepository.insert(user, session)
}
