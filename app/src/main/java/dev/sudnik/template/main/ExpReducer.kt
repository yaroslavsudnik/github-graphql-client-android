package dev.sudnik.template.main

import dev.sudnik.basecleanandroid.presentation.BaseReducer
import dev.sudnik.template.domain.interactor.ExpInteractor
import dev.sudnik.template.domain.state.ExpDataState

class ExpReducer(private val interactor: ExpInteractor) :
        BaseReducer<MainViewState, ExpDataState>(interactor) {

    override fun processDataState(data: ExpDataState): MainViewState = when (data) {
        is ExpDataState.ExpLoaded -> MainViewState.ShowI(data.i)
        else -> super.processDataState(data)
    }

    override fun processErrorState(error: ExpDataState): MainViewState = when (error) {
        is ExpDataState.OnError -> MainViewState.OnError(error.message)
        else -> super.processErrorState(error)
    }

    override fun unknownError(): MainViewState = MainViewState.OnError("author name loading error")

    fun getExp() = interactor.getExp()
}
