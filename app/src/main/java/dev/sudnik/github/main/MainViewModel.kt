package dev.sudnik.github.main

import android.app.Application
import dev.sudnik.basecleanandroid.presentation.BaseReducer
import dev.sudnik.basecleanandroid.presentation.BaseViewModel

class MainViewModel(application: Application) : BaseViewModel<MainViewState>(application) {

    override fun instanceReducers(): ArrayList<BaseReducer<MainViewState, out Any>> {
        return arrayListOf()
    }

}
