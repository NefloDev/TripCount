package neflo.dev.tripcount.database.model

import kotlinx.serialization.Serializable

@Serializable
data class GroupMemberRelation(
    val user: UserModel,
    val group: GroupModel,
    val timeBalance: Int
)
