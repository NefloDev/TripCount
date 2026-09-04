package neflo.dev.tripcount.screens

import android.content.SharedPreferences
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalResources
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavController
import kotlinx.coroutines.launch
import neflo.dev.tripcount.R
import neflo.dev.tripcount.Screen
import neflo.dev.tripcount.api.model.authentication.LoginDTO
import neflo.dev.tripcount.api.model.authentication.LoginResponse
import neflo.dev.tripcount.api.model.helper.ApiResponse
import neflo.dev.tripcount.api.model.helper.BaseViewModel
import neflo.dev.tripcount.api.viewModel.AuthViewModel
import neflo.dev.tripcount.api.viewModel.TokenViewModel

@Composable
fun LoginScreen(sharedPreferences: SharedPreferences, navController: NavController, tokenViewModel: TokenViewModel) {
    val resources = LocalResources.current
    val authVM: AuthViewModel = hiltViewModel()
    val loginResponse = authVM.loginResponse.collectAsState()
    val token = tokenViewModel.token.collectAsState()
    val scope = rememberCoroutineScope()

    val emailValue = remember {
        mutableStateOf("")
    }
    val pwdValue = remember {
        mutableStateOf("")
    }
    val errorMessage = remember {
        mutableStateOf("")
    }

    val isEmailError = remember {
        mutableStateOf(false)
    }

    val isPasswordError = remember {
        mutableStateOf(false)
    }

    val isLoginError = remember {
        mutableStateOf(false)
    }

    val isLoadingResponse = remember {
        mutableStateOf(false)
    }

    if (token.value != null) {
        navController.navigate(route = Screen.Main.route)
    }

    if (loginResponse.value != null){
        when(loginResponse.value) {
            is ApiResponse.Failure -> {
                isLoginError.value = true
                errorMessage.value = resources.getString(R.string.login_error)
            }
            is ApiResponse.Success -> {
                tokenViewModel.saveToken((loginResponse.value as ApiResponse.Success<LoginResponse>).data.token)
            }
            else -> {}
        }
    }

    return Scaffold(
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        Column (
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(horizontal = 24.dp)
            ) {
                Column (
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .padding(horizontal = 16.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = resources.getString(R.string.login_screen_title),
                        modifier = Modifier.padding(16.dp),
                        style = MaterialTheme.typography.headlineLarge,
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold
                    )
                    if (isLoadingResponse.value) {
                        CircularProgressIndicator()
                    }
                    OutlinedTextField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp, horizontal = 16.dp),
                        value = emailValue.value,
                        isError = isEmailError.value,
                        supportingText = {
                            if (isEmailError.value){
                                Text(errorMessage.value)
                            }
                        },
                        label = { Text(resources.getString(R.string.email_field)) },
                        onValueChange = {
                            errorMessage.value = ""
                            isEmailError.value = false
                            emailValue.value = it
                                        },
                        placeholder = { Text(resources.getString(R.string.email_field_placeholder)) },
                        singleLine = true
                    )
                    OutlinedTextField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp, horizontal = 16.dp),
                        value = pwdValue.value,
                        isError = isPasswordError.value,
                        supportingText = {
                            if (isPasswordError.value){
                                Text(errorMessage.value)
                            }
                        },
                        label = { Text(resources.getString(R.string.password_field)) },
                        onValueChange = {
                            errorMessage.value = ""
                            isPasswordError.value = false
                            pwdValue.value = it
                                        },
                        visualTransformation = PasswordVisualTransformation(),
                        placeholder = { Text(resources.getString(R.string.password_field_placeholder)) },
                        singleLine = true
                    )
                    Button (
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 32.dp, horizontal = 16.dp),
                        shape = RoundedCornerShape(10.dp),
                        onClick = {
                            val email = emailValue.value
                            val password = pwdValue.value

                            when {
                                email.isBlank() -> {
                                    errorMessage.value = resources.getString(R.string.email_required)
                                    isEmailError.value = true
                                }
                                !email.matches(Regex("^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) -> {
                                    errorMessage.value = resources.getString(R.string.invalid_email)
                                    isEmailError.value = true
                                }
                                password.isBlank() -> {
                                    errorMessage.value = resources.getString(R.string.password_required)
                                    isPasswordError.value = true
                                }
                            }

                            scope.launch {
                                authVM.login(
                                    LoginDTO(emailValue.value, pwdValue.value),
                                    object : BaseViewModel.CoroutinesErrorHandler {
                                        override fun onError(message: String) {
                                            isLoginError.value = true
                                            errorMessage.value = resources.getString(R.string.login_error)
                                        }
                                    }
                                )
                            }
                        }
                    ) {
                        Text(
                            text = resources.getString(R.string.login),
                            style = MaterialTheme.typography.titleMedium,
                            textAlign = TextAlign.Center,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    }
}