package com.example.vote_01.Activity

import android.annotation.SuppressLint
import android.os.Build
import android.util.Patterns
import androidx.annotation.RequiresApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.vote_01.Classes.Group
import com.example.vote_01.Classes.User
import com.example.vote_01.DataClassesForServer.CreateGroup
import com.example.vote_01.DataClassesForServer.inviteUser
import com.example.vote_01.DataClassesForServer.setModerator
import com.example.vote_01.Fragment.ResultVote
import com.example.vote_01.Fragment.ToastComp
import com.example.vote_01.Fragment.VoteFragment
import com.example.vote_01.ViewModel.GroupViewModel
import com.example.vote_01.ui.theme.*


enum class DrawerValue {
    Closed,
    Open
}

@RequiresApi(Build.VERSION_CODES.N)
@ExperimentalFoundationApi
@Composable
fun GroupActivity(navController: NavController,Admin: Boolean, EndedVote:List<ResultVote>, Vote:List<VoteFragment>,idUser: String,idGroup: String,viewModel: GroupViewModel)
{
    Box( modifier = Modifier
        .fillMaxSize()
    ) {
        //todo view on last vote
        if(EndedVote.size + Vote.size == 0)
        {
            Box(modifier = Modifier.fillMaxSize())
            Text(
                text = "There was no vote in this group",
                style = MaterialTheme.typography.h5,
                color = Color.Gray,
                modifier = Modifier.align(Alignment.Center)
            )
        }
        else{
            val idVote = remember {viewModel.idActive}
            val ansver = remember {viewModel.AnsverVote}
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
                if(!ansver.isEmpty()) {
                    if (idVote[it] in ansver)
                        Vote[it].VoteToCompose(navController,idGroup,Admin, true, idVote[it],idUser,viewModel)
                    else
                        Vote[it].VoteToCompose(navController,idGroup,Admin, false, idVote[it],idUser,viewModel)
                }
                else
                {
                    Vote[it].VoteToCompose(navController,idGroup,Admin, false, idVote[it],idUser,viewModel)
                }
            }
        }}
        if (Admin == true) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp)
                    .background(Blue)
                    .padding(5.dp)
                    .clickable
                    {
                        navController.navigate("Create_New_Vote/${idGroup}/${idUser}")
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
@ExperimentalFoundationApi
@Composable
fun GroupMenu(navController: NavController,Admin: Boolean,creator: Int,idGroup: String,idUser: String,viewModel: GroupViewModel) {
    Box(modifier = Modifier
        .background(LightBackGray)
        .fillMaxSize()
    )
    {
        Column {
            if(Admin == true){
            Column(modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(5.dp))
                .background(Blue)
                .padding(10.dp)
                .clickable
                {
                    viewModel.openDialogInvite.value = true
                }
            ) {
                Text(
                    text = "Invate User",
                    style = MaterialTheme.typography.h4,
                    color = WhiteText,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            }
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
                listUsers(navController,Admin, creator, idGroup, viewModel,idUser)
            }
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(DarkRed)
                .padding(10.dp)
                .clickable
                {
                    viewModel.kickUser(setModerator(idGroup.toInt(), idUser.toInt()))
                    navController.popBackStack()
                    navController.navigate("MainMenu/${idUser}")
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
fun listUsers(navController: NavController, Admin: Boolean,creator: Int,idGroup: String,viewModel: GroupViewModel,mainId: String) {
    val users =  remember { viewModel.users }
    Column(modifier = Modifier.fillMaxWidth()) {
        LazyVerticalGrid(
            cells = GridCells.Fixed(1),
            contentPadding = PaddingValues(start = 7.5.dp, bottom = 100.dp),
            modifier = Modifier.fillMaxHeight()
        )
        {
            items(users.size)
            {
                userBlock(navController,user = users[it],Admin,creator, idGroup, viewModel, mainId)
            }
        }
    }
}

@Composable
fun userBlock(navController: NavController,user: User,Admin: Boolean,creator: Int,idGroup: String,viewModel: GroupViewModel,mainId:String) {
    //open Dialog for options user
    val openDialog = remember { mutableStateOf(false)  }
    val openDialogInvite = remember { viewModel.openDialogInvite  }
    val resultInvite = remember { viewModel.resultInviteBool  }
    BoxWithConstraints(modifier = Modifier
        .padding(2.dp)
        .clip(RoundedCornerShape(5.dp))
        .background(LightGray)
        .padding(5.dp)
        .clickable {
            openDialog.value = true
        }
    ) {
        if (user.administrator) {
            Text(
                text = user.name,
                style = MaterialTheme.typography.h5,
                lineHeight = 26.sp,
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(15.dp, 0.dp)
            )
            Text(
                text = "Moderator",
                color = LightRed,
                fontSize = 10.sp,
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(15.dp, 0.dp)
            )
        } else {
            Text(
                text = user.name,
                style = MaterialTheme.typography.h5,
                lineHeight = 26.sp,
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(15.dp, 0.dp)
            )
        }
    }
    if(openDialog.value) {
        AlertDialog(
            onDismissRequest = { openDialog.value = false },
            title = { Text(text = "User options", modifier = Modifier.padding(bottom = 10.dp)) },
                confirmButton = {
                    if(!user.administrator && Admin && user.id != creator) {
                        Button(
                            onClick = {
                                viewModel.setModerator(setModerator(idGroup.toInt(),user.id))
                                viewModel.refreshUser(idGroup.toInt())
                                openDialog.value = false
                                viewModel.drawerState.value = DrawerValue.Closed
                            }, colors = ButtonDefaults.buttonColors(backgroundColor = LightYellow)
                        ) {
                            Text("Give moderator mode this user")
                        }
                        Button(
                            onClick = {
                                viewModel.kickUser(setModerator(idGroup.toInt(),user.id))
                                openDialog.value = false
                                viewModel.refreshUser(idGroup.toInt())
                                viewModel.drawerState.value = DrawerValue.Closed
                            },
                            colors = ButtonDefaults.buttonColors(backgroundColor = LightRed)
                        ) {
                            Text("Kick user")
                        }
                    }
                    Button(
                        onClick = {
                            navController.navigate("Open_User_Profile/${idGroup}/${user.id}/${mainId}")
                            openDialog.value = false
                        }, colors = ButtonDefaults.buttonColors(backgroundColor = LightBlue)) {
                        Text("Open profile")
                    }
                    Button(
                        onClick = {
                            openDialog.value = false
                        }, colors = ButtonDefaults.buttonColors(backgroundColor = Color.Gray)) {
                        Text("Cancel")
                    }
                }
        )
    }
    if(openDialogInvite.value){
        val context = LocalContext.current
        var email = rememberSaveable { mutableStateOf("") }
        AlertDialog(
            onDismissRequest = {
                openDialogInvite.value = false
            },
            title = {
                Text(text = "Invite User", modifier = Modifier.padding(bottom = 10.dp))
            },
            text = {
                OutlinedTextField(
                    value = email.value,
                    onValueChange = {
                        email.value = it
                    },
                    label = { Text("Email new User", color = Color.Black) },
                    colors = TextFieldDefaults.outlinedTextFieldColors(focusedBorderColor = Color.Gray),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 20.dp)
                )
            },
            confirmButton = {
                Button(
                    onClick = {
                        if(email.value != "" && Patterns.EMAIL_ADDRESS.matcher(email.value).matches()){
                            viewModel.inviteUser(inviteUser(idGroup.toInt(),email.value))
                            openDialogInvite.value = false
                            resultInvite.value = true
                        }
                        else{
                            ToastComp(context,"Type correct Email User")
                        }
                    }, colors = ButtonDefaults.buttonColors(backgroundColor = LightBlue)) {
                    Text("Invite")
                }
            },
            dismissButton = {
                Button(
                    onClick = {
                        openDialogInvite.value = false
                    }, colors = ButtonDefaults.buttonColors(backgroundColor = Color.LightGray)) {
                    Text("Cancel")
                }
            }
        )
    }
    if(resultInvite.value){
        val context = LocalContext.current
        var email = rememberSaveable { mutableStateOf("") }
        val result = remember { viewModel.resultInvite }
        AlertDialog(
            onDismissRequest = {
                resultInvite.value = false
            },
            text = {
                Text(
                    text = result.value,
                    style = MaterialTheme.typography.h5,
                    color = Color.Gray,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 20.dp)
                )
            },
            confirmButton = {
                Button(
                    onClick = {
                        viewModel.refreshUser(idGroup.toInt())
                        resultInvite.value = false
                        viewModel.drawerState.value = DrawerValue.Closed
                    }, colors = ButtonDefaults.buttonColors(backgroundColor = LightBlue)) {
                    Text("OK")
                }
            },
            dismissButton = {
                Button(
                    onClick = {
                        resultInvite.value = false
                        openDialogInvite.value = true
                    }, colors = ButtonDefaults.buttonColors(backgroundColor = Color.LightGray)) {
                    Text("Back to invite")
                }
            }
        )
    }
}

@RequiresApi(Build.VERSION_CODES.N)
@ExperimentalFoundationApi
@Composable
fun VoteInGroup(navController: NavController, GroupId: String,UserId: String, viewModel: GroupViewModel = hiltViewModel()) {
    val drawerState = remember { viewModel.drawerState }
    viewModel.getVote(UserId.toInt(),GroupId.toInt())
    val listaEnd = mutableListOf<ResultVote>()
    val listaAct = mutableListOf<VoteFragment>()
    for(i in viewModel.mapList)
    {
        listaEnd += ResultVote(i.key,i.value)
    }
    var a = 0
    for(i in viewModel.mapActive)
    {
        listaAct += VoteFragment(viewModel.manyAnsver[a],i.key,i.value,viewModel.endTime[a])
        a++
    }
    val GroupName = remember { viewModel.groupName }
    val Admin = remember {viewModel.admin }
    val creator = remember {viewModel.creator}
    Scaffold(
        topBar = {
            Box(
                Modifier
                    .fillMaxWidth()
                    .background(DarkBlue)
            ) {
                Row {
                    Icon(imageVector = Icons.Outlined.ArrowBack, contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier
                            .size(50.dp)
                            .padding(top = 20.dp, start = 15.dp)
                            .clickable {
                                navController.navigate("MainMenu/${UserId}")
                            }
                        )
                    Text(
                        text = GroupName.value,
                        style = MaterialTheme.typography.h4,
                        color = WhiteText,
                        modifier = Modifier
                            .padding(start = 15.dp, top = 10.dp, bottom = 10.dp)
                    )
                }
                Icon(imageVector = Icons.Outlined.Menu, contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier
                        .padding(end = 15.dp, top = 20.dp)
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
                    GroupActivity(navController,Admin.value, listaEnd,listaAct,UserId,GroupId,viewModel)
                    if (drawerState.value == DrawerValue.Open) {
                        Box(
                            modifier = Modifier
                                .size((parentWidth * 0.25).dp, parentHeight.dp)
                                .offset(x = (parentWidth * 0.12).dp)
                        ) {
                            GroupMenu(navController,Admin.value,creator.value, GroupId,UserId,viewModel)
                        }
                    }
                }
            }
        }
    )
}