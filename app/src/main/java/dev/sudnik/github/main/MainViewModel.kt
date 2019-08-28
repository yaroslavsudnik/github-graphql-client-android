package dev.sudnik.github.main

import android.app.Application
import dev.sudnik.basecleanandroid.presentation.BaseReducer
import dev.sudnik.basecleanandroid.presentation.BaseViewModel
import dev.sudnik.github.data.exp.ExpRepositoryImpl
import dev.sudnik.github.domain.interactor.ExpInteractor

class MainViewModel(application: Application) : BaseViewModel<MainViewState>(application) {

    private lateinit var expReducer: ExpReducer

    override fun instanceReducers(): ArrayList<BaseReducer<MainViewState, out Any>> {
        expReducer = ExpReducer(ExpInteractor(ExpRepositoryImpl()))
        return arrayListOf(expReducer)
    }

    override fun unknownError(): MainViewState = MainViewState.OnError("unknown error")

    fun getExp() = expReducer.getExp()
}
