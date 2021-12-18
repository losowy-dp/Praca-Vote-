package com.example.vote_01.Classes

class Group(
    var Id: Int,
    var Name: String,
    var Users: List<User>? = null,          //todo delete this null
    var Administrators: List<User>? = null,
    var Creator: User
)