package neflo.dev.tripcount.api.config

import jakarta.inject.Inject
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor @Inject constructor(private val tokenManager: TokenManager): Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val token = runBlocking {
            tokenManager.getToken().first()
        }

        val request = chain.request()
        if (request.header("Skip-Auth") == "true") {
            return chain.proceed(request)
        }

        return chain.proceed(request
            .newBuilder()
            .addHeader("Authorization", "Bearer $token")
            .build())
    }

}