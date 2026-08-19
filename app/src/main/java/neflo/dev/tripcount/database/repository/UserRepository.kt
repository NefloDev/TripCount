package neflo.dev.tripcount.database.repository

import io.github.jan.supabase.postgrest.from
import neflo.dev.tripcount.database.DatabaseClient
import neflo.dev.tripcount.database.groupMembersView
import neflo.dev.tripcount.database.model.GroupMemberModel
import neflo.dev.tripcount.database.model.UserModel
import neflo.dev.tripcount.database.userTable
import java.util.UUID

interface UserRepository {

    suspend fun insert(userModel: UserModel) {
        DatabaseClient.supabase
            .from(userTable)
            .insert(userModel)
    }

    suspend fun updateUser(userModel: UserModel) {
        DatabaseClient.supabase
            .from(userTable)
            .update(
                mapOf(
                    "NAME" to userModel.name,
                    "NICKNAME" to userModel.nickname,
                    "PFP" to userModel.pfp
                )
            ){
                filter {
                    eq("UUID", userModel.uuid)
                }
            }
    }

    suspend fun deleteUserByUuid(uuid: UUID) {
        DatabaseClient.supabase
            .from(userTable)
            .delete {
                filter {
                    eq("UUID", uuid)
                }
            }
    }

    suspend fun getUserByEmail(email: String): UserModel {
        return DatabaseClient.supabase
            .from(userTable)
            .select {
                filter {
                    eq("EMAIL", email)
                }
            }.decodeAs<UserModel>()
    }

    suspend fun getGroupMembers(groupId: UUID) : List<GroupMemberModel> {
        return DatabaseClient.supabase
            .from(groupMembersView)
            .select {
                filter {
                    eq("GRP_ID", groupId)
                }
            }.decodeList<GroupMemberModel>()
    }

}