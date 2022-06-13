package com.example.vote_01.Navigator

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.vote_01.Activity.*

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
        composable("MainMenu/{userId}", arguments = listOf(navArgument("userId"){
            type = NavType.StringType
        })){
            HomeScreen(navController,it.arguments?.getString("userId").toString())
        }
        composable("Open_Group/{GroupId}/{UserId}"){
            VoteInGroup(navController = navController, GroupId = it.arguments?.getString("GroupId").toString(), UserId = it.arguments?.getString("UserId").toString())
        }
        composable("Create_New_Vote/{GroupId}/{UserId}")
        {
            CreateVoteActivity(navController,it.arguments?.getString("GroupId").toString(),it.arguments?.getString("UserId").toString())
        }
        composable("Registration")
        {
            Registration(navController)
        }
        composable("Open_User_Profile/{GroupId}/{UserId}/{idMain}"){
            ProfileUser(
                navController = navController,
                idGroup = it.arguments?.getString("GroupId").toString(),
                idUser = it.arguments?.getString("UserId").toString(),
                idMain = it.arguments?.getString("idMain").toString(),
            )
        }
    }
}