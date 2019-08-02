package dev.sudnik.template.domain.interactor

import dev.sudnik.basecleanandroid.domain.BaseInteractor
import dev.sudnik.basecleanandroid.domain.ErrorResponse
import dev.sudnik.template.domain.entity.ExpEntity
import dev.sudnik.template.domain.repository.ExpRepository
import dev.sudnik.template.domain.state.ExpDataState

class ExpInteractor(private val repository: ExpRepository) : BaseInteractor<ExpDataState, ExpEntity>() {

    fun getExp() = call(ExpDataState.ExpLoaded.create()) { repository.getExp(it) }

    override fun processSuccessState(clazz: ExpDataState, data: ExpEntity):
            ExpDataState = when (clazz) {
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