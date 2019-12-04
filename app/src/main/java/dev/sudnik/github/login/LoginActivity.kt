package dev.sudnik.github.login

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.annotation.VisibleForTesting
import dev.sudnik.basecleanandroid.presentation.BaseActivity
import dev.sudnik.github.R
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : BaseActivity<LoginViewState, LoginViewModel>() {
    override fun getLayoutId() = R.layout.activity_login

    @VisibleForTesting
    override fun instanceViewModel() = LoginViewModel(application)

    @SuppressLint("SetJavaScriptEnabled")
    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        webview.settings.javaScriptEnabled = true
        webview.webViewClient = object : WebViewClient() {
            override fun onPageStarted(view: WebView, url: String, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
                baseViewModel.onPageStartedCallback(url)
            }
        }
        webview.loadUrl(LoginViewModel.OAUTH_URL)
    }

    override fun processDataState(dataState: LoginViewState) = when (dataState) {
        is LoginViewState.ShowSuccessMessage -> hideWebView()
        else -> super.processDataErrorState(dataState)
    }

    private fun hideWebView() {
        webview.visibility = View.GONE
    }

    override fun showLoading() {
        println("loading")
    }

    override fun hideLoading() {
        println("loaded")
    }
}
