package com.example.vote_01.Response

import com.example.vote_01.Classes.UserRegistration

data class UserInfoResponse (
    val successful: Boolean,
    val UserInfo: UserRegistration
        )