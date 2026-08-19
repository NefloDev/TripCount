package neflo.dev.tripcount.database.repository

import io.github.jan.supabase.postgrest.from
import neflo.dev.tripcount.database.DatabaseClient
import neflo.dev.tripcount.database.groupDriverTimeMonthlyView
import neflo.dev.tripcount.database.groupTable
import neflo.dev.tripcount.database.model.GroupDriverTimeMonthlyModel
import neflo.dev.tripcount.database.model.GroupModel
import neflo.dev.tripcount.database.model.UserGroupModel
import neflo.dev.tripcount.database.userGroupsView
import java.util.UUID

interface GroupRepository {

    suspend fun insert(groupModel: GroupModel) {
        DatabaseClient.supabase
            .from(groupTable)
            .insert(groupModel)
    }

    suspend fun updateGroup(groupModel: GroupModel) {
        DatabaseClient.supabase
            .from(groupTable)
            .update(
                mapOf(
                    "NAME" to groupModel.name,
                    "PFP" to groupModel.pfp
                )
            ){
                filter {
                    eq("UUID", groupModel.uuid)
                }
            }
    }

    suspend fun deleteGroupByUuid(uuid: UUID) {
        DatabaseClient.supabase
            .from(groupTable)
            .delete {
                filter {
                    eq("UUID", uuid)
                }
            }
    }

    suspend fun getGroupsByUser(user: UUID) : List<UserGroupModel> {
        return DatabaseClient.supabase
            .from(userGroupsView)
            .select {
                filter {
                    eq("USR_ID", user)
                }
            }.decodeList<UserGroupModel>()
    }

    suspend fun getGroupSummaryByMonth(month: Int, groupId: UUID) : List<GroupDriverTimeMonthlyModel>{
        return DatabaseClient.supabase
            .from(groupDriverTimeMonthlyView)
            .select {
                filter {
                    eq("GRP_ID", groupId)
                    eq("MONTH", month)
                }
            }.decodeList<GroupDriverTimeMonthlyModel>()
    }

}