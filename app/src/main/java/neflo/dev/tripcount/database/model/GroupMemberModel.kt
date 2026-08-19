package neflo.dev.tripcount.database.model

import kotlinx.serialization.Serializable
import java.util.UUID

@Serializable
data class GroupMemberModel(
    val groupId: UUID,
    val userId: UUID,
    val nickname: String,
    val timeBalance: Int
){

}