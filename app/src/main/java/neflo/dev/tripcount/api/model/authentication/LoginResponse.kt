package neflo.dev.tripcount.api.model.authentication

data class LoginResponse(
    val token: String,
    val expiresOn: Long
)
