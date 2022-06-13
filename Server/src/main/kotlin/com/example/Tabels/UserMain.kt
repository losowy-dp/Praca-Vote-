package com.example.Tabels

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.Table.Dual.varchar

object UserMain: Table() {
    val idUser = integer("id_user").autoIncrement().uniqueIndex()
    val email = varchar("email",512).uniqueIndex()
    val first_name = varchar("first_name",512)
    val last_name = varchar("last_name",512)
    val company = varchar("company",512)
    val phone = varchar("phone",512)
    val hashPassword = varchar("hashPassword",512)
    override val primaryKey: PrimaryKey = PrimaryKey(idUser)
}