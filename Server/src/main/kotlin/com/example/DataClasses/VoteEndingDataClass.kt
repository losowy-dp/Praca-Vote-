package com.example.DataClasses

import com.example.Tabels.GroupUser
import com.example.Tabels.VoteEnding
import com.example.Tabels.VoteEnding.uniqueIndex

data class VoteEndingDataClass (
    val idVoteEnding : Int,
    val id_group : Int,
    val vote_name : String
)