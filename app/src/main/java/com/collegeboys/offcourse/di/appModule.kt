package com.collegeboys.offcourse.di

import com.collegeboys.offcourse.database.OffCourseAppDatabase
import com.collegeboys.offcourse.repository.UserRepository

import androidx.room.Room
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module.module

val databaseModule = module {
    single {
        Room.databaseBuilder(
            androidApplication(),
            OffCourseAppDatabase::class.java,
            OffCourseAppDatabase.DATABASE_NAME
        )
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()
    }
}

val userModule = module {
    single("UserDao") {
        get<OffCourseAppDatabase>().userDao()
    }

    single("UserRepository") {
        UserRepository(get())
    }
}
