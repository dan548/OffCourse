package com.collegeboys.offcourse.database.dao

import com.collegeboys.offcourse.database.entity.User

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: User)

    @Query("SELECT * FROM users WHERE id = :id")
    fun getById(id: String): User

    @Query("SELECT * FROM users WHERE name = :name")
    fun getByName(name: String): User

    @Query("DELETE FROM users WHERE id = :id")
    fun deleteById(id: String)
}
