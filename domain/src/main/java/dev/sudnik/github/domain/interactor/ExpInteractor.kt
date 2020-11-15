package dev.sudnik.github.domain.interactor

import dev.sudnik.basecleanandroid.domain.BaseInteractor
import dev.sudnik.basecleanandroid.domain.ErrorResponse
import dev.sudnik.github.data.exp.ExpEntity
import dev.sudnik.github.data.exp.ExpRepository
import dev.sudnik.github.data.exp.ExpRepositoryImpl
import dev.sudnik.github.domain.state.ExpDataState

class ExpInteractor : BaseInteractor<ExpDataState, ExpEntity>() {

    private val repository: ExpRepository = ExpRepositoryImpl()

    fun getExp() = call(ExpDataState.ExpLoaded.create()) { repository.getExp(it) }

    override fun processSuccessState(clazz: ExpDataState, data: ExpEntity): ExpDataState = when (clazz) {
        is ExpDataState.ExpLoaded -> ExpDataState.ExpLoaded(data.getExpValue())
        else -> throw IllegalArgumentException(
            "State object $clazz has not been determined"
        )
    }

    override fun processErrorState(error: ErrorResponse): ExpDataState = when (error.errorCode) {
        403 -> ExpDataState.ExpError
        else -> ExpDataState.OnError(error.message)
    }
}