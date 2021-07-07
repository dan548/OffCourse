package com.collegeboys.offcourse.repository

import com.collegeboys.offcourse.database.dao.UserDao
import com.collegeboys.offcourse.database.entity.User
import com.collegeboys.offcourse.database.entity.UserSession

class UserRepository(private val userDao: UserDao) {
    fun insert(user: User, session: UserSession) = userDao.insertUserWithSession(user, session)

    fun newSession(session: UserSession) = userDao.upsertSession(session)

    fun getById(id: String): User? = userDao.getById(id)

    fun getByName(name: String): User? = userDao.getByName(name)

    fun getAll(): List<User> = userDao.getUsers()

    fun deleteById(id: String) = userDao.deleteUser(id)
}
