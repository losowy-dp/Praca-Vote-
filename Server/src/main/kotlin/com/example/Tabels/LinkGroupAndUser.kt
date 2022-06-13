package com.example.Tabels

import org.jetbrains.exposed.sql.Table

object LinkGroupAndUser: Table() {
    val idLink = integer("idLink").autoIncrement().uniqueIndex()
    val id_group = reference("id_group",GroupUser.idGroup)
    val id_user = reference("id_user",UserMain.idUser)
    val moderator = bool("moderator")
    override val primaryKey: PrimaryKey = PrimaryKey(idLink)
}