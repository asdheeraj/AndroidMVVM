package com.dheeraj.samplemvvm.network.interceptors

import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

/**
 * [Interceptor] for adding API key
 */

class APIKeyInterceptor : Interceptor {

    companion object {
        private const val X_GRANT_TYPE = "X-GrantType"
        private const val X_API_KEY = "x-APIKey"
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val newRequest = chain.request().newBuilder()
                //Add API keys here
                .build()
        return chain.proceed(newRequest)
    }
}

/**
 * [Interceptor] for NO data in response
 */
class GeneralInterceptor : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {

        val originalRequest = chain.request()
        val requestBuilder = originalRequest.newBuilder()
        val response = chain.proceed(requestBuilder.build())

        // Throw specific Exception on HTTP 204 response code
        if (response.code() == 204) {
            throw NoContentException("There is no content")
        } else if(response.code() == 304) {
            throw NotModifiedException("There is no change in response")
        }

        return response // Carry on with the response
    }
}

class NoContentException(message: String) : Exception(message)

class NotModifiedException(message: String) : Exception(message)