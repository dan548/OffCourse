package com.collegeboys.offcourse.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import java.time.LocalDateTime
import java.util.*

@Entity(tableName = "sessions")
data class UserSession(
        @PrimaryKey(autoGenerate = false)
        @ColumnInfo(name = "session_id")
        val sessionId: String = UUID.randomUUID().toString(),

        @ColumnInfo(name = "user_id")
        val userId: String
)
