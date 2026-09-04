package neflo.dev.tripcount.api.viewModel

import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import neflo.dev.tripcount.api.model.authentication.GoogleLoginDTO
import neflo.dev.tripcount.api.model.authentication.LoginDTO
import neflo.dev.tripcount.api.model.authentication.LoginResponse
import neflo.dev.tripcount.api.model.helper.ApiResponse
import neflo.dev.tripcount.api.model.helper.BaseViewModel
import neflo.dev.tripcount.api.model.user.UserDTO
import neflo.dev.tripcount.api.repository.AuthRepository

@HiltViewModel
class AuthViewModel @Inject constructor(private val authRepository: AuthRepository): BaseViewModel() {
    private val _loginResponse = MutableStateFlow<ApiResponse<LoginResponse>?>(null)
    val loginResponse: StateFlow<ApiResponse<LoginResponse>?> = _loginResponse


    fun login(loginDTO: LoginDTO, coroutinesErrorHandler: CoroutinesErrorHandler) = responseRequest(_loginResponse, coroutinesErrorHandler) {
        authRepository.login(loginDTO)
    }

    fun googleLogin(googleLoginDTO: GoogleLoginDTO, coroutinesErrorHandler: CoroutinesErrorHandler) = responseRequest(_loginResponse, coroutinesErrorHandler) {
        authRepository.googleLogin(googleLoginDTO)
    }

    fun signUp(registerDTO: UserDTO, coroutinesErrorHandler: CoroutinesErrorHandler) = responseRequest(_loginResponse, coroutinesErrorHandler) {
        authRepository.signUp(registerDTO)
    }
}