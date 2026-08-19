package neflo.dev.tripcount.database.model

import kotlinx.serialization.Serializable

@Serializable
data class TripPassengersRelation(
    val trip: TripModel,
    val user: UserModel
    )
