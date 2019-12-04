package dev.sudnik.github.main

import android.webkit.WebView
import dev.sudnik.github.login.LoginActivity
import dev.sudnik.github.login.LoginViewModel
import io.mockk.*
import org.junit.Test

class LoginActivityTest {

    @Test
    fun onPageStartedCallbackAccessCodeTest() {
        val expectedAccessCode = "access_code_value"
        val expectedQueryParameter = LoginActivity.accessCodeFragment
        val expectedQuery =
            "${LoginActivity.QUERY}&${LoginActivity.accessCodeFragment}=$expectedAccessCode"
        val expectedUrl = "${LoginActivity.OAUTH_URL}&${LoginActivity.accessCodeFragment}"
        val expectedByteArray = expectedQuery.toByteArray()

        val mockOAuthActivity = spyk<LoginActivity>()
        val mockWebView = mockk<WebView> {
            every {
                postUrl(LoginActivity.OAUTH_ACCESS_TOKEN_URL, expectedByteArray)
            } just runs
        }

        every {
            mockOAuthActivity.containsQuery(expectedUrl, LoginActivity.accessTokenFragment)
        } returns false
        every {
            mockOAuthActivity.containsQuery(expectedUrl, expectedQueryParameter)
        } returns true
        every {
            mockOAuthActivity.getQueryParameter(expectedUrl, expectedQueryParameter)
        } returns expectedAccessCode

        mockOAuthActivity.onPageStartedCallback(expectedUrl, mockWebView)

        verify {
            mockWebView.postUrl(LoginActivity.OAUTH_ACCESS_TOKEN_URL, expectedByteArray)
        }
    }

    @Test
    fun onPageStartedCallbackAccessTokenTest() {
        val expectedToken = "access_token_value"
        val expectedQueryParameter = LoginActivity.accessTokenFragment
        val expectedUrl = "${LoginActivity.OAUTH_URL}&$expectedQueryParameter=$expectedToken"

        val mockOAuthActivity = spyk<LoginActivity>()
        val mockOAuthViewModel = mockk<LoginViewModel> {
            every {
                saveToken(expectedToken)
            } just runs
        }
        val mockWebView = mockk<WebView>()
        mockOAuthActivity.viewModel = mockOAuthViewModel

        every {
            mockOAuthActivity.containsQuery(expectedUrl, expectedQueryParameter)
        } returns true
        every {
            mockOAuthActivity.getQueryParameter(expectedUrl, expectedQueryParameter)
        } returns expectedToken

        mockOAuthActivity.onPageStartedCallback(expectedUrl, mockWebView)

        verify {
            mockOAuthViewModel.saveToken(expectedToken)
        }
    }
}
