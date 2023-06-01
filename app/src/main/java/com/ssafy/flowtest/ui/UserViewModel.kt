package com.ssafy.flowtest.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ssafy.flowtest.data.User
import com.ssafy.flowtest.data.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class UserViewModel :ViewModel(){
    private val userRepository = UserRepository.get()

    private val _userListState = MutableStateFlow<MutableList<User>>(mutableListOf())
    val userListState : StateFlow<MutableList<User>> = _userListState

    init {
        getList()
    }

    private fun getList(){
        viewModelScope.launch {
            userRepository.selectAll().collect{ userList->
                _userListState.update { userList }
            }
        }
    }

    fun insert(user : User){
        viewModelScope.launch {
            userRepository.insertUser(user)
        }

    }
}