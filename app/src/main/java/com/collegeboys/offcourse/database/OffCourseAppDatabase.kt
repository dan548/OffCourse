package com.collegeboys.offcourse.database

import com.collegeboys.offcourse.database.entity.User
import com.collegeboys.offcourse.database.entity.UserSession
import com.collegeboys.offcourse.database.entity.Contact
import com.collegeboys.offcourse.database.entity.Message
import com.collegeboys.offcourse.database.dao.UserDao
import com.collegeboys.offcourse.database.dao.ContactDao
import com.collegeboys.offcourse.database.dao.MessageDao
import com.collegeboys.offcourse.database.converters.LocalDateTimeConverter

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(
    entities = [User::class, UserSession::class, Contact::class, Message::class],
    version = 3,
    exportSchema = false
)
@TypeConverters(
    LocalDateTimeConverter::class
)
abstract class OffCourseAppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun contactDao(): ContactDao
    abstract fun messageDao(): MessageDao

    companion object {
        const val DATABASE_NAME = "offcourse_db"
    }
}
