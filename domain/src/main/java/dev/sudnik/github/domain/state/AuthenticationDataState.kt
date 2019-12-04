package dev.sudnik.github.domain.state

sealed class AuthenticationDataState {
    object AuthenticationLoaded : AuthenticationDataState()
    class OnError(val message: String) : AuthenticationDataState()
}
