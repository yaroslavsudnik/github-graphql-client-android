package dev.sudnik.github.domain.repository

import dev.sudnik.basecleanandroid.domain.RepositoryResponse
import dev.sudnik.github.domain.entity.AuthenticationEntity

interface AuthenticationRepository {
    suspend fun loadToken(code: String, response: RepositoryResponse<AuthenticationEntity>)
}
