package dev.sudnik.template.main

sealed class MainViewState {
    class ShowI(val i: String) : MainViewState()
    class OnError(val message: String) : MainViewState()
}
