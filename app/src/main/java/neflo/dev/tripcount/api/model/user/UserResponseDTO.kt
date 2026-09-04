package neflo.dev.tripcount.api.model.user

import kotlin.uuid.Uuid

data class UserResponseDTO(
    val id: Uuid,
    val email: String,
    val name: String,
    val nickname: String,
    val pfp: String
)
