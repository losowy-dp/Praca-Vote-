package com.example.Response

import com.example.DataClasses.OptionDataClass

data class MapListOptionResponse(
    val successful: Boolean,
    val message: MutableMap<String, MutableList<OptionDataClass>>
)
