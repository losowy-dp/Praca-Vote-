package com.example.vote_01.ViewModel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vote_01.Server.ServerApi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaType
import javax.inject.Inject

@HiltViewModel
class ProfileUserViewModel @Inject constructor(
    private val api: ServerApi
) : ViewModel()
{
    var firstName = mutableStateOf("")
    var lastName = mutableStateOf("")
    var company = mutableStateOf("")
    var email = mutableStateOf("")
    var phone = mutableStateOf("")
    var aboutMe = mutableStateOf("")

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
}