package com.example.Response

import com.example.DataClasses.GroupDataClass

data class GroupResponse(
    val successful: Boolean,
    val message: GroupDataClass
)
