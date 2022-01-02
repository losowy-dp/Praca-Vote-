package com.example.vote_01.Fragment

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.vote_01.ui.theme.Blue
import com.example.vote_01.ui.theme.LightBackGray
import com.example.vote_01.ui.theme.Red
import com.example.vote_01.ui.theme.WhiteText


class VoteFragment (
    val many_Option:Boolean,
    val description:String,
    val options:List<String>,
    //val time:Time todo Time
        ){
    @RequiresApi(Build.VERSION_CODES.N)
    @ExperimentalFoundationApi
    @Composable
    fun VoteToCompose(Admin: Boolean) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(5.dp)
                .background(LightBackGray)
                .clickable {
                    if (Admin == true) {
                    }
                    //todo can delete
                }
        ){
            Column(modifier = Modifier.align(Alignment.TopCenter)) {
                Text(
                    text = description,
                    style = MaterialTheme.typography.h5,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(10.dp)
                )
                Text(
                    //todo Time
                    text = "Time to end - ???",
                    color = Red,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(10.dp)
                )
                if(many_Option == false)
                {
                    radioButtonVote()
                }
                else{
                    checkBoxVote()
                }
                }
            }
        }
@Composable
fun radioButtonVote() {
    val isReady = remember{ mutableStateOf(false)}
    if(!isReady.value){
    Column(modifier = Modifier.fillMaxSize()) {
        val selectedOption = remember{ mutableStateOf("")}
        var i = 0
        options.forEach()
        {
            Box(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = it,
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .padding(10.dp, 0.dp, 0.dp, 10.dp)
                )
                RadioButton( selected = (selectedOption.value == it),
                    onClick = {selectedOption.value = it},
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(0.dp, 0.dp, 10.dp, 0.dp)
                )
            }
            i++
        }
        // Context to toast composable
        val context = LocalContext.current
        Text(
            text = "Acept",
            style = MaterialTheme.typography.h5,
            color = WhiteText,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(20.dp, 0.dp, 20.dp, 15.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(Blue)
                .padding(20.dp, 0.dp, 20.dp, 0.dp)
                .clickable {
                    if (selectedOption.value == "") {
                        ToastComp(context, messege = "Select Option")
                    } else {
                        //todo acept voiting
                        isReady.value = true;
                    }
                }
        )
    }
    }
}

@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun checkBoxVote() {
    var isReady = remember{ mutableStateOf(false)}
    if(!isReady.value){
    // Context to toast composable
    val context = LocalContext.current
    Column(modifier = Modifier.fillMaxSize()) {
        var i = 0
        val selectedOption = remember{ mutableMapOf<String,Int>() }
        options.forEach()
        {
            val visualOption = remember{ mutableStateOf(false)}
            val str = remember{ mutableStateOf(it)}
            Box(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = it,
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .padding(10.dp, 0.dp, 0.dp, 10.dp)
                )
                Checkbox( checked = visualOption.value,
                    onCheckedChange = {visualOption.value = it;if(visualOption.value == true){selectedOption[str.toString()] = 1}else{selectedOption[str.toString()] =0} },
                    enabled = true,
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(0.dp, 0.dp, 10.dp, 0.dp)
                )
            }
            i++
        }

        Text(
            text = "Acept",
            style = MaterialTheme.typography.h5,
            color = WhiteText,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(20.dp, 0.dp, 20.dp, 15.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(Blue)
                .padding(20.dp, 0.dp, 20.dp, 0.dp)
                .clickable {
                    if (selectedOption.isEmpty()) {
                        ToastComp(context, messege = "Select options")
                    } else {
                        if(1 in selectedOption.values)
                        {
                            //todo acept
                            isReady.value = true
                        }
                        else
                        {
                            ToastComp(context, messege = "Select options")
                        }
                    }
                }
        )

    }
}
}
}