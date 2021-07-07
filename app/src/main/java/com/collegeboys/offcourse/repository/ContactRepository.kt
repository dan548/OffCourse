package com.collegeboys.offcourse.repository

import com.collegeboys.offcourse.database.dao.ContactDao
import com.collegeboys.offcourse.database.entity.Contact

class ContactRepository(private val contactDao: ContactDao) {
    fun upsert(contact: Contact) = contactDao.upsertContact(contact)

    fun getById(id: String): Contact = contactDao.getById(id)

    fun getAll(): List<Contact> = contactDao.getAll()

    fun deleteById(id: String) = contactDao.deleteById(id)

    fun deleteAll() = contactDao.deleteAll()
}