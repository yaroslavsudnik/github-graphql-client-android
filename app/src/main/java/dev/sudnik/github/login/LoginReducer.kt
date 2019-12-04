package dev.sudnik.github.login

import dev.sudnik.basecleanandroid.presentation.BaseReducer
import dev.sudnik.github.data.authentication.AuthenticationRepositoryImpl
import dev.sudnik.github.domain.interactor.AuthenticationInteractor
import dev.sudnik.github.domain.state.AuthenticationDataState

class LoginReducer : BaseReducer<LoginViewState, AuthenticationDataState>() {
    override fun instanceInteractor() = AuthenticationInteractor(AuthenticationRepositoryImpl())

    override fun processDataState(data: AuthenticationDataState): LoginViewState = when (data) {
        is AuthenticationDataState.AuthenticationLoaded ->
            LoginViewState.ShowSuccessMessage("token is added")
        else -> super.processDataState(data)
    }

    override fun processErrorState(error: AuthenticationDataState): LoginViewState = when (error) {
        is AuthenticationDataState.OnError -> LoginViewState.OnError(error.message)
        else -> super.processErrorState(error)
    }

    override fun unknownError(): LoginViewState = LoginViewState.OnError("token getting error")

    fun loadingToken(code: String) = (interactor as AuthenticationInteractor).callToken(code)
}
