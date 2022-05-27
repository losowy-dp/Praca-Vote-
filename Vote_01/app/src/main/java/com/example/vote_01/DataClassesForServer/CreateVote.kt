package com.example.vote_01.DataClassesForServer

data class CreateVote(
    val id_group : Int,
    val vote_name : String,
    val many_ansver: Boolean,
    val time_end : String,
    val option: MutableList<String>
)
