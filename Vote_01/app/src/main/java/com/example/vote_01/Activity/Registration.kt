package com.example.vote_01.Activity

import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material.icons.outlined.VisibilityOff
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.vote_01.ui.theme.LightBlue

@Composable
fun Registration(navController: NavController) {
    Box(modifier = Modifier.fillMaxSize())
    {
        Icon(imageVector = Icons.Outlined.ArrowBack, contentDescription = null,
            modifier = Modifier
                .align(Alignment.TopStart)
                .size(50.dp)
                .padding(top = 20.dp, start = 15.dp)
                .clickable {
                    navController.navigate("LoginActivity")
                }
        )
        val firstName = remember { mutableStateOf("")}
        val lastName = remember { mutableStateOf("")}
        val email = remember { mutableStateOf("")}
        val company = remember { mutableStateOf("")}
        val passwordOne = remember { mutableStateOf("")}
        val passwordTwo = remember { mutableStateOf("")}
        val numberPhone = remember { mutableStateOf("")}
        val aboutMe = remember { mutableStateOf("")}
        val passwordOneVisibility = remember { mutableStateOf(false)}
        val passwordTwoVisibility = remember { mutableStateOf(false)}
        val scroll = rememberScrollState()
        Column(modifier = Modifier
            .verticalScroll(scroll)
            .fillMaxHeight()
            .fillMaxWidth(0.65f)
            .align(Alignment.Center))
        {
            Text(
                text = "Sign up",
                style = MaterialTheme.typography.h2,
                color = LightBlue,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(bottom = 15.dp)
            )
            OutlinedTextField(
                value = firstName.value,
                onValueChange = {
                    firstName.value = it
                },
                label = { Text("First Name", color = Color.Black) },
                colors = TextFieldDefaults.outlinedTextFieldColors(focusedBorderColor = Color.Gray, cursorColor = Color.Gray),
                maxLines = 1,
                modifier = Modifier
                    .padding(bottom = 5.dp)
                    .align(Alignment.CenterHorizontally)
            )
            OutlinedTextField(
                value = lastName.value,
                onValueChange = {
                    lastName.value = it
                },
                label = { Text("Last Name", color = Color.Black) },
                colors = TextFieldDefaults.outlinedTextFieldColors(focusedBorderColor = Color.Gray, cursorColor = Color.Gray),
                maxLines = 1,
                modifier = Modifier
                    .padding(bottom = 5.dp)
                    .align(Alignment.CenterHorizontally)
            )
            OutlinedTextField(
                value = email.value,
                onValueChange = {
                    email.value = it
                },
                label = { Text("E-mail", color = Color.Black) },
                colors = TextFieldDefaults.outlinedTextFieldColors(focusedBorderColor = Color.Gray, cursorColor = Color.Gray),
                maxLines = 1,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                modifier = Modifier
                    .padding(bottom = 5.dp)
                    .align(Alignment.CenterHorizontally)
            )
            OutlinedTextField(
                value = company.value,
                onValueChange = {
                    company.value = it
                },
                label = { Text("Company name (not necessary)", color = Color.Black) },
                colors = TextFieldDefaults.outlinedTextFieldColors(focusedBorderColor = Color.Gray, cursorColor = Color.Gray),
                maxLines = 1,
                modifier = Modifier
                    .padding(bottom = 5.dp)
                    .align(Alignment.CenterHorizontally)
            )
            OutlinedTextField(
                value = numberPhone.value,
                onValueChange = {
                    numberPhone.value = it
                },
                label = { Text("Number Phone (not necessary)", color = Color.Black) },
                colors = TextFieldDefaults.outlinedTextFieldColors(focusedBorderColor = Color.Gray, cursorColor = Color.Gray),
                maxLines = 1,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                modifier = Modifier
                    .padding(bottom = 5.dp)
                    .align(Alignment.CenterHorizontally)
            )
            //PASSWORD ONE
            OutlinedTextField(
                value = passwordOne.value,
                onValueChange = {
                    passwordOne.value = it
                },
                label = { Text("Password", color = Color.Black) },
                colors = TextFieldDefaults.outlinedTextFieldColors(focusedBorderColor = Color.Gray, cursorColor = Color.Gray),
                visualTransformation = if (passwordOneVisibility.value) VisualTransformation.None else PasswordVisualTransformation(),
                maxLines = 1,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                trailingIcon = {
                    val image = if (passwordOneVisibility.value)
                        Icons.Outlined.Visibility
                    else Icons.Outlined.VisibilityOff
                    IconButton(onClick = {
                        passwordOneVisibility.value = !passwordOneVisibility.value
                    }) {
                        Icon(imageVector  = image, "")
                    }
                },
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(bottom = 5.dp)
            )
            //PASSWORD TWO
            OutlinedTextField(
                value = passwordTwo.value,
                onValueChange = {
                    passwordTwo.value = it
                },
                label = { Text("Password", color = Color.Black) },
                colors = TextFieldDefaults.outlinedTextFieldColors(focusedBorderColor = Color.Gray, cursorColor = Color.Gray),
                visualTransformation = if (passwordTwoVisibility.value) VisualTransformation.None else PasswordVisualTransformation(),
                maxLines = 1,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                trailingIcon = {
                    val image = if (passwordTwoVisibility.value)
                        Icons.Outlined.Visibility
                    else Icons.Outlined.VisibilityOff
                    IconButton(onClick = {
                        passwordTwoVisibility.value = !passwordTwoVisibility.value
                    }) {
                        Icon(imageVector  = image, "")
                    }
                },
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(bottom = 5.dp)
            )
            OutlinedTextField(
                value = aboutMe.value,
                onValueChange = {
                    aboutMe.value = it
                },
                label = { Text("About me", color = Color.Black) },
                colors = TextFieldDefaults.outlinedTextFieldColors(focusedBorderColor = Color.Gray, cursorColor = Color.Gray),
                modifier = Modifier
                    .height(120.dp)
                    .padding(bottom = 5.dp)
                    .align(Alignment.CenterHorizontally)
            )
            Button(
                onClick = {

                },
                colors = ButtonDefaults.buttonColors(backgroundColor = LightBlue),
                modifier = Modifier
                    .padding(bottom = 20.dp)
                    .fillMaxWidth(0.8f)
                    .align(Alignment.CenterHorizontally)
            ) {
                Text("Accept",color = Color.White,style = MaterialTheme.typography.h5)
            }

        }
    }
}