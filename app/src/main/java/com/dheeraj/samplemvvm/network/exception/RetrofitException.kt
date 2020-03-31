package com.dheeraj.samplemvvm.network.exception

import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Response
import retrofit2.Retrofit
import java.io.IOException

/**
 *  Holder class for wrapping exceptions from Network layer, extends the [RuntimeException]
 *  @param message the message of Exception
 *  @param code the exception code
 *  @param url the url for which the exception occurred
 *  @param response the respons for the URL
 *  @param kind the kind of exception
 *  @param exception the throwable exception
 *  @param retrofit the Retrofit instance
 */
class RetrofitException(message: String?, val errorBody: String?, val code: Int?, val url: String?,
                        private val response: Response<*>?, val kind: Kind, exception: Throwable?,
                        private val retrofit: Retrofit?) : RuntimeException(message, exception) {

    companion object {
        /**
         * returns the [RetrofitException] by taking the parameters
         * @param url the value of the url
         * @param response the type of response
         * @param retrofit the Retrofit response
         */
        fun httpError(url: String, response: Response<*>, retrofit: Retrofit): RetrofitException {
            val message = response.code().toString() + " " + response.message()
            val error =
                RetrofitException(message, response.errorBody()?.string(), response.code(), url,
                    response, Kind.HTTP, null, retrofit)
            //error.deserializeServerError()
            return error
        }

        /**
         * returns the [RetrofitException] by taking the parameters
         * @param exception the IOException received
         */
        fun networkError(exception: IOException): RetrofitException {
            return RetrofitException(exception.message, null, null, null, null, Kind.NETWORK,
                exception, null)
        }

        /**
         * returns the [RetrofitException] by taking the parameters
         * @param exception the Throwable received
         */
        fun unexpectedError(exception: Throwable): RetrofitException {
            return RetrofitException(exception.message, null, null, null, null, Kind.UNEXPECTED,
                exception, null)
        }
    }

    /** The event kind which triggered this error. */
    fun getErrorMessage() = ""

    /**
     * HTTP response body converted to specified `type`. `null` if there is no
     * response.

     * @throws IOException if unable to convert the body to the specified `type`.
     */
    @Throws(IOException::class) private fun <T> getErrorBodyAs(type: Class<T>): T? {
        val errorBody = response?.errorBody()
        if (errorBody == null || retrofit == null) {
            return null
        }
        return try {
            val converter: Converter<ResponseBody, T> =
                retrofit.responseBodyConverter(type, arrayOfNulls<Annotation>(0))
            converter.convert(errorBody)
        } catch (e: IllegalArgumentException) {
            null
        }
    }

    enum class Kind {
        /** An [IOException] occurred while communicating to the server.  */
        NETWORK,
        /** A non-200 HTTP type code was received from the server.  */
        HTTP,
        /**
         * An internal error occurred while attempting to execute a request. It is best practice to
         * re-throw this exception so your application crashes.
         */
        UNEXPECTED
    }

    /**
     * Error class contains error messages from server.
     *
     * @property errors list of server errors
     */
    data class HttpError(val errors: List<Error>) {
        data class Error(val description: String, val errorCode: String, val errorLogRef: String)
    }
}