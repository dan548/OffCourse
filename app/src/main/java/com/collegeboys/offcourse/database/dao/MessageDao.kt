package com.collegeboys.offcourse.database.dao

import com.collegeboys.offcourse.database.entity.Message

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface MessageDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsertMessage(message: Message)

    @Query("SELECT * FROM messages WHERE message_id = :id")
    fun getById(id: String): Message

    @Query("SELECT * FROM messages WHERE sender_id = :sender AND recipient_id = :recipient")
    fun getBySenderAndRecipient(sender: String, recipient: String): List<Message>

    @Query("DELETE FROM messages WHERE message_id = :id")
    fun deleteById(id: String)

    @Query("DELETE FROM messages WHERE sender_id = :sender AND recipient_id = :recipient")
    fun deleteChat(sender: String, recipient: String)

    @Query("DELETE FROM messages")
    fun deleteAll()
}