package com.example.plugins

import com.example.DataClasses.*
import com.example.Response.*
import com.example.authentficate.JwtService
import com.example.repository.Repo
import io.ktor.server.routing.*
import io.ktor.http.HttpStatusCode.Companion.BadRequest
import io.ktor.http.HttpStatusCode.Companion.Conflict
import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.request.*
import kotlin.Exception

fun Application.configureRouting(database: Repo, jwtService: JwtService, hashFunction: (String)->String) {

    routing {
        get("/") {
            call.respondText("Hello World!")
        }
        post("/login") {
            val loginDate = try {
                call.receive<LoginData>()
            } catch (e: Exception) {
                call.respond(BadRequest, SimpleResponse(false, "Not connection"))
                return@post
            }
            try {
                val result = database.findUserByEmail(loginDate.login)
                if (result != null && result.password == hashFunction(loginDate.password)) {

                    call.respond(OK, SimpleResponse(true, result.user_id.toString()))
                }
                else {
                    call.respond(Conflict, SimpleResponse(false, "Login error"))
                }
            } catch (e: Exception) {
            }
        }
        post("/registration") {
            val registrationDate = try {
                call.receive<UserData>()
            } catch (e: Exception) {
                call.respond(BadRequest, SimpleResponse(false, "Not connection"))
                return@post
            }
            try {
                database.addUser(
                    UserData(
                        registrationDate.email,
                        registrationDate.first_name,
                        registrationDate.last_name,
                        registrationDate.company,
                        registrationDate.phone,
                        hashFunction(registrationDate.password)
                    )
                )
                call.respond(OK, SimpleResponse(true, "successful"))
            } catch (e: Exception) {
                call.respond(BadRequest, SimpleResponse(false, "Email already using"))
            }
        }

            post("/takegroup") {
            val id_user = try {
                call.receive<String>()
            } catch (e: Exception) {
                call.respond(BadRequest, SimpleResponse(false, "Not connection"))
                return@post
            }
            try {
                val result = database.LoaderGroup(id_user.toInt())
                val lista = mutableMapOf<Int, String>()
                for (i in result) {
                    lista[i] = database.getGroupName(i).toString()
                }
                call.respond(OK, MapResponse(true, lista))
            } catch (e: Exception) {
                call.respond(Conflict, SimpleResponse(false, "Error"))
            }
        }

        post("/getgroup")
        {
            val id_group = try {
                call.receive<String>()
            } catch (e: Exception) {
                call.respond(BadRequest, SimpleResponse(false, "Not connection"))
                return@post
            }
            try {
                val user = database.getGroup(id_group.toInt())
                call.respond(OK, GroupResponse(true, user!!))
            } catch (e: Exception) {
                call.respond(BadRequest, SimpleResponse(false, "Group not found"))
            }
        }

        post("/getListUserinGroup") {
            val id_group = try {
                call.receive<String>()
            } catch (e: Exception) {
                call.respond(BadRequest, LinkMapResponse(false, null))
                return@post
            }
            try {
                val result = database.getLinksbyidGroup(id_group.toInt())
                val lista = mutableListOf<userForGroups>()
                for (i in result) {
                    if (i != null) {
                        lista += userForGroups(i.id_user,i.moderator, database.getUserNameById(i.id_user)!!)
                    }
                }
                call.respond(OK, LinkMapResponse(true, lista))
            } catch (e: Exception) {
                call.respond(BadRequest, LinkMapResponse(false, null))
            }
        }

        post("/checkAdmin"){
            val id = try{
                call.receive<CheckAdmin>()
            }catch (e: Exception) {
                call.respond(BadRequest, CheckResponse(false, false))
                return@post
            }
            try {
                val result = database.checkAdminInGroup(id.userId.toInt(),id.groupId.toInt())
                if(result != null)
                call.respond(OK, CheckResponse(true,result))
            }catch (e: Exception) {
                call.respond(Conflict, CheckResponse(false, false))
            }
        }

        post("/getListEndingVote") {
            val id_group = try {
                call.receive<String>()
            } catch (e: Exception) {
                call.respond(BadRequest, SimpleResponse(false, "Not connection"))
                return@post
            }
            try {
                val result = database.getEndingVote(id_group.toInt())
                val map = mutableMapOf<String, MutableList<OptionDataClass>>()
                for (i in result) {
                    if (i != null) {
                        val lista = mutableListOf<OptionDataClass>()
                        val list = database.getOption(i.idVoteEnding)
                        for (j in list) {
                            if (j != null)
                                lista.add(j)
                        }
                        map.set(i.vote_name, lista)
                    }
                }
                call.respond(OK, MapListOptionResponse(true, map))
            } catch (e: Exception) {
                call.respond(BadRequest, SimpleResponse(false, "Group not found"))
            }
        }

        post("/getgroupname"){
            val id_group = try {
                call.receive<String>()
            } catch (e: Exception) {
                call.respond(BadRequest, SimpleResponse(false, "Not connection"))
                return@post
            }
            try{
                val result = database.getGroupName(id_group.toInt())
                call.respond(OK,SimpleResponse(true, result.toString()))
            }catch (e: Exception) {
                call.respond(BadRequest, SimpleResponse(false, "Group not found"))
            }
        }

        post("/getListActiveVote") {
            val id_group = try {
                call.receive<String>()
            } catch (e: Exception) {
                call.respond(BadRequest, SimpleResponse(false, "Not connection"))
                return@post
            }
            try {
                val result = database.getActiveVote(id_group.toInt())
                val map = mutableMapOf<String, MutableList<OptionDataClass>>()
                val listAnsver = mutableListOf<Boolean>()
                val listEndTime = mutableListOf<String>()
                val id = mutableListOf<Int>()
                for (i in result) {
                    if (i != null) {
                        val lista = mutableListOf<OptionDataClass>()
                        val list = database.getOption(i.idVoteActive)
                        for (j in list) {
                            if (j != null)
                                lista.add(j)
                        }
                        map.set(i.vote_name, lista)
                        id += i.idVote
                        if (i.many_ansver == true)
                            listAnsver += true
                        else
                            listAnsver += false
                        listEndTime += i.time_end
                    }
                }
                call.respond(OK, MapListAcriveOption(true, map,listAnsver,listEndTime,id))
            } catch (e: Exception) {
                call.respond(BadRequest, SimpleResponse(false, "Group not found"))
            }
        }

        post("/getCreator"){
            val id_group = try {
                call.receive<String>()
            } catch (e: Exception) {
                call.respond(BadRequest, SimpleResponse(false, "Not connection"))
                return@post
            }
            try {
                val result = database.findCreator(id_group.toInt())
                if(result != null)
                    call.respond(OK, SimpleResponse(true, result.toString()))
            }
            catch (e: Exception) {
                call.respond(Conflict, SimpleResponse(false, "Group not found"))
            }
        }

        post("/createGroup"){
            val infoGroup = try {
                call.receive<CreateGroup>()
            }catch (e: Exception){
                call.respond(BadRequest, SimpleResponse(false, "Not connection"))
                return@post
            }
            try{
                val new = database.addGroup(infoGroup)
                database.addLink(CreateLink(new!!.id_group,infoGroup.idCreator.toInt(),true))
                call.respond(OK, SimpleResponse(true,"successful"))
            }
            catch (e: Exception){
                call.respond(Conflict, SimpleResponse(false,"Bad data"))
            }
        }

        post("/getansver")
        {
            val idUser = try{
                call.receive<String>()
            }catch (e: Exception){
                call.respond(BadRequest, ListIntResponse(false, null))
                return@post
            }
            try{
                val list = database.findAnsver(idUser.toInt())
                call.respond(OK,ListIntResponse(true, list))
            }
            catch (e: Exception){
                call.respond(Conflict, ListIntResponse(false,null))
            }
        }

        post("/setAnsver"){
            val data = try{
                call.receive<setAnsver>()
            }catch (e: Exception){
                call.respond(BadRequest, SimpleResponse(false, "error"))
                return@post
            }
            try{
                database.setAnsver(data)
                call.respond(OK, SimpleResponse(true,"successful"))
            }
            catch (e: Exception){
                call.respond(Conflict, SimpleResponse(false,"erorr data"))
            }
        }

        post("/closeVote"){
            val idVote = try{
                call.receive<String>()
            }catch (e: Exception){
                call.respond(BadRequest, SimpleResponse(false, "error"))
                return@post
            }
            try{
                database.closeVote(idVote.toInt())
                call.respond(OK, SimpleResponse(true,"successful"))
            }
            catch (e: Exception){
                call.respond(Conflict, SimpleResponse(false,"erorr data"))
            }
        }

        post("/createVote"){
            val data = try{
                call.receive<CreateVote>()
            }catch (e:Exception){
                call.respond(BadRequest, SimpleResponse(false, "error"))
                return@post
            }
            try{
                database.createVote(data)
                call.respond(OK, SimpleResponse(true,"successful"))
            }
            catch (e: Exception){
                call.respond(Conflict, SimpleResponse(false,"erorr data"))
            }
        }

        post("/setModerator"){
            val data = try{
                call.receive<setModerator>()
            }catch (e:Exception){
                call.respond(BadRequest, SimpleResponse(false, "error"))
                return@post
            }
            try{
                database.setModerator(data)
                call.respond(OK, SimpleResponse(true,"successful"))
            }
            catch (e: Exception){
                call.respond(Conflict, SimpleResponse(false,"erorr data"))
            }
        }

        post("/kickUser"){
            val data = try{
                call.receive<setModerator>()
            }catch (e:Exception){
                call.respond(BadRequest, SimpleResponse(false, "error"))
                return@post
            }
            try{
                database.kickUser(data)
                call.respond(OK, SimpleResponse(true,"successful"))
            }
            catch (e: Exception){
                call.respond(Conflict, SimpleResponse(false,"erorr data"))
            }
        }

        post("/inviteUser"){
            val data = try{
                call.receive<inviteUser>()
            }catch (e:Exception){
                call.respond(BadRequest, SimpleResponse(false, "error"))
                return@post
            }
            try{
                database.inviteUser(data)
                call.respond(OK, SimpleResponse(true,"successful"))
            }
            catch (e: Exception){
                call.respond(Conflict, SimpleResponse(false,"erorr data"))
            }
        }

        post("/getUserInfo"){
            val idUser = try{
                call.receive<String>()
            }catch (e:Exception){
                call.respond(BadRequest, SimpleResponse(false, "error"))
                return@post
            }
            try{
                val data = database.getUserOnId(idUser.toInt())
                if(data != null)
                    call.respond(OK, UserInfoResponse(true,data))
            }
            catch (e: Exception){
                call.respond(Conflict, SimpleResponse(false,"erorr data"))
            }
        }
    }
}

