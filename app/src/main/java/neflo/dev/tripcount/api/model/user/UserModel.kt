package neflo.dev.tripcount.api.model.user

import kotlin.uuid.Uuid

data class UserModel(
    val id: Uuid,
    val email: String,
    var password: String,
    val name: String,
    val nickname: String,
    var pfp: String
)
