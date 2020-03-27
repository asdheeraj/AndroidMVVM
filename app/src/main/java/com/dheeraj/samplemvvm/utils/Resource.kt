package com.dheeraj.samplemvvm.utils

import com.dheeraj.samplemvvm.utils.Status.ERROR
import com.dheeraj.samplemvvm.utils.Status.LOADING
import com.dheeraj.samplemvvm.utils.Status.SUCCESS

/**
 * A data class to communicate the status of the network call to the UI
 * @param T the type of the data from the API call
 * @property status the status of the API call
 * @property data the data from the API call
 * @property message An Error message to communicate to the user in case of API failure
 */

data class Resource<out T>(val status: Status, val data: T?, val message: String?) {
    companion object {
        fun <T> success(data: T): Resource<T> = Resource(status = SUCCESS, data = data, message = null)

        fun <T> error(data: T?, message: String): Resource<T> =
            Resource(status = ERROR, data = data, message = message)

        fun <T> loading(data: T?): Resource<T> = Resource(status = LOADING, data = data, message = null)
    }
}