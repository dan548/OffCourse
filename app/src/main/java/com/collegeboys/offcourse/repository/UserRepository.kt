package com.collegeboys.offcourse.repository

import com.collegeboys.offcourse.database.dao.UserDao
import com.collegeboys.offcourse.database.entity.User

class UserRepository(private val userDao: UserDao) {
    fun insert(user: User) = userDao.insert(user)

    fun getById(id: String): User = userDao.getById(id)

    fun getByName(name: String) = userDao.getByName(name)

    fun deleteById(id: String) = userDao.deleteById(id)
}