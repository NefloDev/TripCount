package neflo.dev.tripcount.database.repository

import io.github.jan.supabase.postgrest.from
import neflo.dev.tripcount.database.DatabaseClient
import neflo.dev.tripcount.database.groupTripsView
import neflo.dev.tripcount.database.model.GroupTripModel
import neflo.dev.tripcount.database.model.TripModel
import neflo.dev.tripcount.database.tripsTable
import java.util.UUID

interface TripsRepository {

    suspend fun insert(groupTripsModel: TripModel) {
        DatabaseClient.supabase
            .from(tripsTable)
            .insert(groupTripsModel)
    }

    suspend fun updateTrip(groupTripsModel: TripModel) {
        DatabaseClient.supabase
            .from(tripsTable)
            .update(
                mapOf(
                        "DRIVER" to groupTripsModel.driver,
                        "DATE" to groupTripsModel.date,
                        "DURATION_MINUTES" to groupTripsModel.durationMinutes,
                        "ORIGIN" to groupTripsModel.origin,
                        "DESTINATION" to groupTripsModel.destination,
                        "NOTES" to groupTripsModel.notes
                )
            ){
                filter {
                    eq("UUID", groupTripsModel.uuid)
                }
            }
    }

    suspend fun deleteTripByUuid(uuid: UUID) {
        DatabaseClient.supabase
            .from(tripsTable)
            .delete {
                filter {
                    eq("UUID", uuid)
                }
            }
    }

    suspend fun getTripsByGroup(groupId: UUID) : List<GroupTripModel> {
        return DatabaseClient.supabase
            .from(groupTripsView)
            .select {
                filter {
                    eq("GRP_ID", groupId)
                }
            }.decodeList<GroupTripModel>()
    }

}