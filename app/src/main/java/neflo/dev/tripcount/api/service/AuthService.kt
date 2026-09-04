package neflo.dev.tripcount.api.service

import neflo.dev.tripcount.api.model.authentication.GoogleLoginDTO
import neflo.dev.tripcount.api.model.authentication.LoginDTO
import neflo.dev.tripcount.api.model.authentication.LoginResponse
import neflo.dev.tripcount.api.model.user.UserDTO
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface AuthService {

    @POST("auth/login")
    suspend fun login(@Body loginDTO: LoginDTO) : Response<LoginResponse>

    @GET("auth/refresh")
    suspend fun refresh(@Header("Authorization") token: String) : Response<LoginResponse>

    @POST("auth/google/login")
    suspend fun googleLogin(@Body googleLoginDTO: GoogleLoginDTO) : Response<LoginResponse>

    @POST("auth/signup")
    suspend fun signUp(@Body registerDTO: UserDTO) : Response<LoginResponse>

}