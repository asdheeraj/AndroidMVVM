package com.dheeraj.samplemvvm.network

import com.dheeraj.samplemvvm.network.exception.RetrofitException
import okhttp3.Request
import retrofit2.*
import java.io.IOException
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type
import java.util.concurrent.Executor

/**
 *  Custom [CallAdapter] for handling non 200 status code resulting in [Callback.onFailure] being called
 *  and wrap it in [RetrofitException]
 */
class ErrorHandlingExecutorCallAdapterFactory(private val callbackExecutor: Executor) : CallAdapter.Factory() {

    override fun get(returnType: Type, annotations: Array<out Annotation>, retrofit: Retrofit): CallAdapter<*, *>? {

        val responseType = getCallResponseType(returnType)
        return object : CallAdapter<Any, Any> {

            override fun adapt(call: Call<Any>): Any = ExecutorCallbackCall(callbackExecutor, call, retrofit)

            override fun responseType(): Type = responseType
        }
    }

    class ExecutorCallbackCall<T>(
        private val callbackExecutor: Executor,
        private val delegate: Call<T>,
        private val retrofit: Retrofit
    ) : Call<T> {

        override fun enqueue(callback: Callback<T>) {
            delegate.enqueue(
                ErrorHandlingExecutorCallback(
                    callbackExecutor = callbackExecutor,
                    callback = callback, executorCallBack = this, retrofit = retrofit
                )
            )
        }

        override fun isExecuted(): Boolean = delegate.isExecuted

        override fun clone(): Call<T> = ExecutorCallbackCall(callbackExecutor, delegate.clone(), retrofit)

        override fun isCanceled(): Boolean = delegate.isCanceled

        override fun cancel() = delegate.cancel()

        //todo : replace with execute in line 64 and execute with callback.
        override fun execute(): Response<T> = delegate.execute()

        override fun request(): Request = delegate.request()

        /**
         * This method is used when you what to get data serially but at the same time use same callback
         * as in any other request and get proper error handled in the failure block
         */
        fun execute(callback: Callback<T>) {
            try {
                val response: Response<T> = delegate.execute()
                if (response.isSuccessful) {
                    callback.onResponse(this, response)
                } else {
                    val exception =
                        RetrofitException.httpError(response.raw().request().url().toString(), response, retrofit)
                    callback.onFailure(this, exception)
                }
            } catch (e: IOException) {
                callback.onFailure(this, RetrofitException.networkError(e))
            }
        }
    }

    internal class ErrorHandlingExecutorCallback<T>(
        private val callbackExecutor: Executor,
        private val callback: Callback<T>?,
        private val executorCallBack: ExecutorCallbackCall<T>,
        private val retrofit: Retrofit
    ) : Callback<T> {

        override fun onFailure(call: Call<T>, t: Throwable) {
            val exception = when (t) {
                is IOException -> {
                    RetrofitException.networkError(t)
                }
                else -> {
                    RetrofitException.unexpectedError(t)
                }
            }
            callbackExecutor.execute {
                callback?.onFailure(executorCallBack, exception)
            }
        }

        override fun onResponse(call: Call<T>, response: Response<T>) {
            if (response.isSuccessful) {
                callbackExecutor.execute {
                    callback?.onResponse(executorCallBack, response)
                }
            } else {
                callbackExecutor.execute {
                    val exception =
                        RetrofitException.httpError(response.raw().request().url().toString(), response, retrofit)
                    callback?.onFailure(executorCallBack, exception)
                }
            }
        }
    }

    companion object {
        fun create() = ErrorHandlingExecutorCallAdapterFactory(Executor{})
        fun getCallResponseType(returnType: Type): Type {
            if ((returnType !is ParameterizedType)) {
                throw  IllegalArgumentException(
                    "Call return type must be parameterized as Call<Foo> or Call<? extends Foo>"
                )
            }
            return getParameterUpperBound(0, returnType)
        }
    }
}