package com.collegeboys.offcourse.database

import com.collegeboys.offcourse.database.entity.User
import com.collegeboys.offcourse.database.entity.UserSession
import com.collegeboys.offcourse.database.dao.UserDao
import com.collegeboys.offcourse.database.converters.LocalDateTimeConverter

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.collegeboys.offcourse.database.dao.ContactDao
import com.collegeboys.offcourse.database.entity.Contact

@Database(
    entities = [User::class, UserSession::class, Contact::class],
    version = 2,
    exportSchema = false
)
@TypeConverters(
    LocalDateTimeConverter::class
)
abstract class OffCourseAppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun contactDao(): ContactDao

    companion object {
        const val DATABASE_NAME = "offcourse_db"
    }
}
