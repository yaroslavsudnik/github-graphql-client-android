package dev.sudnik.github.domain.state

sealed class ExpDataState {
    class ExpLoaded(val i: String) : ExpDataState() {
        companion object {
            fun create(): ExpLoaded = ExpLoaded("text")
        }
    }

    object ExpError : ExpDataState()
    class OnError(val message: String) : ExpDataState()
}
