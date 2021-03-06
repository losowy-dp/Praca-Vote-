package com.example.vote_01.ViewModel

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vote_01.DataClassesForServer.CreateGroup
import com.example.vote_01.Fragment.ButtonGroup
import com.example.vote_01.Server.ServerApi
import com.example.vote_01.ui.theme.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MenuTestViewModel @Inject constructor(
    private val api: ServerApi
) : ViewModel()
{
    var listGroup = mutableStateListOf<ButtonGroup>()
    var menuBool = mutableStateOf(true)
    var firstName = mutableStateOf("")
    var lastName = mutableStateOf("")
    var company = mutableStateOf("")
    var email = mutableStateOf("")
    var phone = mutableStateOf("")

    fun getUser(userId:Int)
    {
        viewModelScope.launch(Dispatchers.Main)
        {
            val req = api.getUserInfo(userId)
            if(req.isSuccessful){
                if(req.body()!!.successful)
                {
                    firstName.value = req.body()!!.UserInfo.first_name
                    lastName.value = req.body()!!.UserInfo.last_name
                    company.value = req.body()!!.UserInfo.company
                    email.value = req.body()!!.UserInfo.email
                    phone.value = req.body()!!.UserInfo.phone
                }
            }
        }
    }
    fun loadGroup(idUser: Int){
        viewModelScope.launch(Dispatchers.Main){
            var req = api.getGropeName(idUser)
            if(req.isSuccessful){
                if(req.body()!!.successful)
                {
                    listGroup.clear()
                    for(i in req.body()!!.message){
                        when(i.key % 10 ) {
                            1,5,0 -> listGroup += ButtonGroup(
                                    i.key.toString(),
                                    i.value,
                                    DarkRed,
                                    Red,
                                    LightRed
                                    )
                            2,6 -> listGroup += ButtonGroup(
                                i.key.toString(),
                                i.value,
                                DarkBlue,
                                Blue,
                                LightBlue
                            )
                            3,7 -> listGroup += ButtonGroup(
                                i.key.toString(),
                                i.value,
                                DarkYellow,
                                Yellow,
                                LightYellow
                            )
                            4,9 -> listGroup += ButtonGroup(
                                i.key.toString(),
                                i.value,
                                DarkGreen,
                                Green,
                                LightGreen
                            )
                        }
                    }
                }
            }
        }
    }

    fun addGroup(info:CreateGroup){
        viewModelScope.launch(Dispatchers.Main) {
            api.addGroup(info)
        }
    }
}