package com.collegeboys.offcourse.database.converters

import androidx.room.TypeConverter
import java.time.LocalDateTime

class LocalDateTimeConverter {
    @TypeConverter
    fun toLocalDateTime(dateString: String?): LocalDateTime? {
        return dateString?.let {
            LocalDateTime.parse(dateString)
        }
    }

    @TypeConverter
    fun toDateString(dateTime: LocalDateTime?): String? {
        return dateTime?.let {
            dateTime.toString()
        }
    }
}