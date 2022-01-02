package com.example.vote_01.Classes

import java.sql.Time

class VoteToCreate(
    val description: String,
    val time: Time,
    val options: MutableList<String>,
    val box: Boolean
) {
}