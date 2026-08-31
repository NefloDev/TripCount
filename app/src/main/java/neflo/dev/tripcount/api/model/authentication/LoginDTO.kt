package neflo.dev.tripcount.api.model.authentication

data class LoginDTO(
    val email: String,
    val password: String
)