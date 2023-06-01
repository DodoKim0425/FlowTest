package com.ssafy.flowtest.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Query("select * from user")
    fun getAllUser() : Flow<MutableList<User>>

    @Insert
    suspend fun insert(user: User)
}