package neflo.dev.tripcount.database.model

import kotlinx.serialization.Serializable
import java.util.UUID

@Serializable
data class UserModel(
    val uuid: UUID,
    val email: String,
    val password: String,
    val name: String,
    val nickname: String? = null,
    val pfp: ByteArray
) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as UserModel

        if (uuid != other.uuid) return false
        if (email != other.email) return false
        if (password != other.password) return false
        if (name != other.name) return false
        if (nickname != other.nickname) return false
        if (!pfp.contentEquals(other.pfp)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = uuid.hashCode()
        result = 31 * result + email.hashCode()
        result = 31 * result + password.hashCode()
        result = 31 * result + name.hashCode()
        result = 31 * result + (nickname?.hashCode() ?: 0)
        result = 31 * result + pfp.contentHashCode()
        return result
    }
}