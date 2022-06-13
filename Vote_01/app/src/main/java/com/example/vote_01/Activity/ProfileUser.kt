package com.example.vote_01.Activity

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.vote_01.ViewModel.ProfileUserViewModel
import com.example.vote_01.ui.theme.DarkBlue
import com.example.vote_01.ui.theme.LightBlue


//@Preview(showSystemUi = true)
@Composable
fun ProfileUser(navController: NavController, viewModel: ProfileUserViewModel = hiltViewModel(),idGroup:String,idUser: String,idMain: String) {
    viewModel.getUser(idUser.toInt())
    Scaffold(
        topBar = {
            Box(
                Modifier
                    .fillMaxWidth()
                    .background(DarkBlue)
            ) {
                Row {
                    Icon(imageVector = Icons.Outlined.ArrowBack,
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier
                            .size(50.dp)
                            .padding(top = 20.dp, start = 15.dp, bottom = 10.dp)
                            .clickable {
                                navController.navigate("Open_Group/${idGroup}/${idMain}")
                            }
                    )
                }
            }
        },
        bottomBar = {},
        content = { mainContent(viewModel)}
    )
}

@Composable
fun mainContent(viewModel: ProfileUserViewModel) {
    Box(modifier = Modifier.fillMaxSize()) {
        Column() {
            Row(modifier = Modifier
                .background(LightBlue)
                .fillMaxWidth()
            ){
                Column(modifier = Modifier.fillMaxWidth()) {
                    Icon(
                        imageVector = Icons.Outlined.AccountCircle,
                        contentDescription = null,
                        tint = Color.Black,
                        modifier = Modifier
                            .size(200.dp)
                            .align(Alignment.CenterHorizontally)
                            .padding(top = 10.dp, bottom = 10.dp))
                }
            }
                Text(
                    text = viewModel.firstName.value + " " + viewModel.lastName.value,
                    style = MaterialTheme.typography.h4,
                    modifier = Modifier
                        .align(CenterHorizontally)
                        .padding(bottom = 5.dp)
                )
                Text(
                    text = viewModel.company.value,
                    fontSize = 15.sp,
                    modifier = Modifier
                        .align(CenterHorizontally)
                        .padding(bottom = 20.dp)
                )
                Text(
                    text = "E-mail: " + viewModel.email.value,
                    style = MaterialTheme.typography.h5,
                    modifier = Modifier
                        .align(CenterHorizontally)
                        .padding(bottom = 10.dp)
                )
                if(viewModel.phone.value != ""){
                Text(
                    text = "Phone: " + viewModel.phone.value,
                    style = MaterialTheme.typography.h5,
                    modifier = Modifier
                        .align(CenterHorizontally)
                )
                }
                if(viewModel.aboutMe.value != ""){
                Text(
                    text = viewModel.aboutMe.toString(),
                    style = MaterialTheme.typography.h6,
                    fontStyle = FontStyle.Italic,
                    modifier = Modifier
                        .align(CenterHorizontally)
                        .padding(top = 30.dp)
                )
                }
        }
    }
}