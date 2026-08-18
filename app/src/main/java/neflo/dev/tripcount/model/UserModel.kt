package neflo.dev.tripcount.model

import java.security.InvalidParameterException

data class UserModel(
    val name: String? = null,
    val email: String? = null,
    val nickname: String? = null
) {
    fun userToHashMap() : HashMap<String, *> {
        if (name == null || email == null){
            throw InvalidParameterException("Name and email fields must be filled")
        }
        return hashMapOf(
            "name" to name,
            "email" to email,
            "nickname" to (nickname ?: name)
        )
    }
}