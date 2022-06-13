package com.example.repository

import com.example.DataClasses.*
import com.example.Tabels.*
import com.example.repository.DatabaseFactory.dbQuery
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq


class Repo {
    suspend fun addUser(user: UserData) =
        dbQuery{
            UserMain.insert {
                it[email] = user.email
                it[first_name] = user.first_name
                it[last_name] = user.last_name
                it[company] = user.company
                it[phone] = user.phone
                it[hashPassword] = user.password
            }
        }

    suspend fun addGroup(info: CreateGroup) =
        dbQuery {
     GroupUser.insert {
         it[group_name] = info.nameGroup
         it[group_creator] = info.idCreator.toInt()
     }.resultedValues?.map { RowToGroup(it) }?.single()
    }

    suspend fun addLink(info: CreateLink) =
        dbQuery {
            LinkGroupAndUser.insert {
                it[id_group] = info.id_group
                it[id_user] = info.id_user
                it[moderator] = info.moderator
            }
        }

    suspend fun LoaderGroup(idUser:Int) = dbQuery {
        LinkGroupAndUser.select{
            LinkGroupAndUser.id_user.eq(idUser)
        }.map{
            RowToIdGroup(it)
        }
    }

    suspend fun setAnsver(data:setAnsver) = dbQuery{
        for(i in data.options){
            val a = Option.select{ Option.idOption.eq(i.toInt())}.map { it.get(Option.option_value) }.singleOrNull()
            Option.update({Option.idOption eq i.toInt()}){
                it[option_value] = a!! + 1
            }
        }
        Ansver.insert {
            it[id_user] = data.idUser.toInt()
            it[id_vote] = data.idVote.toInt()
        }
    }

    suspend fun findUserByEmail(email:String) = dbQuery {
        UserMain.select{
            UserMain.email.eq(email)
        }.map {
            RowToLoginUser(it)
        }.singleOrNull()
    }

    suspend fun getGroupName(id_group: Int) = dbQuery {
        GroupUser.select{
            GroupUser.idGroup.eq(id_group)
        }.map{
            RowToNameGroup(it)
        }.singleOrNull()
    }

    suspend fun getGroup(id_group: Int) = dbQuery {
        GroupUser.select{
            GroupUser.idGroup.eq(id_group)
        }.map{
            RowToGroup(it)
        }.singleOrNull()
    }

    suspend fun getLinksbyidGroup(id_group: Int)= dbQuery {
        LinkGroupAndUser.select{
            LinkGroupAndUser.id_group.eq(id_group)
        }.map{
            RowToLink(it)
        }
    }

    suspend fun getUserNameById(id_user: Int)= dbQuery {
        UserMain.select{
            UserMain.idUser.eq(id_user)
        }.map{
            RowToUserName(it)
        }.singleOrNull()
    }

    suspend fun getEndingVote(id_group: Int) = dbQuery {
        VoteEnding.select{
            VoteEnding.id_group.eq(id_group)
        }.map {
            RowToEndingVote(it)
        }
    }

    suspend fun getOption(id_vote: Int) = dbQuery {
        Option.select{
            Option.id_vote.eq(id_vote)
        }.map {
            RowToOption(it)
        }
    }

    suspend fun getActiveVote(id_group: Int) = dbQuery{
        VoteActive.select{
            VoteActive.id_group.eq(id_group)
        }.map {
            RowToActiveVote(it)
        }
    }

    suspend fun checkAdminInGroup(id_user: Int,id_group: Int)= dbQuery{
        LinkGroupAndUser.select{
            LinkGroupAndUser.id_group.eq(id_group).and(LinkGroupAndUser.id_user.eq(id_user))
        }.map {
            RowToAdmin(it)
        }.singleOrNull()
    }

    suspend fun findCreator(id_group: Int)= dbQuery {
        GroupUser.select{
            GroupUser.idGroup.eq(id_group)
        }.map {
            RowToId(it)
        }.singleOrNull()
    }

    suspend fun findAnsver(id_user: Int) = dbQuery {
        Ansver.select{
            Ansver.id_user.eq(id_user)
        }.map {
            RowToIdVote(it)
        }
    }

    suspend fun closeVote(id_vote: Int) = dbQuery {
        val old = VoteActive.select{
            VoteActive.idVoteActive.eq(id_vote)
        }.map { RowToActiveVote(it) }.singleOrNull()
        if(old != null){
            VoteEnding.insert {
                it[idVoteEnding] = id_vote
                it[vote_name] =old.vote_name
                it[id_group] = old.id_group
            }
            VoteActive.deleteWhere {
                VoteActive.idVoteActive.eq(id_vote)
            }
        }
    }

