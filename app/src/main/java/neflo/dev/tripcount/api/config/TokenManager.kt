package neflo.dev.tripcount.api.config

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.time.LocalDateTime
import java.time.ZoneOffset

class TokenManager(private val context: Context) {

    companion object {
        private val TOKEN_KEY = stringPreferencesKey("jwt_token")
        private val LOGIN_TIMESTAMP = longPreferencesKey("login_timestamp")
        private val TOKEN_EXPIRATION = longPreferencesKey("token_expiration")
        private val EMAIL_KEY = stringPreferencesKey("user_email")
        private val PASSWORD_KEY = stringPreferencesKey("user_password")
    }

    fun getToken(): Flow<String?> {
        return context.dataStore.data.map { preferences ->
            preferences[TOKEN_KEY]
        }
    }

    fun getTokenExpiration(): Flow<Long?> {
        return context.dataStore.data.map { preferences ->
            preferences[TOKEN_EXPIRATION]
        }
    }

    fun getLoginTimestamp(): Flow<Long?> {
        return context.dataStore.data.map { preferences ->
            preferences[LOGIN_TIMESTAMP]
        }
    }

    suspend fun saveToken(token: String, tokenExpiration: Long) {
        context.dataStore.edit { preferences ->
            preferences[TOKEN_KEY] = token
            preferences[LOGIN_TIMESTAMP] = LocalDateTime.now().toEpochSecond(ZoneOffset.UTC)
            preferences[TOKEN_EXPIRATION] = tokenExpiration
        }
    }

    suspend fun deleteToken() {
        context.dataStore.edit { preferences ->
            preferences.remove(TOKEN_KEY)
            preferences[LOGIN_TIMESTAMP] = LocalDateTime.MIN.toEpochSecond(ZoneOffset.UTC)
            preferences[TOKEN_EXPIRATION] = 0L
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