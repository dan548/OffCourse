package com.collegeboys.offcourse

import com.collegeboys.offcourse.di.databaseModule
import com.collegeboys.offcourse.di.userModule
import com.collegeboys.offcourse.di.createAccountModule

import android.app.Application
import org.koin.android.ext.android.startKoin

class OffCourseApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin(this,
                listOf(databaseModule, userModule,
                        createAccountModule)
        )
    }
}