package com.collegeboys.offcourse

import android.app.Application
import com.collegeboys.offcourse.di.*
import org.koin.android.ext.android.startKoin

class OffCourseApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin(
            this,
            listOf(
                databaseModule, userModule,
                createAccountModule, signInModule,
                contactModule, messageModule
            )
        )
    }
}