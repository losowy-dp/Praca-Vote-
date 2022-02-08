package com.example.vote_01.ViewModel

import androidx.compose.runtime.mutableStateOf

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vote_01.Classes.UserRegistration
import com.example.vote_01.Server.ServerApi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

import javax.inject.Inject

@HiltViewModel
class RegistrationViewModel @Inject constructor(
    private val api: ServerApi
) : ViewModel() {

    var isLoading = mutableStateOf(false)
    var loadError = mutableStateOf("")

    fun registratinModel(newUser:UserRegistration)
    {
        isLoading.value = true
        viewModelScope.launch(Dispatchers.IO) {
            var requst = api.registration(newUser = newUser)
            if(requst.isSuccessful){
                if(requst.body()!!.successful)
                {
                    loadError.value = requst.body()!!.message
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
