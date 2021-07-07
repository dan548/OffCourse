package com.collegeboys.offcourse.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "messages")
data class Message(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "message_id")
    val messageId: String = UUID.randomUUID().toString(),

    @ColumnInfo(name = "sender_id")
    val senderId: String,

    @ColumnInfo(name = "recipient_id")
    val recipientId: String,

    @ColumnInfo(name = "text")
    val text: String
)