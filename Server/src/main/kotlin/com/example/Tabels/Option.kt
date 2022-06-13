package com.example.Tabels

import org.jetbrains.exposed.sql.Table

object Option:Table() {
    val idOption = integer("id").autoIncrement().uniqueIndex()
    val id_vote = integer("id_vote")
    val option_name = varchar("option_name",512)
    val option_value = integer("option_value")
    override val primaryKey: PrimaryKey = PrimaryKey(idOption)
}