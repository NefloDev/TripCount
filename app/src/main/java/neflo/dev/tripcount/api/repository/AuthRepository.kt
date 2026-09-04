package neflo.dev.tripcount.api.repository

import jakarta.inject.Inject
import neflo.dev.tripcount.api.model.authentication.GoogleLoginDTO
import neflo.dev.tripcount.api.model.authentication.LoginDTO
import neflo.dev.tripcount.api.model.helper.apiRequestFlow
import neflo.dev.tripcount.api.model.user.UserDTO
import neflo.dev.tripcount.api.service.AuthService

class AuthRepository @Inject constructor(private val authService: AuthService) {

    fun login(loginDTO: LoginDTO) = apiRequestFlow {
        authService.login(loginDTO)
    }

    fun googleLogin(googleLoginDTO: GoogleLoginDTO) = apiRequestFlow {
        authService.googleLogin(googleLoginDTO)
    }

    fun signUp(registerDTO: UserDTO) = apiRequestFlow {
        authService.signUp(registerDTO)
    }


}