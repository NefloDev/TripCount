package neflo.dev.tripcount.api.model.user

data class UserDTO(
    val email: String,
    var password: String,
    val name: String,
    val nickname: String,
    var pfp: String
)