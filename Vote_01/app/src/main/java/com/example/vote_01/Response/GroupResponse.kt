package com.example.vote_01.Response

import com.example.DataClasses.GroupDataClass

data class GroupResponse(
    val successful: Boolean,
    val message: GroupDataClass
)
