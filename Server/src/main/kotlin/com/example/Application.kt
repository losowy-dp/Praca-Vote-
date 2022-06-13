package com.example

import com.example.authentficate.JwtService
import com.example.authentficate.hash
import com.example.plugins.*
import com.example.repository.DatabaseFactory
import com.example.repository.Repo
import io.ktor.server.application.*
import io.ktor.server.locations.*

fun main(args: Array<String>): Unit =
    io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // application.conf references the main function. This annotation prevents the IDE from marking it as unused.
fun Application.module() {
    DatabaseFactory.init()
    val db = Repo()
    val jwtService = JwtService()
    val hashFunction = {s:String -> hash(s)}
    configureRouting(db,jwtService,hashFunction)
    configureSecurity()
    configureSerialization()
}
