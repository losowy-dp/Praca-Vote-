package com.example.vote_01.Response

import com.example.vote_01.Classes.User

data class LinkMapResponse (
    val successful: Boolean,
    val message: MutableList<User>
)