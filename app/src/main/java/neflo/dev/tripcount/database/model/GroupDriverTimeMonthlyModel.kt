package neflo.dev.tripcount.database.model

import kotlinx.serialization.Serializable
import java.util.UUID

@Serializable
data class GroupDriverTimeMonthlyModel(
    val groupId: UUID,
    val userId: UUID,
    val year: Int,
    val month: Int,
    val durationMinutes: Int,
    val totalDurationMinutes: Int
){

}