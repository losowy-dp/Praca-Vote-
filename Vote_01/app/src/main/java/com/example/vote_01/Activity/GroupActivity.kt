package com.example.vote_01.Activity

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.vote_01.Classes.Group
import com.example.vote_01.Classes.User
import com.example.vote_01.ui.theme.*


enum class DrawerValue {
    Closed,
    Open
}

@Composable
fun GroupActivity() {
    Text(text = "AAAAAAAAAAAAAA")
    Text(text = "AAAAAAAAAAAAAA")
    Text(text = "AAAAAAAAAAAAAA")
    Text(text = "AAAAAAAAAAAAAA")
    Text(text = "AAAAAAAAAAAAAA")
    Text(text = "AAAAAAAAAAAAAA")
}
@ExperimentalFoundationApi
@Composable
fun GroupMenu() {
    Box(modifier = Modifier
        .fillMaxSize()
        .background(LightBackGray)
    )
    {
        Column {
            Column(modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(5.dp))
                .background(Blue)
                .padding(10.dp)
                .clickable
                {//todo new user
                }
            ) {
                Text(
                    text = "Invate User",
                    style = MaterialTheme.typography.h4,
                    color = WhiteText,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            }
            Column(modifier = Modifier
                .fillMaxWidth()
            )
            {
                Text(
                    text = "Users in this group",
                    style = MaterialTheme.typography.h6,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
                listUsers(users = listOf(
                    User(1,"First Name"),
                    User(1,"Second Name"),
                    User(1,"32 Name"),
                    User(1,"8 Name"),
                    User(1,"First Name"),
                    User(1,"Second Name"),
                    User(1,"32 Name"),
                    User(1,"8 Name"),
                    User(1,"First Name"),
                    User(1,"Second Name"),
                    User(1,"32 Name"),
                    User(1,"8 Name"),
                    User(1,"First Name"),
                    User(1,"Second Name"),
                    User(1,"32 Name"),
                    User(1,"8 Name")
                ))
            }
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(DarkRed)
                .padding(10.dp)
                .clickable
                {
                    //todo leave whis group
                }
                .align(Alignment.BottomEnd)
        ) {
            Text(
                text = "LEAVE THIS GROUP",
                style = MaterialTheme.typography.h5,
                color = WhiteText,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        }

    }
}

@ExperimentalFoundationApi
@Composable
fun listUsers(users: List<User>) {
    Column(modifier = Modifier.fillMaxWidth()) {
        LazyVerticalGrid(
            cells = GridCells.Fixed(1),
            contentPadding = PaddingValues(start = 7.5.dp, bottom = 100.dp),
            modifier = Modifier.fillMaxHeight()
        )
        {
            items(users.size)
            {
                userBlock(user = users[it])
            }
        }
    }
}

@Composable
fun userBlock(user: User) {
    BoxWithConstraints(modifier = Modifier
        .padding(2.dp)
        .clip(RoundedCornerShape(5.dp))
        .background(LightGray)
        .padding(5.dp)
        .clickable {
            //todo option users
        }
    ){
        Text (
            text = user.name,
            style = MaterialTheme.typography.h5,
            lineHeight = 26.sp,
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(15.dp,0.dp)
        )
    }
}

@ExperimentalFoundationApi
@Composable
fun VoteInGroup(group: Group) {
    val drawerState = remember { mutableStateOf(DrawerValue.Closed) }
    Scaffold(
        topBar = {
            Box(
                Modifier
                    .fillMaxWidth()
                    .background(DarkBlue)
            ) {
                Row {
                    Text(
                        text = "<-",
                        style = MaterialTheme.typography.h4,
                        color = WhiteText,
                        modifier = Modifier
                            .padding(10.dp)
                            .clickable {
                                //todo back to menu
                            }
                    )
                    Text(
                        text = group.Name,
                        style = MaterialTheme.typography.h4,
                        color = WhiteText,
                        modifier = Modifier
                            .padding(10.dp)
                    )
                }
                Text(
                    text = "*",
                    style = MaterialTheme.typography.h4,
                    color = WhiteText,
                    modifier = Modifier
                        .padding(15.dp)
                        .align(Alignment.TopEnd)
                        .clickable {
                            if (drawerState.value == DrawerValue.Closed) {
                                drawerState.value = DrawerValue.Open
                            } else {
                                drawerState.value = DrawerValue.Closed
                            }
                        }
                )
            }
        },
        bottomBar = {},
        content = {
            BoxWithConstraints {
                val parentWidth = constraints.maxWidth
                val parentHeight = constraints.maxHeight
                Box {
                    //todo Animation
                    GroupActivity()

                    if (drawerState.value == DrawerValue.Open) {
                        Box(
                            modifier = Modifier
                            .size((parentWidth * 0.25).dp , parentHeight.dp)
                            .offset(x = (parentWidth * 0.12).dp)
                        ) {
                            GroupMenu()
                        }
                    }
                }
            }
        }
    )
}


