package dev.sudnik.github.data.authentication

import dev.sudnik.basecleanandroid.domain.RepositoryResponse
import dev.sudnik.github.domain.entity.AuthenticationEntity
import dev.sudnik.github.domain.repository.AuthenticationRepository

class AuthenticationRepositoryImpl : AuthenticationRepository {

    override suspend fun loadToken(
        code: String,
        response: RepositoryResponse<AuthenticationEntity>
    ) {
//        if (true) {
        val dataSource = AuthenticationDataSource(response)
        dataSource.getToken(code)
//        } else {
//            TODO("load token from data storage")
//        }
    }
}
