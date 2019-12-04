package dev.sudnik.github.login

import android.app.Application
import android.net.Uri
import androidx.annotation.VisibleForTesting
import dev.sudnik.basecleanandroid.presentation.BaseReducer
import dev.sudnik.basecleanandroid.presentation.BaseViewModel

class LoginViewModel(application: Application) : BaseViewModel<LoginViewState>(application) {

    private lateinit var loginReducer: LoginReducer

    override fun instanceReducers(): ArrayList<BaseReducer<LoginViewState, out Any>> {
        loginReducer = LoginReducer()
        return arrayListOf(loginReducer)
    }

    fun saveToken(expectedToken: String) {
        println("expectedToken = $expectedToken")
    }

    fun onPageStartedCallback(url: String) {
        if (containsQuery(url, accessCodeParam)) {
            getQueryParameter(url, accessCodeParam)?.let {
                loginReducer.loadingToken(it)
            }
        }
    }

    @VisibleForTesting
    fun containsQuery(url: String, queryParameter: String) =
        Uri.parse(url).queryParameterNames.contains(queryParameter)

    @VisibleForTesting
    fun getQueryParameter(url: String, queryParameter: String) =
        Uri.parse(url).getQueryParameter(queryParameter)

    companion object {
        const val OAUTH_URL =
            "https://github.com/login/oauth/authorize?client_id=2c8e069939d4683529ca"
        const val accessCodeParam = "code"
    }
}
