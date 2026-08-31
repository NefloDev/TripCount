package neflo.dev.tripcount.api.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import neflo.dev.tripcount.api.ApiInstance
import neflo.dev.tripcount.api.model.authentication.GoogleLoginDTO
import neflo.dev.tripcount.api.model.authentication.LoginDTO
import neflo.dev.tripcount.api.model.authentication.LoginResponse
import neflo.dev.tripcount.api.model.user.UserDTO

class AuthenticationViewModel : ViewModel() {

    private val _loginToken = MutableStateFlow<LoginResponse?>(null)
    val loginToken : StateFlow<LoginResponse?> = _loginToken

    fun login(loginDTO: LoginDTO) {
        viewModelScope.launch {
            try {
                _loginToken.value = ApiInstance.authenticationApi.login(loginDTO)
            } catch (e: Exception) {
                Log.e("AuthenticationViewModel", "Error logging in", e)
            }
        }
    }

    fun googleLogin(googleLoginDTO: GoogleLoginDTO) {
        viewModelScope.launch {
            try {
                _loginToken.value = ApiInstance.authenticationApi.googleLogin(googleLoginDTO)
            } catch (e: Exception) {
                Log.e("AuthenticationViewModel", "Error logging in with google", e)
            }
        }
    }

    fun signUp(registerDTO: UserDTO) {
        viewModelScope.launch {
            try {
                _loginToken.value = ApiInstance.authenticationApi.signUp(registerDTO)
            } catch (e: Exception) {
                Log.e("AuthenticationViewModel", "Error signing up", e)
            }
        }
    }

}