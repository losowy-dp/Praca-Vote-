package com.example.vote_01.Activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import com.example.vote_01.Classes.Group
import com.example.vote_01.Classes.User
import com.example.vote_01.ui.theme.*

class MainActivity : ComponentActivity() {
    @ExperimentalFoundationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Vote_01Theme {
                //HomeScreen()
                VoteInGroup(Group(1,"asd",null ,null , User(1,"Alosha")))
            }
        }
    }
}
