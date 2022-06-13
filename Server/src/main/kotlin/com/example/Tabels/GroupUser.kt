package com.example.Tabels

import org.jetbrains.exposed.sql.Table

object GroupUser: Table() {
    val idGroup = integer("id_group").autoIncrement().uniqueIndex()
    val group_name = varchar("group_name",512)
    val group_creator = reference("group_creator_id",UserMain.idUser)
    override val primaryKey: Table.PrimaryKey = PrimaryKey(idGroup)
}