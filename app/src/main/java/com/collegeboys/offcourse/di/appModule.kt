package com.collegeboys.offcourse.di

import com.collegeboys.offcourse.database.OffCourseAppDatabase
import com.collegeboys.offcourse.repository.UserRepository

import androidx.room.Room
import com.collegeboys.offcourse.repository.ContactRepository
import com.collegeboys.offcourse.viewmodel.CreateAccountViewModel
import com.collegeboys.offcourse.viewmodel.SignInViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.android.viewmodel.ext.koin.viewModel
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

val createAccountModule = module {
    viewModel("CreateAccountViewModel") {
        CreateAccountViewModel(get())
    }
}

val signInModule = module {
    viewModel("SignInViewModel") {
        SignInViewModel(get())
    }
}

val contactModule = module {
    single("ContactDao") {
        get<OffCourseAppDatabase>().contactDao()
    }

    single("ContactRepository") {
        ContactRepository(get())
    }
}
