package neflo.dev.tripcount.api.config

import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import neflo.dev.tripcount.api.model.authentication.LoginDTO
import neflo.dev.tripcount.api.model.authentication.LoginResponse
import neflo.dev.tripcount.api.service.AuthService
import neflo.dev.tripcount.util.BASE_URL
import okhttp3.Authenticator
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

class AuthAuthenticator @Inject constructor(private val tokenManager: TokenManager): Authenticator {

    override fun authenticate(route: Route?, response: Response): Request? {
        val token = runBlocking {
            tokenManager.getToken().first()
        }

        return runBlocking {
            val newToken = getNewToken(token)

            if (!newToken.isSuccessful || newToken.body() == null) {
                tokenManager.deleteToken()
            }

            newToken.body()?.let {
                tokenManager.saveToken(it.token)
                response.request.newBuilder()
                    .header("Authorization", "Bearer ${it.token}")
                    .build()
            }
        }
    }

    private suspend fun getNewToken(refreshToken: String?): retrofit2.Response<LoginResponse> {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        val okHttpClient = OkHttpClient.Builder().addInterceptor(loggingInterceptor).build()

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()

        val service = retrofit.create(AuthService::class.java)
        return service.login(LoginDTO(tokenManager.getEmail().first() ?: "", tokenManager.getPassword().first() ?: ""))
    }

}