package com.collegeboys.offcourse.database

import com.collegeboys.offcourse.database.entity.User
import com.collegeboys.offcourse.database.dao.UserDao

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [User::class],
    version = 1,
    exportSchema = false
)
abstract class OffCourseAppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao

    companion object {
        const val DATABASE_NAME = "offcourse_db"
    }
}
