package com.collegeboys.offcourse.repository

import com.collegeboys.offcourse.database.dao.MessageDao
import com.collegeboys.offcourse.database.entity.Message

class MessageRepository(private val messageDao: MessageDao) {
    fun insert(message: Message) = messageDao.upsertMessage(message)

    fun getById(id: String): Message = messageDao.getById(id)

    fun getChatMessages(sender: String, recipient: String): List<Message> =
        messageDao.getBySenderAndRecipient(sender, recipient)

    fun deleteById(id: String) = messageDao.deleteById(id)

    fun deleteChatMessages(sender: String, recipient: String) =
        messageDao.deleteChat(sender, recipient)

    fun deleteAll() = messageDao.deleteAll()
}