package dev.sudnik.github.data

import dev.sudnik.github.data.authentication.AuthenticationDTO
import retrofit2.Response
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Query

interface IApi {
    @Headers("Accept: application/json", "Content-Type: application/json")
    @POST("/login/oauth/access_token")
    suspend fun accessToken(
        @Query("client_id") clientId: String,
        @Query("client_secret") clientSecret: String,
        @Query("code") code: String
    ): Response<AuthenticationDTO>
}
