package com.example.vote_01.ViewModel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vote_01.DataClassesForServer.LoginData
import com.example.vote_01.Server.ServerApi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val api: ServerApi
) : ViewModel(){

    var isLoading = mutableStateOf(false)
    var loadError = mutableStateOf("")
    var id = mutableStateOf("")

    fun login(data: LoginData)
    {
        isLoading.value = true
        viewModelScope.launch(Dispatchers.IO) {
            var requst = api.login(loginUser = data)
            if(requst.isSuccessful){
            if(requst.body()!!.successful)
            {
                id.value = requst.body()!!.message
                isLoading.value = false
            }
            else
            {
                loadError.value = requst.body()!!.message
                isLoading.value = false
            }
            }
            else{
                isLoading.value = false
                loadError.value = requst.body().toString()
            }
        }
    }
}