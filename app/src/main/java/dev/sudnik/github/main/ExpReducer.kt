package dev.sudnik.github.main

import dev.sudnik.basecleanandroid.presentation.BaseReducer
import dev.sudnik.github.domain.interactor.ExpInteractor
import dev.sudnik.github.domain.state.ExpDataState

class ExpReducer : BaseReducer<MainViewState, ExpDataState>() {

    override fun instanceInteractor() = ExpInteractor()

    override fun processDataState(data: ExpDataState): MainViewState = when (data) {
        is ExpDataState.ExpLoaded -> MainViewState.ShowI(data.i)
        else -> super.processDataState(data)
    }

    override fun processErrorState(error: ExpDataState): MainViewState = when (error) {
        is ExpDataState.OnError -> MainViewState.OnError(error.message)
        else -> super.processErrorState(error)
    }

    override fun unknownError(): MainViewState = MainViewState.OnError("author name loading error")

    fun getExp() = (interactor as ExpInteractor).getExp()
}
