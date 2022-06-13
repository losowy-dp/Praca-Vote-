package com.example.Response

data class MapResponse(
    val successful: Boolean,
    val message: MutableMap<Int,String>
)
