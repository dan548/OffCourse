package com.collegeboys.offcourse

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        setupActionBarWithNavController(navController)

        navController.addOnDestinationChangedListener { _, _, _ ->
            actionBar?.title = navController.currentDestination?.label
        }

        val actionBar = supportActionBar
        val colorDrawable = ColorDrawable(Color.parseColor("#004C99"))
        actionBar?.setBackgroundDrawable(colorDrawable)

    }
}