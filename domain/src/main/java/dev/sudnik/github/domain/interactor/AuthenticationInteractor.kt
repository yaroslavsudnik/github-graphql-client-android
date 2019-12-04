package dev.sudnik.github.domain.interactor

import dev.sudnik.basecleanandroid.domain.BaseInteractor
import dev.sudnik.basecleanandroid.domain.ErrorResponse
import dev.sudnik.github.domain.entity.AuthenticationEntity
import dev.sudnik.github.domain.repository.AuthenticationRepository
import dev.sudnik.github.domain.state.AuthenticationDataState

class AuthenticationInteractor(private val repository: AuthenticationRepository) :
        BaseInteractor<AuthenticationDataState, AuthenticationEntity>() {

    fun callToken(code: String) = call(AuthenticationDataState.AuthenticationLoaded) {
        repository.loadToken(code, it)
    }

    override fun processSuccessState(clazz: AuthenticationDataState, data: AuthenticationEntity)
            : AuthenticationDataState = when (clazz) {
        is AuthenticationDataState.AuthenticationLoaded ->
            AuthenticationDataState.AuthenticationLoaded
        else -> throw IllegalArgumentException(
                "State object $clazz has not been determined"
        )
    }

    override fun processErrorState(error: ErrorResponse): AuthenticationDataState =
            AuthenticationDataState.OnError(error.message)
}
