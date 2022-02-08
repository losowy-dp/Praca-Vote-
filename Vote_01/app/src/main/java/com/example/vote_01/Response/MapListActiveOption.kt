package com.example.vote_01.Response

import com.example.DataClasses.OptionDataClass

data class MapListActiveOption(
    val successful: Boolean,
    val message: MutableMap<String, MutableList<OptionDataClass>>,
    val ansver: MutableList<Boolean>,
    val endTime: MutableList<String>,
    val idVote: MutableList<Int>
)