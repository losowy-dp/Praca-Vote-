package com.example.vote_01.Response

import com.example.DataClasses.OptionDataClass

data class MapListOptionResponse (
    val successful: Boolean,
    val message: MutableMap<String, MutableList<OptionDataClass>>
)