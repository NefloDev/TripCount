package neflo.dev.tripcount.api.config

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class TokenManager(private val context: Context) {

    companion object {
        private val TOKEN_KEY = stringPreferencesKey("jwt_token")
        private val EMAIL_KEY = stringPreferencesKey("user_email")
        private val PASSWORD_KEY = stringPreferencesKey("user_password")
    }

    fun getToken(): Flow<String?> {
        return context.dataStore.data.map { preferences ->
            preferences[TOKEN_KEY]
        }
    }

    suspend fun saveToken(token: String) {
        context.dataStore.edit { preferences ->
            preferences[TOKEN_KEY] = token
        }
    }

    suspend fun deleteToken() {
        context.dataStore.edit { preferences ->
            preferences.remove(TOKEN_KEY)
        }
    }

    fun getEmail(): Flow<String?> {
        return context.dataStore.data.map { preferences ->
            preferences[EMAIL_KEY]
        }
    }

    fun getPassword(): Flow<String?> {
        return context.dataStore.data.map { preferences ->
            preferences[PASSWORD_KEY]
        }
    }

    suspend fun saveSession(email: String, password: String) {
        context.dataStore.edit { preferences ->
            preferences[EMAIL_KEY] = email
            preferences[PASSWORD_KEY] = password
        }
    }

    suspend fun clearSession() {
        context.dataStore.edit { preferences ->
            preferences.remove(EMAIL_KEY)
            preferences.remove(PASSWORD_KEY)
        }
    }

}