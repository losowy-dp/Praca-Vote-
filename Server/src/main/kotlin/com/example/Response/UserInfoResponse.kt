package com.example.Response

import com.example.DataClasses.UserRegistration

data class UserInfoResponse (
    val successful: Boolean,
    val UserInfo: UserRegistration
)