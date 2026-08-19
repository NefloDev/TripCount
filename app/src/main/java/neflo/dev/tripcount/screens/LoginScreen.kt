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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import androidx.core.content.edit
import androidx.navigation.NavController
import kotlinx.coroutines.launch
import neflo.dev.tripcount.R
import neflo.dev.tripcount.Screen
import neflo.dev.tripcount.database.repository.UserRepository
import neflo.dev.tripcount.util.EMAIL_KEY
import neflo.dev.tripcount.util.UID_KEY
import neflo.dev.tripcount.util.encrypt256

@Composable
fun LoginScreen(sharedPreferences: SharedPreferences, navController: NavController) {
    val userRepository = UserRepository()
    val resources = LocalResources.current
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

    val email = sharedPreferences.getString(EMAIL_KEY, "").toString()
    val userId = sharedPreferences.getString(UID_KEY, "").toString()

    if (email != "" && userId != "") {
        navController.navigate(route = Screen.Main.route)
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

                            if (errorMessage.value.isBlank()){
                                scope.launch {
                                    val user = userRepository.getUserByEmail(emailValue.value)
                                    if (user == null) {
                                        errorMessage.value = resources.getString(R.string.user_not_found)
                                    } else {
                                        if (user.password != encrypt256(pwdValue.value)){
                                            errorMessage.value = resources.getString(R.string.incorrect_password)
                                            isPasswordError.value = true
                                        }

                                        saveData(emailValue.value, user.uuid.toString(), sharedPreferences, navController)
                                    }
                                }
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

private fun saveData(email: String, userId: String, sharedPreferences: SharedPreferences, navController: NavController){
    sharedPreferences.edit {
        putString("email", email)
        putString("userId", userId)
    }

    navController.navigate(route = Screen.Main.route)
}