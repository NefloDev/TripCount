package neflo.dev.tripcount.database.model

import kotlinx.serialization.Serializable
import java.util.Date
import java.util.UUID

@Serializable
data class GroupTripModel(
    val tripId: UUID,
    val groupId: UUID,
    val driverNickname: String,
    val date: Date,
    val durationMinutes: Int,
    val origin: String?,
    val destination: String?,
    val notes: String?
){

}