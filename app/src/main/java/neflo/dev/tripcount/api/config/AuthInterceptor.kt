package neflo.dev.tripcount.api.config

import jakarta.inject.Inject
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import neflo.dev.tripcount.api.model.authentication.LoginDTO
import neflo.dev.tripcount.api.model.authentication.LoginResponse
import neflo.dev.tripcount.api.service.AuthService
import neflo.dev.tripcount.util.BASE_URL
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.time.LocalDateTime
import java.time.ZoneOffset

class AuthInterceptor @Inject constructor(private val tokenManager: TokenManager): Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val token = runBlocking {
            tokenManager.getToken().first()
        }
        val tokenExpiration = runBlocking {
            tokenManager.getTokenExpiration().first()
        }
        val loginTimestamp = runBlocking {
            tokenManager.getLoginTimestamp().first()
        }

        val request = chain.request()
        if (request.header("Skip-Auth") == "true") {
            return chain.proceed(request)
        }

        if (LocalDateTime.now().toEpochSecond(ZoneOffset.UTC) - (loginTimestamp ?: 0L) >= (tokenExpiration ?: 0L)) {
            return runBlocking {
                val newToken = getNewToken(token)

                if (!newToken.isSuccessful || newToken.body() == null) {
                    tokenManager.deleteToken()
                }

                newToken.body()?.let {
                    tokenManager.saveToken(
                        it.token,
                        it.expiresOn
                    )
                }

                chain.proceed(request
                    .newBuilder()
                    .addHeader("Authorization", "Bearer $token")
                    .build())
            }
        }

        return chain.proceed(request
            .newBuilder()
            .addHeader("Authorization", "Bearer $token")
            .build())
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