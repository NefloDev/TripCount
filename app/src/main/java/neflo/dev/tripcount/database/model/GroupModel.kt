package neflo.dev.tripcount.database.model

import kotlinx.serialization.Serializable
import java.util.UUID

@Serializable
data class GroupModel(
    val uuid: UUID,
    val name: String,
    val pfp: ByteArray
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as GroupModel

        if (uuid != other.uuid) return false
        if (name != other.name) return false
        if (!pfp.contentEquals(other.pfp)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = uuid.hashCode()
        result = 31 * result + name.hashCode()
        result = 31 * result + pfp.contentHashCode()
        return result
    }
}