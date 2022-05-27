package com.example.vote_01.Activity

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.vote_01.DataClassesForServer.CreateVote
import com.example.vote_01.Fragment.ToastComp
import com.example.vote_01.ViewModel.CreateVoteViewModel
import com.example.vote_01.ViewModel.GroupViewModel
import com.example.vote_01.ui.theme.*
import java.sql.Time
import java.time.format.TextStyle
import androidx.compose.runtime.remember as remember

@ExperimentalFoundationApi
@Composable
fun CreateVoteActivity(navController: NavController, id_group: String, id_User: String, viewModel: CreateVoteViewModel = hiltViewModel()) {
    var text = rememberSaveable { mutableStateOf("") }
    val selectedOption = remember { mutableStateOf(false) }
    var day = rememberSaveable { mutableStateOf(0) }
    var hour = rememberSaveable { mutableStateOf(0) }
    var minute = rememberSaveable { mutableStateOf(0) }
    val options = remember { mutableStateOf(1) }
    var optionsStr = remember { mutableStateListOf<String>() }
    //context for toast
    val context = LocalContext.current
    Scaffold(
        topBar = {
            Box(
                Modifier
                    .fillMaxWidth()
                    .background(DarkBlue)
            ) {
                    Text(
                        text = "Create vote",
                        style = MaterialTheme.typography.h4,
                        color = WhiteText,
                        modifier = Modifier
                            .padding(10.dp)
                    )
                }
        },
        bottomBar = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp)
                    .background(Blue)
                    .padding(5.dp)
                    .clickable
                    {
                        if (text.value == "") {
                            ToastComp(context, "Name can not be empty")
                        } else {
                            if (day.value == 0 && hour.value == 0 && minute.value == 0) {
                                ToastComp(context, "Time can not be 0")
                            } else {
                                if (optionsStr[0].isBlank()) {
                                    ToastComp(context, "Options can not be empty")
                                } else {
                                    val time =
                                        day.value.toString() + " " + hour.value.toString() + " " + minute.value.toString()
                                    var lista = mutableListOf<String>()
                                    for (j in optionsStr) {
                                        if (j != "")
                                            lista += j
                                    }
                                    viewModel.createVote(
                                        CreateVote(
                                            id_group?.toInt()!!,
                                            text.value,
                                            selectedOption.value,
                                            time,
                                            lista
                                        )
                                    )
                                    navController.popBackStack()
                                    navController.navigate("Open_Group/${id_group.toString()}/${id_User.toString()}")
                                }
                            }
                        }
                    }
            ) {
                Text(
                    text = "Create new vote",
                    style = MaterialTheme.typography.h5,
                    color = WhiteText,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            }
        },
        content = {
            Column{
                OutlinedTextField(
                    value = text.value,
                    onValueChange = {
                        text.value = it
                    },
                    label = { Text("Description vote", color = Black) },
                    colors = TextFieldDefaults.outlinedTextFieldColors(focusedBorderColor = Color.Gray, cursorColor = Color.Gray),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 15.dp)
                )
            Row(modifier = Modifier.fillMaxWidth())
            {

                Box(modifier = Modifier.fillMaxWidth()) {
                    Row(modifier = Modifier
                        .align(Alignment.TopStart)
                        .clickable { selectedOption.value = false }){
                    RadioButton(
                        selected = !selectedOption.value,
                        onClick = { selectedOption.value = false },
                        modifier = Modifier
                            .padding(0.dp,10.dp)
                    )
                Text(text = "One answer", style = MaterialTheme.typography.h6, modifier = Modifier
                    .padding(8.dp)
                    )}
                    Row(modifier = Modifier
                        .align(Alignment.TopEnd)
                        .clickable { selectedOption.value = true }){
                    RadioButton(
                        selected = selectedOption.value, onClick = { selectedOption.value = true },
                        modifier = Modifier
                            .padding(0.dp,10.dp)
                    )
                    Text(text = "Many answers", style = MaterialTheme.typography.h6, modifier = Modifier
                        .padding(8.dp)
                    )
                    }
            }}
                Text(text = "Select finish time", style = MaterialTheme.typography.h5, modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 20.dp, bottom = 10.dp))
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally))
                {
                    Box(modifier = Modifier.fillMaxWidth())
                    {
                    Column(modifier = Modifier
                        .align(Alignment.TopStart)
                        .padding(start = 40.dp)) {
                        Text(text = "+",color = Green, style = MaterialTheme.typography.h4, modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .clickable { if (day.value < 10) day.value++ else day.value = 0 }
                            .clip(RoundedCornerShape(50.dp))
                            .background(WhiteText)
                            .padding(12.dp, 0.dp)
                        )
                        Text(text = "Day's", style = MaterialTheme.typography.h5, modifier = Modifier.align(Alignment.CenterHorizontally))
                        Text(text = day.value.toString(), style = MaterialTheme.typography.h5, modifier = Modifier.align(Alignment.CenterHorizontally))
                        Text(text = "-",color = Red, style = MaterialTheme.typography.h4, modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .clickable { if (day.value > 0) day.value-- else day.value = 10 }
                            .clip(RoundedCornerShape(50.dp))
                            .background(WhiteText)
                            .padding(18.dp, 0.dp)
                        )
                    }
                    Column(modifier = Modifier.align(Alignment.TopCenter)) {
                        Text(text = "+",color = Green, style = MaterialTheme.typography.h4, modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .clickable { if (hour.value < 23) hour.value++ else hour.value = 0 }
                            .clip(RoundedCornerShape(50.dp))
                            .background(WhiteText)
                            .padding(12.dp, 0.dp)
                        )
                        Text(text = "Hour's", style = MaterialTheme.typography.h5, modifier = Modifier.align(Alignment.CenterHorizontally))
                        Text(text = hour.value.toString(), style = MaterialTheme.typography.h5, modifier = Modifier.align(Alignment.CenterHorizontally))
                        Text(text = "-",color = Red, style = MaterialTheme.typography.h4, modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .clickable { if (hour.value > 0) hour.value-- else hour.value = 23 }
                            .clip(RoundedCornerShape(50.dp))
                            .background(WhiteText)
                            .padding(18.dp, 0.dp)
                        )
                    }
                    Column(modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(end = 40.dp)) {
                        Text(text = "+",color = Green, style = MaterialTheme.typography.h4, modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .clickable {
                                if (minute.value < 59) minute.value++ else minute.value = 0
                            }
                            .clip(RoundedCornerShape(50.dp))
                            .background(WhiteText)
                            .padding(12.dp, 0.dp)
                        )
                        Text(text = "Minute's", style = MaterialTheme.typography.h5, modifier = Modifier.align(Alignment.CenterHorizontally))
                        Text(text = minute.value.toString(), style = MaterialTheme.typography.h5, modifier = Modifier.align(Alignment.CenterHorizontally))
                        Text(text = "-",color = Red, style = MaterialTheme.typography.h4, modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .clickable {
                                if (minute.value > 0) minute.value-- else minute.value = 59
                            }
                            .clip(RoundedCornerShape(50.dp))
                            .background(WhiteText)
                            .padding(18.dp, 0.dp)
                        )
                    }
                    }
                }
                LazyVerticalGrid(
                    cells = GridCells.Fixed(1),
                    contentPadding = PaddingValues(start = 7.5.dp, bottom = 100.dp),
                    modifier = Modifier.fillMaxHeight()
                )
                {
                    items(options.value) {
                        val a = it
                        var b = remember { mutableStateOf("")}
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(5.dp)
                        ) {
                            optionsStr += ""
                            OutlinedTextField(
                                value = b.value,
                                onValueChange = {
                                    b.value = it
                                    optionsStr[a] = b.value
                                },
                                label = { Text("Option - " + (a+1).toString(), color = Black) },
                                colors = TextFieldDefaults.outlinedTextFieldColors(focusedBorderColor = Color.Gray, cursorColor = Color.Gray),
                                modifier = Modifier
                                    .fillMaxWidth()
                            )
                        }
                    }
                    items(1){
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(5.dp)
                                .background(Blue)
                                .padding(5.dp)
                                .clickable
                                {
                                    options.value++
                                    //testing string options
                                    //ToastComp(context, messege = optionsStr[options.value-1])
                                }
                        ) {
                            Text(
                                text = "Add option",
                                style = MaterialTheme.typography.h5,
                                color = WhiteText,
                                modifier = Modifier.align(Alignment.CenterHorizontally)
                            )
                        }
                    }
                    }
                }
            }
    )
}