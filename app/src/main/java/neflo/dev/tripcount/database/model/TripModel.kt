package neflo.dev.tripcount.database.model

import kotlinx.serialization.Serializable
import java.time.LocalDate
import java.util.UUID

@Serializable
data class TripModel(
    val uuid: UUID,
    val group: GroupModel,
    val driver: UserModel,
    val date: LocalDate,
    val durationMinutes: Int,
    val origin: String?,
    val destination: String?,
    val notes: String?
) {

}
