package com.example.vote_01.Fragment

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.unit.dp
import com.example.vote_01.Activity.groupBlock
import com.example.vote_01.Activity.newGroup
import com.example.vote_01.ui.theme.LightBackGray


class ResultVote (
    val description:String,
    val options:List<String>,
    val results:List<Int>
){
    @ExperimentalFoundationApi
    @Composable
    fun ResultToCompose(Admin: Boolean) {
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
            Column() {
                Text(
                    text = description,
                    style = MaterialTheme.typography.h5,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(10.dp)
                )
                Column(modifier = Modifier.fillMaxSize()) {
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
                            Text(
                                text = results[i].toString(),
                                modifier = Modifier.align(Alignment.TopEnd)
                                    .padding(0.dp, 0.dp, 10.dp, 0.dp)
                            )
                        }
                        i++
                    }
                }
            }
        }
    }
}