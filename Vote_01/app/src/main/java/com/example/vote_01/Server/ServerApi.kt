package com.example.vote_01.Server

import com.example.vote_01.Classes.User
import com.example.vote_01.Classes.UserRegistration
import com.example.vote_01.DataClassesForServer.CheckAdminForServer
import com.example.vote_01.DataClassesForServer.CreateGroup
import com.example.vote_01.DataClassesForServer.LoginData
import com.example.vote_01.DataClassesForServer.setAnsver
import com.example.vote_01.Response.*
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ServerApi {

    @GET("/home")
    suspend fun test(): Response<SimpleResponse>

    @POST("/registration")
    suspend fun registration(@Body newUser: UserRegistration): Response<SimpleResponse>

    @POST("/login")
    suspend fun login(@Body loginUser: LoginData): Response<SimpleResponse>

    @POST("/takegroup")
    suspend fun getGropeName(@Body id_user:Int): Response<MapResponse>

    @POST("/getListEndingVote")
    suspend fun getEndingVote(@Body id_group:Int): Response<MapListOptionResponse>

    @POST("/getListActiveVote")
    suspend fun getActiveVote(@Body id_group:Int): Response<MapListActiveOption>

    @POST("/getgroupname")
    suspend fun getGroupName(@Body id_group:Int): Response<SimpleResponse>

    @POST("/getListUserinGroup")
    suspend fun getUsers(@Body id_group:Int): Response<LinkMapResponse>

    @POST("/checkAdmin")
    suspend fun checkAdmin(@Body id: CheckAdminForServer): Response<checkAdmin>

    @POST("/getCreator")
    suspend fun getCreator(@Body id_group:Int): Response<SimpleResponse>

    @POST("/createGroup")
    suspend fun addGroup(@Body info:CreateGroup): Response<SimpleResponse>

    @POST("/getansver")
    suspend fun getAnsver(@Body id_user:Int): Response<ListIntResponse>

    @POST("/setAnsver")
    suspend fun setAnsver(@Body data: setAnsver): Response<SimpleResponse>

    @POST("/closeVote")
    suspend fun closeVote(@Body idVote:Int): Response<SimpleResponse>

    companion object {
        const val BASE_URL = "http://10.206.2.131:8080"
    }
}