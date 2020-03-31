package com.dheeraj.samplemvvm.network

import com.dheeraj.samplemvvm.BuildConfig
import com.dheeraj.samplemvvm.network.environment.EnvironmentManager
import com.dheeraj.samplemvvm.network.interceptors.APIKeyInterceptor
import com.dheeraj.samplemvvm.network.interceptors.AuthenticatorInterceptor
import com.dheeraj.samplemvvm.network.interceptors.GeneralInterceptor
import com.google.gson.ExclusionStrategy
import com.google.gson.FieldAttributes
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 *
 * Network module Dependencies. All dependencies are scoped under a specific name so that client
 * applications can have same dependency (with different scope).
 *
 */

const val RETROFIT = "RETROFIT"
private const val HTTP_CLIENT = "HTTP_CLIENT"
private const val HTTP_LOGGING = "HTTP_LOGGING"
private const val API_INTERCEPTOR = "API_INTERCEPTOR"
private const val AUTH_INTERCEPTOR = "AUTH_INTERCEPTOR"
private const val GENERAL_INTERCEPTOR = "GENERAL_INTERCEPTOR"
const val GSON = "GSON"

val networkModule = module {
    single(named(RETROFIT)) {
        provideRetrofit(get(), get(named(HTTP_CLIENT)), get(named(GSON)))
    }

    factory(named(HTTP_LOGGING)) { providesHttplogging() }

    factory(named(HTTP_CLIENT)) {
        providesOkHttpClient(
            get(named(HTTP_LOGGING)), get(named(GENERAL_INTERCEPTOR)),
            get(named(API_INTERCEPTOR)), get(named(AUTH_INTERCEPTOR))
        )
    }

    factory(named(API_INTERCEPTOR)) { APIKeyInterceptor() }

    factory(named(AUTH_INTERCEPTOR)) { AuthenticatorInterceptor() }

    factory(named(GENERAL_INTERCEPTOR)) { GeneralInterceptor() }

    single(named(GSON)) { provideGson() }
}

/**
 *
 * Provides Retrofit dependency.
 *
 * @param environment EnvironmentManager
 * @param okHttpClient OkHttpClient
 * @param gson Gson
 *
 */
fun provideRetrofit(
    environment: EnvironmentManager, okHttpClient: OkHttpClient,
    gson: Gson
): Retrofit {
    return Retrofit.Builder().baseUrl(environment.baseUrl()).client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create(gson)).build()
}

/**
 * Provides OkHttpClient dependency.
 *
 * @param httpLoggingInterceptor Http Logging Interceptor
 * @param apiKeyInterceptor ApiKeyInterceptor
 * @param authenticatorInterceptor AuthenticationInterceptor
 *
 */
fun providesOkHttpClient(
    httpLoggingInterceptor: HttpLoggingInterceptor,
    statusInterceptor: GeneralInterceptor,
    apiKeyInterceptor: APIKeyInterceptor,
    authenticatorInterceptor: AuthenticatorInterceptor
): OkHttpClient {
    return OkHttpClient.Builder().connectTimeout(60L, TimeUnit.SECONDS)
        .readTimeout(60L, TimeUnit.SECONDS).addInterceptor(httpLoggingInterceptor)
       // .addInterceptor(statusInterceptor)
       //.addInterceptor(apiKeyInterceptor)
       // .addInterceptor(authenticatorInterceptor)
        .build()
}

/**
 * Provides HttpLoggingInterceptor dependency.
 */
fun providesHttplogging(): HttpLoggingInterceptor {
    val interceptor = HttpLoggingInterceptor()
    interceptor.level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY
    else HttpLoggingInterceptor.Level.NONE
    return interceptor
}

/**
 * Provides Gson dependency.
 */
fun provideGson(): Gson {
    val strategy = object : ExclusionStrategy {
        override fun shouldSkipField(field: FieldAttributes): Boolean {
            // Some models will have column 'localId' to maintain offline record which shouldn't
            // be sent to server.
            return field.name.equals("localId", true)
        }

        override fun shouldSkipClass(clazz: Class<*>): Boolean {
            return false
        }
    }
    return GsonBuilder().addSerializationExclusionStrategy(strategy).create()
}

/**
 * Create an implementation of the API endpoints defined by the service interface.
 */
inline fun <reified T> createWebService(retrofit: Retrofit): T = retrofit.create(T::class.java)