    suspend fun createVote(new:CreateVote) = dbQuery {
        val a = VoteActive.insert {
            it[id_group] = new.id_group
            it[vote_name] = new.vote_name
            it[many_ansver] = new.many_ansver
            it[endtime] = new.time_end
        }.resultedValues?.map { RowToIdActiveVote(it) }?.single()
        for(i in new.option)
        {
            Option.insert {
                it[id_vote] = a!!
                it[option_name] = i
                it[option_value] = 0
            }
        }
    }

    suspend fun setModerator(info:setModerator) = dbQuery {
        LinkGroupAndUser.update({LinkGroupAndUser.id_user eq info.idUser and (LinkGroupAndUser.id_group eq info.idGroup)} ) {
            it[moderator] = true
        }
    }

    suspend fun kickUser(info:setModerator) = dbQuery {
        LinkGroupAndUser.deleteWhere {
            LinkGroupAndUser.id_user eq info.idUser and (LinkGroupAndUser.id_group eq info.idGroup)
        }
    }

    suspend fun inviteUser(info:inviteUser) = dbQuery {
        val id = UserMain.select{
            UserMain.email.eq(info.email)
        }.map { RowToIdUser(it) }.singleOrNull()
        LinkGroupAndUser.insert {
            it[id_group] = info.id
            it[id_user] = id!!
            it[moderator] = false
        }
    }
    suspend fun getUserOnId(id_user: Int) = dbQuery {
        UserMain.select{
            UserMain.idUser.eq(id_user)
        }.map { RowToUserData(it) }.singleOrNull()
    }

    private fun RowToUserData(row: ResultRow?):UserRegistration?
    {
        if(row == null)
            return null
        return UserRegistration(
            row[UserMain.email],row[UserMain.first_name],
            row[UserMain.last_name],row[UserMain.company],
            row[UserMain.phone],"NONE"
        )
    }

    private fun RowToIdLink(row: ResultRow?):Int?{
        if(row == null)
            return null

        return row[LinkGroupAndUser.idLink]
    }

    private fun RowToIdUser(row: ResultRow?):Int?{
        if(row == null)
            return null

        return row[UserMain.idUser]
    }

    private fun RowToIdActiveVote(row: ResultRow?): Int?
    {
        if(row == null)
            return null

        return row[VoteActive.idVoteActive]
    }

    private fun RowToIdVote(row: ResultRow?): Int?{
        if(row == null)
            return null

        return row[Ansver.id_vote]
    }

    private fun RowToId(row: ResultRow?): Int?{
        if(row == null)
            return null
        return row[GroupUser.group_creator]
    }

    private fun RowToAdmin(row: ResultRow?):Boolean?
    {
        if(row == null)
            return null
        return row[LinkGroupAndUser.moderator]
    }

    private fun RowToActiveVote(row: ResultRow?): VoteActiveDataClass?
    {
        if(row == null)
            return null

        return VoteActiveDataClass(row[VoteActive.idVoteActive],row[VoteActive.id_group],row[VoteActive.vote_name],row[VoteActive.many_ansver],row[VoteActive.endtime],row[VoteActive.idVoteActive])
    }

    private fun RowToOption(row: ResultRow?): OptionDataClass?{
        if(row == null)
            return null

        return OptionDataClass(row[Option.idOption],row[Option.option_name],row[Option.option_value])
    }

    private fun RowToEndingVote(row: ResultRow?): VoteEndingDataClass?
    {
        if(row == null)
            return null

        return VoteEndingDataClass(row[VoteEnding.idVoteEnding],row[VoteEnding.id_group],row[VoteEnding.vote_name])
    }

    private fun RowToUserName(row: ResultRow?):String
    {
        if(row == null)
            return "null"

        return row[UserMain.first_name] + " " + row[UserMain.last_name]
    }

    private fun RowToLink(row: ResultRow?):LinkDataClass?
    {
        if(row == null)
            return null

        return LinkDataClass(row[LinkGroupAndUser.idLink],row[LinkGroupAndUser.id_group],row[LinkGroupAndUser.id_user],row[LinkGroupAndUser.moderator])
    }

    private fun RowToGroup(row: ResultRow?): GroupDataClass?
    {
        if(row == null)
            return null

        return GroupDataClass(row[GroupUser.idGroup],row[GroupUser.group_name],row[GroupUser.group_creator])
    }

    private fun RowToNameGroup(row: ResultRow?): String
    {
        if(row == null)
            return ""

        return row[GroupUser.group_name]
    }

    private fun RowToIdGroup(row: ResultRow?): Int {
        if(row == null)
            return 0

        return row[LinkGroupAndUser.id_group]
    }

    private fun RowToLoginUser(row: ResultRow?): LoginData? {
        if(row == null)
            return null

        return LoginData(row[UserMain.idUser],row[UserMain.email],row[UserMain.hashPassword])
    }
}