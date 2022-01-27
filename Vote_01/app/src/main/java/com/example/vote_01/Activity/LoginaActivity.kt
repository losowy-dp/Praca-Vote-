package com.example.vote_01.Activity

import android.graphics.drawable.Icon
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.vote_01.ui.theme.LightBlue
import com.example.vote_01.ui.theme.LightRed

@Composable
fun LoginActivity(navController: NavController) {
    Box(modifier = Modifier.fillMaxSize()){
        var errorText = remember { mutableStateOf("")}
        Column(modifier = Modifier.align(Alignment.Center)) {
            Text(
                text = "Log in",
                style = MaterialTheme.typography.h2,
                color = LightBlue,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(bottom = 15.dp)
            )
            Text(
                text = errorText.value,
                color = LightRed,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
            )
            val login = remember { mutableStateOf("")}
            val password = remember { mutableStateOf("")}
            var passwordVisibility = remember { mutableStateOf(false) }
            OutlinedTextField(
                value = login.value,
                onValueChange = {
                    login.value = it
                },
                label = { Text("E-mail", color = Color.Black) },
                colors = TextFieldDefaults.outlinedTextFieldColors(focusedBorderColor = Color.Gray, cursorColor = Color.Gray),
                maxLines = 1,
                modifier = Modifier
                    .fillMaxWidth(0.65f)
                    .padding(bottom = 20.dp)
                    .align(Alignment.CenterHorizontally)
            )
            OutlinedTextField(
                value = password.value,
                onValueChange = {
                    password.value = it
                },
                label = { Text("Password", color = Color.Black) },
                colors = TextFieldDefaults.outlinedTextFieldColors(focusedBorderColor = Color.Gray, cursorColor = Color.Gray),
                visualTransformation = if (passwordVisibility.value) VisualTransformation.None else PasswordVisualTransformation(),
                maxLines = 1,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                trailingIcon = {
                    val image = if (passwordVisibility.value)
                        Icons.Outlined.Visibility
                    else Icons.Outlined.VisibilityOff
                    IconButton(onClick = {
                        passwordVisibility.value = !passwordVisibility.value
                    }) {
                        Icon(imageVector  = image, "")
                    }
                },
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .fillMaxWidth(0.65f)
                    .padding(bottom = 20.dp)
            )
            Button(
                onClick = {
                    if(login.value.isNotBlank() && password.value.isNotBlank())
                    {
                        //todo validation the database
                        navController.navigate("MainMenu")
                    } else{
                        errorText.value = "Type email and password"
                    }
                          },
                colors = ButtonDefaults.buttonColors(backgroundColor = LightBlue),
                modifier = Modifier
                    .padding(bottom = 20.dp)
                    .fillMaxWidth(0.5f)
                    .align(Alignment.CenterHorizontally)
            ) {
                Text("log in",color = Color.White,style = MaterialTheme.typography.h5)
            }
            Row(modifier = Modifier
                .fillMaxWidth(0.65f)
                .align(Alignment.CenterHorizontally)
                .padding(bottom = 15.dp)){
                Divider(modifier = Modifier.fillMaxWidth(0.425f).align(Alignment.CenterVertically).height(2.dp))
                Text(
                    text = " or ",
                    color = Color.LightGray,
                    style = MaterialTheme.typography.h5,
                    modifier = Modifier.align(Alignment.Top)
                )
                Divider(modifier = Modifier.fillMaxWidth().align(Alignment.CenterVertically).height(2.dp))
            }
            Button(
                onClick = { navController.navigate("Registration") },
                colors = ButtonDefaults.buttonColors(backgroundColor = LightBlue),
                modifier = Modifier
                    .padding(bottom = 20.dp)
                    .fillMaxWidth(0.5f)
                    .align(Alignment.CenterHorizontally)
            ) {
                Text("sign up",color = Color.White,style = MaterialTheme.typography.h5)
            }
        }
    }
}