package com.collegeboys.offcourse.database.entity

import androidx.room.Embedded
import androidx.room.Relation

data class UserWithSession(
        @Embedded
        val user: User,

        @Relation(
                parentColumn = "user_id ",
                entityColumn = "user_id",
                entity = UserSession::class
        )
        val session: UserSession
)
