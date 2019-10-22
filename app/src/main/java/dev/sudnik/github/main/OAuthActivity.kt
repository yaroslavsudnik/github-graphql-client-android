package dev.sudnik.github.main

import android.graphics.Bitmap
import android.os.Bundle
import android.util.Log
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import dev.sudnik.github.R
import kotlinx.android.synthetic.main.activity_oauth.*


class OAuthActivity : AppCompatActivity() {

    var TAG = "TOKENOID"

    var OAUTH_URL = "https://github.com/login/oauth/authorize"
    var OAUTH_ACCESS_TOKEN_URL = "https://github.com/login/oauth/access_token"

    var CLIENT_ID = "2c8e069939d4683529ca"
    var CLIENT_SECRET = "c854e816b5ad813fc7757ffbe0d5f6c8f54e64b6"
    var CALLBACK_URL = "http://localhost"

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_oauth)

        val url = "$OAUTH_URL?client_id=$CLIENT_ID"

        webview.settings.javaScriptEnabled = true
        webview.webViewClient = object : WebViewClient() {
            override fun onPageStarted(view: WebView, url: String, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
                val accessTokenFragment = "access_token="
                val accessCodeFragment = "code="

                // We hijack the GET request to extract the OAuth parameters

                if (url.contains(accessTokenFragment)) {
                    // the GET request contains directly the token
                    val accessToken = url.substring(url.indexOf(accessTokenFragment))
                    Log.d(TAG, "accessToken = $accessToken")
                } else if (url.contains(accessCodeFragment)) {
                    // the GET request contains an authorization code
                    val accessCode = url.substring(url.indexOf(accessCodeFragment))
                    Log.d(TAG, "accessCode = $accessCode")
                    val query = "client_id=$CLIENT_ID&client_secret=$CLIENT_SECRET&code=$accessCode"
                    view.postUrl(OAUTH_ACCESS_TOKEN_URL, query.toByteArray())
                }
            }
        }
        webview.loadUrl(url)
    }
}
