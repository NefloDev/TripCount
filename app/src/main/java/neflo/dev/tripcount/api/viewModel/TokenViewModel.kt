package neflo.dev.tripcount.api.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import neflo.dev.tripcount.api.config.TokenManager

@HiltViewModel
class TokenViewModel @Inject constructor(private val tokenManager: TokenManager): ViewModel() {
    private val _email = MutableStateFlow<String>("")
    val email: StateFlow<String> = _email

    private val _password = MutableStateFlow<String>("")
    val password: StateFlow<String> = _password

    private val _token = MutableStateFlow<String?>(null)
    val token: StateFlow<String?> = _token

    init {
        viewModelScope.launch(Dispatchers.IO) {
            tokenManager.getToken().collect {
                withContext(Dispatchers.Main) {
                    _token.value = it
                }
            }
        }
    }

    fun saveLogin(email: String, password: String){
        _email.value = email
        _password.value = password
    }

    fun saveSession(token: String) {
        viewModelScope.launch(Dispatchers.IO) {
            tokenManager.saveToken(token)
            tokenManager.saveSession(email.value, password.value)
        }
    }

    fun clearSession() {
        viewModelScope.launch(Dispatchers.IO) {
            tokenManager.deleteToken()
            tokenManager.clearSession()
        }
    }

}