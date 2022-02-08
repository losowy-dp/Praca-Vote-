package com.example.vote_01.Response

data class MapResponse(
    val successful: Boolean,
    val message: MutableMap<Int,String>
)
