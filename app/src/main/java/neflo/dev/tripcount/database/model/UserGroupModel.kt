package neflo.dev.tripcount.database.model

import kotlinx.serialization.Serializable
import java.util.UUID

@Serializable
data class UserGroupModel(
    val userId: UUID,
    val groupId: UUID,
    val name: String,
    val pfp: ByteArray
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as UserGroupModel

        if (userId != other.userId) return false
        if (groupId != other.groupId) return false
        if (name != other.name) return false
        if (!pfp.contentEquals(other.pfp)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = userId.hashCode()
        result = 31 * result + groupId.hashCode()
        result = 31 * result + name.hashCode()
        result = 31 * result + pfp.contentHashCode()
        return result
    }
}