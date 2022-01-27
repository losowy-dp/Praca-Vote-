package com.example.vote_01.Classes

data class Group(
    var Id: Int,
    var Name: String,
    var Users: List<User>? = null,
    var Administrators: List<User>? = null,
    var Creator: User
)