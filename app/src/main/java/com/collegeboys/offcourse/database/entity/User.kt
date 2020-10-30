package com.collegeboys.offcourse.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "users")
data class User(
        @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    val id: String = UUID.randomUUID().toString(),

        @ColumnInfo(name = "name")
    val name: String,

        @ColumnInfo(name = "password")
    val password: String
)
