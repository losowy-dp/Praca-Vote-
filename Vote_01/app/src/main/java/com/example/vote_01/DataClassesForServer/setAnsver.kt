package com.example.vote_01.DataClassesForServer

data class setAnsver(
    val idVote: String,
    val idUser: String,
    val options: List<String>
)
