package dev.sudnik.github.data.exp

import com.apollographql.apollo.ApolloCall
import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.exception.ApolloException
import dev.sudnik.basecleanandroid.domain.ErrorResponse
import dev.sudnik.github.data.TestQuery
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import java.util.concurrent.TimeUnit

class ExpDataSource {
    private var viewModelJob = Job()
    private val viewModelScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    fun getExp(callback: OnExpReadyCallback) {
        viewModelScope.launch {
            testQuery(callback)
        }
    }

    private fun testQuery(callback: OnExpReadyCallback) {
        val request = TestQuery
                .builder()
                .owner("octocat")
                .name("Hello-World")
                .build()
        apolloClient.query(request).enqueue(object : ApolloCall.Callback<TestQuery.Data>() {
            override fun onFailure(e: ApolloException) {
                callback.onError(ErrorResponse(e.message.orEmpty(), 500))
            }

            override fun onResponse(response: com.apollographql.apollo.api.Response<TestQuery.Data>) {
                when (response.hasErrors()) {
                    true -> callback.onError(
                            ErrorResponse(response.errors()[0].message()!!, 0))
                    false -> callback.onExpReady(
                            ExpDTO(response.data()?.repository()?.forkCount()!!))
                }
            }
        })
    }

    companion object {

        private const val GITHUB_GRAPHQL_ENDPOINT = "https://api.github.com/graphql"

        private class NetworkInterceptor : Interceptor {

            override fun intercept(chain: Interceptor.Chain?): Response {
                return chain!!.proceed(
                        chain.request().newBuilder().header(
                                "Authorization",
                                "Bearer fd37082aceffcaa81fdb334ee44697471338c21d"
                        ).build()
                )
            }
        }

        private val httpClient: OkHttpClient by lazy {
            OkHttpClient.Builder()
                .writeTimeout(15, TimeUnit.SECONDS)
                .readTimeout(15, TimeUnit.SECONDS)
                .addNetworkInterceptor(NetworkInterceptor())
                .build()
        }

        private val apolloClient: ApolloClient by lazy {
            ApolloClient.builder()
                .serverUrl(GITHUB_GRAPHQL_ENDPOINT)
                .okHttpClient(httpClient)
                .build()
        }

    }

    interface OnExpReadyCallback {
        fun onExpReady(dto: ExpDTO)
        fun onError(error: ErrorResponse)
    }
}