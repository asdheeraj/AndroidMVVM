package com.dheeraj.samplemvvm.network.interceptors

import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.Response
import java.net.HttpURLConnection


/**
 *  [Interceptor] for adding Authorization header
 *  includes Refreshing access token with refresh token
 */
class AuthenticatorInterceptor() : Interceptor {

    companion object {
        private const val AUTHORIZATION_TOKEN = "Authorization"
        private const val URL_REGISTRATION = "Registration"
        private const val MAX_RETRIES_ALLOWED = 3
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val builder = original.newBuilder()
        // 1. Sign the request with AccessToken
        if (isAuthHeaderRequired(original.url().toString())) {
            val accessToken = "accessToken" //TODO To be modified
            accessToken?.let {
                builder.addHeader(AUTHORIZATION_TOKEN, it).build()
            }
        }
        builder.method(original.method(), original.body())
        val modifiedRequest = builder.build()

        // 2. Proceed with the request
        val response = chain.proceed(modifiedRequest)
        // 3. Check the response: have we got a 401?
        if (response.code() == HttpURLConnection.HTTP_UNAUTHORIZED && isAuthHeaderRequired(original.url().toString())) {
            if (responseCount(response) >= MAX_RETRIES_ALLOWED) {
                // If both the original call and the call with refreshed token failed,
                // it will probably keep failing, so don't try again.
                return response
            }
            fetchAndUpdateAccessToken()

            val newBuilder = original.newBuilder()
            val newAccessToken = "accessToken" //TODO To be modified
            if (newAccessToken.isNullOrEmpty()) {
                return response
            }
            with(newBuilder) {
                // Set AccessToken in header and retry the request again
                addHeader(AUTHORIZATION_TOKEN, newAccessToken).build()
                // Disable cache and force Retrofit to make network
                // call (ONLY for this retry request).
                cacheControl(CacheControl.FORCE_NETWORK)
                method(original.method(), original.body())
            }
            return chain.proceed(newBuilder.build())
        }
        return response
    }

    /**
     * Returns if Authorisation is required
     * @param url the url for which the authorisation header needs to be checked
     */
    private fun isAuthHeaderRequired(url: String): Boolean {
        return !(url.contains(URL_REGISTRATION))
    }

    /**
     * This method uses RefreshToken to get AccessToken synchronously using AWSMobileClient
     */
    private fun fetchAndUpdateAccessToken() {
        "accessToken" //TODO To be modified
    }

    /**
     * Check the number of attempts done for refreshing token
     *
     * @param response: Http response obtained from API call
     * @return number of attempts
     */
    private fun responseCount(response: Response): Int {
        var result = 1
        while ((response == response.priorResponse())) {
            result++
        }
        return result
    }

}