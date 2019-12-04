package dev.sudnik.github.data.authentication

import com.squareup.moshi.Json

data class AuthenticationDTO(
    @field:Json(name = "access_token")
    val accessToken: String,
    @field:Json(name = "token_type")
    val tokenType: String,
    val scope: String = "unknown"
)
