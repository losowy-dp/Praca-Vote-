package com.example.vote_01.ViewModel

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.DataClasses.LinkDataClass
import com.example.DataClasses.OptionDataClass
import com.example.vote_01.Activity.DrawerValue
import com.example.vote_01.Classes.User
import com.example.vote_01.DataClassesForServer.CheckAdminForServer
import com.example.vote_01.DataClassesForServer.inviteUser
import com.example.vote_01.DataClassesForServer.setAnsver
import com.example.vote_01.DataClassesForServer.setModerator
import com.example.vote_01.Server.ServerApi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GroupViewModel @Inject constructor(
    private val api: ServerApi
) : ViewModel() {
    var mapList = mutableMapOf<String, MutableList<OptionDataClass>>()
    var mapActive = mutableMapOf<String, MutableList<OptionDataClass>>()
    var idActive = mutableListOf<Int>()
    var manyAnsver = mutableListOf<Boolean>()
    var endTime = mutableListOf<String>()
    var groupName = mutableStateOf("")
    var admin = mutableStateOf(false)
    var users = mutableListOf<User>()
    var creator = mutableStateOf(0)
    var AnsverVote = mutableListOf<Int>()

    val resultInvite = mutableStateOf("")

    val openDialogInvite = mutableStateOf(false)
    val resultInviteBool = mutableStateOf(false)

    val drawerState = mutableStateOf(DrawerValue.Closed)

    fun getVote(idUser:Int,idGroup: Int)
    {
        viewModelScope.launch(Dispatchers.Main)
        {
            val req = api.getEndingVote(id_group = idGroup)
            if(req.isSuccessful){
                if(req.body()!!.successful)
                {
                    mapList = req.body()!!.message
                }
            }
            val reqq = api.getActiveVote(id_group = idGroup)
            if (reqq.isSuccessful) {
                if (reqq.body()!!.successful) {
                    mapActive = reqq.body()!!.message
                    manyAnsver = reqq.body()!!.ansver
                    endTime = reqq.body()!!.endTime
                    idActive = reqq.body()!!.idVote
                }
            }
            val reqGroupName = api.getGroupName(id_group = idGroup)
            if(reqGroupName.isSuccessful){
                if(reqGroupName.body()!!.successful)
                    groupName.value = reqGroupName.body()!!.message
            }
            val requestUser = api.getUsers(id_group = idGroup)
            if(requestUser.isSuccessful){
                if(requestUser.body()!!.successful){
                    users = requestUser.body()!!.message
                }
            }
            val requestAdmin = api.checkAdmin(CheckAdminForServer(idUser,idGroup))
            if(requestAdmin.isSuccessful){
                if(requestAdmin.body()!!.successful){
                    admin.value = requestAdmin.body()!!.message
                }
            }
            val requsetGetCreator = api.getCreator(idGroup)
            if(requsetGetCreator.isSuccessful){
                if(requsetGetCreator.body()!!.successful){
                    creator.value = requsetGetCreator.body()!!.message.toInt()
                }
            }
            val requstAnsver = api.getAnsver(idUser)
            if(requstAnsver.isSuccessful){
                if(requstAnsver.body()!!.successful){
                    AnsverVote = requstAnsver.body()!!.ListIdVote
                }
            }
        }
    }

    fun refreshUser(idGroup: Int){
        users.clear()
        viewModelScope.launch(Dispatchers.Main)
        {
            val requestUser = api.getUsers(id_group = idGroup)
            if (requestUser.isSuccessful) {
                if (requestUser.body()!!.successful) {
                    users = requestUser.body()!!.message
                }
            }
        }
    }

    fun setAnsver(data: setAnsver){
        viewModelScope.launch(Dispatchers.Main)
        {
            val requstSetAnsver = api.setAnsver(data)
        }
    }

    fun closeVote(idVote: Int){
        viewModelScope.launch(Dispatchers.Main)
        {
            val requstCloseVote = api.closeVote(idVote)
        }
    }

    fun setModerator(data:setModerator) {
        viewModelScope.launch(Dispatchers.Main)
        {
            api.setModerator(data)
        }
    }

    fun kickUser(data: setModerator) {
        viewModelScope.launch(Dispatchers.Main)
        {
            api.kickUser(data)
        }
    }

    fun inviteUser(data:inviteUser){
        resultInvite.value = ""
        viewModelScope.launch(Dispatchers.Main)
        {
            val a = api.inviteUser(data)
            if(a.isSuccessful){
                if(a.body()?.successful == true)
                    resultInvite.value = "User join to group"
                else
                    resultInvite.value = "Not Find User"
            }
            else
                resultInvite.value = "Not Find User"
        }
    }
}