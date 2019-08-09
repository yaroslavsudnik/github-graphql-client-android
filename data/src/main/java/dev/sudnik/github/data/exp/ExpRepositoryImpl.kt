package dev.sudnik.github.data.exp

import dev.sudnik.basecleanandroid.domain.ErrorResponse
import dev.sudnik.basecleanandroid.domain.OnCallback
import dev.sudnik.github.domain.entity.ExpEntity
import dev.sudnik.github.domain.repository.ExpRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ExpRepositoryImpl : ExpRepository {

    private val expDataSource = ExpDataSource()
    private val expMapper = ExpMapper()

    override fun getExp(callback: OnCallback<ExpEntity>) {
        expDataSource.getExp(object : ExpDataSource.OnExpReadyCallback {
            override fun onExpReady(dto: ExpDTO) {
                GlobalScope.launch {
                    val exp = expMapper.transformToExp(dto)
                    withContext(Dispatchers.Main) {
                        callback.onSuccess(ExpEntity(exp))
                    }
                }
            }

            override fun onError(error: ErrorResponse) {
                GlobalScope.launch {
                    withContext(Dispatchers.Main) {
                        callback.onError(error)
                    }
                }
            }
        })
    }
}