package com.example.vote_01.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vote_01.DataClassesForServer.CreateVote
import com.example.vote_01.Server.ServerApi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreateVoteViewModel @Inject constructor(private val api: ServerApi) : ViewModel() {

    fun createVote(data: CreateVote){
        viewModelScope.launch(Dispatchers.Main) {
            api.createVote(data)
        }
    }
}