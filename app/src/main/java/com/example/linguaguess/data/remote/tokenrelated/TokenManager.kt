package com.example.linguaguess.data.remote.tokenrelated

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.example.linguaguess.di.dataStore
import com.example.linguaguess.utils.Constants

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class TokenManager(private val context: Context) {
    companion object {
        private val accessToken = stringPreferencesKey(Constants.ACCESSTOKEN)
        private val refreshToken = stringPreferencesKey(Constants.REFRESHTOKEN)
    }

    fun getRefreshToken(): Flow<String?> {
        return context.dataStore.data.map { preferences ->
            preferences[refreshToken]
        }
    }
    suspend fun saveAccessToken(accessTokenResponse: String){
        context.dataStore.edit { preferences ->
            preferences[accessToken] = accessTokenResponse
        }
    }
    fun getAccessToken() : Flow<String?>{
        return context.dataStore.data.map { preferences ->
            preferences[accessToken]
        }
    }

    suspend fun saveRefreshToken(token: String) {
        context.dataStore.edit { preferences ->
            preferences[refreshToken] = token
        }
    }

}