package com.example.vote_01.Activity

import android.os.Build
import androidx.annotation.RequiresApi
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
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.vote_01.Classes.Group
import com.example.vote_01.Classes.User
import com.example.vote_01.Fragment.ResultVote
import com.example.vote_01.Fragment.VoteFragment
import com.example.vote_01.ui.theme.*


enum class DrawerValue {
    Closed,
    Open
}

@RequiresApi(Build.VERSION_CODES.N)
@ExperimentalFoundationApi
@Composable
fun GroupActivity(Admin: Boolean, EndedVote:List<ResultVote>, Vote:List<VoteFragment>) {
    Box( modifier = Modifier
        .fillMaxSize()
    ) {
        //todo view on last vote
        if(EndedVote.size + Vote.size == 0)
        {
            //todo test this messenge
            Box(modifier = Modifier.fillMaxSize())
            Text(
                text = "There was no vote in this group",
                style = MaterialTheme.typography.h4,
                modifier = Modifier.align(Alignment.Center)
            )
        }
        else{
        LazyVerticalGrid(
            cells = GridCells.Fixed(1),
            modifier = Modifier
                .fillMaxHeight()
                .padding(0.dp, 0.dp, 0.dp, 30.dp)
        )
        {
            items(EndedVote.size)
            {
                EndedVote[it].ResultToCompose(Admin)
            }
            items(Vote.size)
            {
                Vote[it].VoteToCompose(Admin)
            }
        }
        if (Admin == true) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp)
                    .background(Blue)
                    .padding(5.dp)
                    .clickable
                    {
                        //todo create vote
                    }
                    .align(Alignment.BottomEnd)
            ) {
                Text(
                    text = "Create new vote",
                    style = MaterialTheme.typography.h5,
                    color = WhiteText,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            }
        }
    }
    }
}
@ExperimentalFoundationApi
@Composable
fun GroupMenu(Admin: Boolean) {
    Box(modifier = Modifier
        .background(LightBackGray)
        .fillMaxSize()
    )
    {
        Column {
            Column(modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(5.dp))
                .background(Blue)
                .padding(10.dp)
                .clickable
                {
                    //todo new user
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
                .padding(15.dp, 0.dp)
        )
    }
}

@RequiresApi(Build.VERSION_CODES.N)
@ExperimentalFoundationApi
@Composable
fun VoteInGroup(group: Group,Admin: Boolean) {
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
                    //todo database votes
                    GroupActivity(Admin, listOf(
                        ResultVote("asasdadssdadsasdasadsdad", listOf("a","b","c"), listOf(2,4,8)),
                        ResultVote("asdasd", listOf("a","b","c"), listOf(1,43,8))
                        ), listOf(VoteFragment(false,"Aasdlasas", listOf("qwdqw","asd","qweeeeee")),VoteFragment(true,"Aasdlasas", listOf("qwdqw","asd","qweeeeee"))))

                    if (drawerState.value == DrawerValue.Open) {
                        Box(
                            modifier = Modifier
                                .size((parentWidth * 0.25).dp, parentHeight.dp)
                                .offset(x = (parentWidth * 0.12).dp)
                        ) {
                            GroupMenu(Admin)
                        }
                    }
                }
            }
        }
    )
}


