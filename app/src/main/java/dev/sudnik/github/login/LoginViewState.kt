package dev.sudnik.github.login

sealed class LoginViewState {
    class ShowSuccessMessage(val message: String) : LoginViewState() {
        companion object {
            fun create(): ShowSuccessMessage =
                ShowSuccessMessage("text")
        }
    }

    class OnError(message: String) : LoginViewState()
}
