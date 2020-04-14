package com.dheeraj.samplemvvm.extensions

import android.content.Context
import android.content.SharedPreferences

private const val PREF_KEY_ENCRYPTION_IV = "encryption_iv"
private const val PREFERENCES_NAME = "pref_cph"

/**
 *  Extension method to remove boilerplate code to save to [SharedPreferences]
 */
inline fun <reified T> SharedPreferences.get(key: String, defaultValue: T): T {
    when(T::class) {
        Boolean::class -> return this.getBoolean(key, defaultValue as Boolean) as T
        Float::class -> return this.getFloat(key, defaultValue as Float) as T
        Int::class -> return this.getInt(key, defaultValue as Int) as T
        Long::class -> return this.getLong(key, defaultValue as Long) as T
        String::class -> return this.getString(key, defaultValue as String) as T
    }

    return defaultValue
}

/**
 * Extension method to insert value into [SharedPreferences]
 * @param key the key value of the attribute
 * @param value the generic value that has to be inserted
 */
inline fun <reified T> SharedPreferences.put(key: String, value: T) {
    val editor = this.edit()

    when(T::class) {
        Boolean::class -> editor.putBoolean(key, value as Boolean)
        Float::class -> editor.putFloat(key, value as Float)
        Int::class -> editor.putInt(key, value as Int)
        Long::class -> editor.putLong(key, value as Long)
        String::class -> editor.putString(key, value as String)
    }
    editor.apply()
}

/**
 * Clears the [SharedPreferences]
 */
fun SharedPreferences.clear() {
    edit().clear().apply()
}

/**
 *  Extension method to remove boilerplate code to save to [SharedPreferences]
 */
inline fun SharedPreferences.edit(body: SharedPreferences.Editor.() -> Unit) {
    val editor: SharedPreferences.Editor = edit()
    editor.body()
    editor.apply()
}

/**
 * Returns the [SharedPreferences] instance for the given context
 * @param context the context for which the [SharedPreferences] instance is to be returned
 */
fun Context.getPreferences() = this.getSharedPreferences(PREFERENCES_NAME,
    Context.MODE_PRIVATE)

/**
 * Gets the value of the initialization vector present in [SharedPreferences]
 * @param context the context to be passed for [SharedPreferences]
 */
fun getInitializationVector(context: Context): String? = context.getPreferences()
    .getString(PREF_KEY_ENCRYPTION_IV, null)

/**
 * Sets the value of the initialization vector present in [SharedPreferences]
 * @param context the context to be passed for [SharedPreferences]
 * @param iv the value to be set for initialization vector
 */
fun setIntializationVector(context: Context, iv: String) {
    context.getPreferences().edit { putString(PREF_KEY_ENCRYPTION_IV, iv) }
}