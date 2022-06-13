package com.example.DataClasses

import com.example.Tabels.UserMain
import com.example.Tabels.UserMain.autoIncrement
import com.example.Tabels.UserMain.uniqueIndex

data class UserData(
    val email: String,
    val first_name: String,
    val last_name: String,
    val company: String,
    val phone: String,
    val password: String
)