package dev.sudnik.github.main

import android.app.Application
import dev.sudnik.basecleanandroid.presentation.BaseReducer
import dev.sudnik.basecleanandroid.presentation.BaseViewModel

class MainViewModel(application: Application) : BaseViewModel<MainViewState>(application) {

    private lateinit var expReducer: ExpReducer

    override fun instanceReducers(): ArrayList<BaseReducer<MainViewState, out Any>> {
        expReducer = ExpReducer()
        return arrayListOf(expReducer)
    }

    fun getExp() = expReducer.getExp()
}
