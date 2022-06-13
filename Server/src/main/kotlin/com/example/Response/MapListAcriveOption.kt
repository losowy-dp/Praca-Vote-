package com.example.Response

import com.example.DataClasses.OptionDataClass

data class MapListAcriveOption(
    val successful: Boolean,
    val message: MutableMap<String, MutableList<OptionDataClass>>,
    val ansver: MutableList<Boolean>,
    val endTime: MutableList<String>,
    val idVote: MutableList<Int>
)
