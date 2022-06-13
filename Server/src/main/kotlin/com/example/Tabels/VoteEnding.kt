package com.example.Tabels

import com.example.Tabels.Option.autoIncrement
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.Table.Dual.integer
import org.jetbrains.exposed.sql.Table.Dual.varchar

object VoteEnding: Table() {
    val idVoteEnding = integer("idVoteEnding").uniqueIndex()
    val id_group = reference("id_group",GroupUser.idGroup)
    val vote_name = varchar("vote_name",512)
    override val primaryKey: Table.PrimaryKey = PrimaryKey(idVoteEnding)
}