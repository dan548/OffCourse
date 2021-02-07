package com.collegeboys.offcourse

import com.collegeboys.offcourse.connection.socket.SendReceiveMessageThread
import com.collegeboys.offcourse.repository.MessageRepository

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import com.collegeboys.offcourse.database.entity.Message
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {
    val messageRepository: MessageRepository by inject()

    val handler = Handler(Looper.getMainLooper(), Handler.Callback {
        val readBuffer = it.obj as ByteArray
        val tempMessage = String(readBuffer, 0, it.arg1)
//        val message = Message(
//            text = tempMessage
//        )
//        messageRepository.insert(message)
        Toast.makeText(applicationContext, tempMessage, Toast.LENGTH_LONG).show()
        return@Callback true
    })

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
    }

    companion object {
        lateinit var sendReceive: SendReceiveMessageThread
    }
}