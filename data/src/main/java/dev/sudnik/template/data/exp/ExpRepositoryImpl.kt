package dev.sudnik.template.data.exp

import dev.sudnik.basecleanandroid.domain.ErrorResponse
import dev.sudnik.basecleanandroid.domain.OnCallback
import dev.sudnik.template.domain.entity.ExpEntity
import dev.sudnik.template.domain.repository.ExpRepository

class ExpRepositoryImpl : ExpRepository {

    private val expDataSource = ExpDataSource()
    private val expMapper = ExpMapper()

    override fun getExp(callback: OnCallback<ExpEntity>) {
        expDataSource.getExp(object : ExpDataSource.OnExpReadyCallback {
            override fun onExpReady(dto: ExpDTO) {
                val exp = expMapper.transformToExp(dto)
                callback.onSuccess(ExpEntity(exp))
            }

            override fun onError(error: ErrorResponse) {
                callback.onError(error)
            }
        })
    }
}