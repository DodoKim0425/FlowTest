package com.ssafy.flowtest.config

import android.app.Application
import com.ssafy.flowtest.data.UserRepository

class ApplicationClass : Application(){

    override fun onCreate() {
        super.onCreate()
        UserRepository.initialize(context = this)
    }
}