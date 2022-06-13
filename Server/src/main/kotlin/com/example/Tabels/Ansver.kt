package com.example.Tabels

import org.jetbrains.exposed.sql.Table


object Ansver:Table() {
    val id_Ansver = integer("idLink").autoIncrement().uniqueIndex()
    val id_user = reference("id_user",UserMain.idUser)
    val id_vote = integer("id_vote")
    override val primaryKey: PrimaryKey = PrimaryKey(Ansver.id_Ansver)
}