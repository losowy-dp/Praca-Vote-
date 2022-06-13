package com.example.Tabels

import org.jetbrains.exposed.sql.Table

object VoteActive: Table() {
    val idVoteActive = integer("idVoteActive").autoIncrement().uniqueIndex()
    val id_group = reference("id_group",GroupUser.idGroup)
    val vote_name = varchar("vote_name",512)
    val many_ansver = bool("many_ansver")
    val endtime = varchar("endtime",512)
    override val primaryKey: PrimaryKey = PrimaryKey(idVoteActive)
}