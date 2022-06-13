package com.example.Response

import com.example.DataClasses.userForGroups

data class LinkMapResponse(
    val successful: Boolean,
    val message: MutableList<userForGroups>?
)
