package neflo.dev.tripcount.api

import neflo.dev.tripcount.api.service.ApiAuthenticationService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiInstance {

    private const val BASE_URL = "http://192.168.1.224:8080/"

    val authenticationApi: ApiAuthenticationService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiAuthenticationService::class.java)
    }

}