package com.collegeboys.offcourse.database.dao

import androidx.room.*
import com.collegeboys.offcourse.database.entity.User
import com.collegeboys.offcourse.database.entity.UserSession
import com.collegeboys.offcourse.database.entity.UserWithSession

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsertUser(user: User)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsertSession(session: UserSession)

    @Transaction
    fun insertUserWithSession(user: User, session: UserSession) {
        upsertUser(user)
        upsertSession(session)
    }

    @Query("SELECT * FROM users WHERE user_id = :id")
    fun getById(id: String): User?

    @Query("SELECT * FROM users WHERE name = :name")
    fun getByName(name: String): User?

    @Query("SELECT * FROM users")
    fun getUsers(): List<User>

    @Query("DELETE FROM users WHERE user_id = :id")
    fun deleteUser(id: String)

    @Query("DELETE FROM sessions WHERE user_id = :userId")
    fun deleteSession(userId: String)

    @Transaction
    fun deleteUserWithSession(userId: String) {
        deleteUser(userId)
        deleteSession(userId)
    }
}
