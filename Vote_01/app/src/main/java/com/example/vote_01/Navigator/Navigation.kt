package com.example.vote_01.Navigator

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.vote_01.Activity.*
import com.example.vote_01.Classes.Group
import com.example.vote_01.Classes.User

@RequiresApi(Build.VERSION_CODES.N)
@ExperimentalFoundationApi
@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "LoginActivity"){
        composable("LoginActivity")
        {
            LoginActivity(navController)
        }
        composable("MainMenu"){
            HomeScreen(navController)
        }
        composable("Open_Group"){
            VoteInGroup(navController,Group(1,"Rok 5 Grupa 3",null ,null , User(1,true,"Alosha")),true)
        }
        composable("Create_New_Vote")
        {
            CreateVoteActivity(navController,id_group = 25)
        }
        composable("Registration")
        {
            Registration(navController)
        }
    }
}