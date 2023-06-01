package com.ssafy.flowtest.data

import android.content.Context
import androidx.room.Room
import kotlinx.coroutines.flow.Flow

private const val DATABASE_NAME = "user_database"
class UserRepository private constructor(context: Context){

    private val database : UserDatabase = Room.databaseBuilder(
        context.applicationContext,
        UserDatabase::class.java,
        DATABASE_NAME
    ).build()

    private val userDao = database.userDao()

    suspend fun insertUser(user : User){
        userDao.insert(user)
    }

    fun selectAll(): Flow<MutableList<User>> {
        return userDao.getAllUser()
    }

    companion object{
        private var INSTANCE : UserRepository? =null

        fun initialize(context : Context){
            if(INSTANCE==null){
                INSTANCE= UserRepository(context)
            }
        }

        fun get(): UserRepository {
            return INSTANCE ?: throw java.lang.IllegalStateException("Repository must be initialized")
        }
    }
}