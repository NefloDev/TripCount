package neflo.dev.tripcount.api.service

import neflo.dev.tripcount.api.model.authentication.GoogleLoginDTO
import neflo.dev.tripcount.api.model.authentication.LoginDTO
import neflo.dev.tripcount.api.model.authentication.LoginResponse
import neflo.dev.tripcount.api.model.user.UserDTO
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiAuthenticationService {

    @POST("auth/login")
    suspend fun login(@Body loginDTO: LoginDTO) : LoginResponse

    @POST("auth/google/login")
    suspend fun googleLogin(@Body googleLoginDTO: GoogleLoginDTO) : LoginResponse

    @POST("auth/signup")
    suspend fun signUp(@Body registerDTO: UserDTO) : LoginResponse

}