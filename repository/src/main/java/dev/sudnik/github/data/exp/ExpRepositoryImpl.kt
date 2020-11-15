package dev.sudnik.github.data.exp

import dev.sudnik.basecleanandroid.domain.ErrorResponse
import dev.sudnik.basecleanandroid.domain.RepositoryResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ExpRepositoryImpl : ExpRepository {

    private val expDataSource = ExpDataSource(GITHUB_GRAPHQL_ENDPOINT)

    override fun getExp(response: RepositoryResponse<ExpEntity>) {
        expDataSource.getExp(object : ExpDataSource.OnExpReadyCallback {
            override fun onExpReady(dto: ExpDTO) {
                GlobalScope.launch {
                    withContext(Dispatchers.Main) {
                        response.onSuccess(ExpEntity(dto))
                    }
                }
            }

            override fun onError(error: ErrorResponse) {
                GlobalScope.launch {
                    withContext(Dispatchers.Main) {
                        response.onError(error)
                    }
                }
            }
        })
    }

    companion object {
        private const val GITHUB_GRAPHQL_ENDPOINT = "https://api.github.com/graphql"
    }
}
