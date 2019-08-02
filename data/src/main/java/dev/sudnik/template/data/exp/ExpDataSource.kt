package dev.sudnik.template.data.exp

import dev.sudnik.basecleanandroid.domain.ErrorResponse
import kotlinx.coroutines.*

class ExpDataSource {
    private var viewModelJob = Job()
    private val viewModelScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    fun getExp(callback: OnExpReadyCallback) {
        viewModelScope.launch {
            repeat(2) {
                delay(1000L)
            }
            callback.onExpReady(ExpDTO(0))
        }
    }

    interface OnExpReadyCallback {
        fun onExpReady(dto: ExpDTO)
        fun onError(error: ErrorResponse)
    }
}