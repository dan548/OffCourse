package com.collegeboys.offcourse.database.dao

import com.collegeboys.offcourse.database.entity.Contact

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ContactDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsertContact(contact: Contact)

    @Query("SELECT * FROM contacts WHERE user_id = :id")
    fun getById(id: String): Contact

    @Query("SELECT * FROM contacts")
    fun getAll(): List<Contact>

    @Query("DELETE FROM contacts WHERE user_id = :id")
    fun deleteById(id: String)

    @Query("DELETE FROM contacts")
    fun deleteAll()
}