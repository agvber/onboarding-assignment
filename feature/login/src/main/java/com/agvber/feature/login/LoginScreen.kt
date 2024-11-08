package com.agvber.feature.login

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.agvber.core.designsystem.R.drawable.ic_email_textfield
import com.agvber.core.designsystem.R.drawable.ic_password_textfield
import com.agvber.core.designsystem.component.BackButtonAppbar
import com.agvber.core.designsystem.component.DefaultButton
import com.agvber.core.designsystem.component.DefaultTextField
import com.agvber.core.designsystem.theme.Black
import com.agvber.core.designsystem.theme.InterFontFamily
import com.agvber.core.designsystem.theme.MainBlue
import com.agvber.core.designsystem.theme.OnboardingTheme
import com.agvber.feature.login.model.LoginSideEffect
import com.agvber.feature.login.model.LoginUiState
import kotlinx.serialization.Serializable

@Serializable
data object LoginRoute

@Composable
fun LoginRoute(
    onBackRequest: () -> Unit,
    onSuccessRequest: () -> Unit,
    registerPageRequest: () -> Unit,
) {
    val context = LocalContext.current
    val viewModel: LoginViewModel = hiltViewModel()
    val uiState = viewModel.uiState

    LoginScreen(
        onBackRequest = onBackRequest,
        onLoginRequest = viewModel::login,
        registerPageRequest = registerPageRequest,
        onEmailChange = viewModel::updateEmailState,
        onPasswordChange = viewModel::updatePasswordState,
        uiState = uiState
    )

    LaunchedEffect(Unit) {
        viewModel.sideEffect.collect {
            when (it) {
                LoginSideEffect.LoginFail ->
                    Toast.makeText(context, "로그인 실패", Toast.LENGTH_SHORT).show()

                LoginSideEffect.LoginSuccess -> onSuccessRequest()
            }
        }
    }
}

@Composable
internal fun LoginScreen(
    onBackRequest: () -> Unit,
    onLoginRequest: () -> Unit,
    registerPageRequest: () -> Unit,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    uiState: LoginUiState
) {
    Scaffold(
        topBar = { BackButtonAppbar(onClick = onBackRequest) },
        modifier = Modifier.statusBarsPadding()
    ) { scaffoldPaddingValues ->

        Column(
            modifier = Modifier
                .padding(scaffoldPaddingValues)
                .padding(horizontal = 36.dp)
        ) {
            Text(
                text = "Login",
                color = MainBlue,
                fontFamily = InterFontFamily,
                fontWeight = FontWeight.Bold,
                fontStyle = FontStyle.Normal,
                fontSize = 32.sp,
            )
            Text(
                text = "Login now to track all your expenses and income at a place!",
                color = Black,
                fontFamily = InterFontFamily,
                fontWeight = FontWeight.Normal,
                fontStyle = FontStyle.Normal,
                fontSize = 16.sp,
                modifier = Modifier.padding(top = 20.dp)
            )
            DefaultTextField(
                value = uiState.email,
                onValueChange = onEmailChange,
                title = {
                    Text(
                        text = "Email",
                        modifier = Modifier
                            .padding(top = 40.dp, bottom = 4.dp)
                    )
                },
                navigation = {
                    Image(
                        painter = painterResource(ic_email_textfield),
                        contentDescription = "email",
                        modifier = Modifier.padding(end = 12.dp)
                    )
                },
                placeholder = {
                    Text(text = "Ex: abc@example.com")
                },
                modifier = Modifier
                    .fillMaxWidth()
            )
            DefaultTextField(
                value = uiState.password,
                onValueChange = onPasswordChange,
                title = {
                    Text(
                        text = "Your Password",
                        modifier = Modifier.padding(top = 28.dp, bottom = 4.dp)
                    )
                },
                navigation = {
                    Image(
                        painter = painterResource(ic_password_textfield),
                        contentDescription = "email",
                        modifier = Modifier.padding(end = 12.dp)
                    )
                },
                placeholder = {
                    Text(text = "Ex: abc@example.com")
                },
                modifier = Modifier.fillMaxWidth()
            )
            DefaultButton(
                onClick = onLoginRequest,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 60.dp)
            ) {
                Text("Login")
            }

            Text(
                text = buildAnnotatedString {
                    withStyle(SpanStyle(color = Black)) {
                        append("Don’t have an account? ")
                    }
                    withStyle(
                        SpanStyle(
                            color = MainBlue,
                            fontWeight = FontWeight.Bold,
                            textDecoration = TextDecoration.Underline
                        )
                    ) {
                        append("Register")
                    }
                },
                fontFamily = InterFontFamily,
                fontWeight = FontWeight.Normal,
                fontStyle = FontStyle.Normal,
                fontSize = 16.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 40.dp)
                    .clickable(onClick = registerPageRequest),
            )
        }
    }
}

@Preview
@Composable
private fun LoginScreenPreview() {
    OnboardingTheme {
        LoginScreen(
            onBackRequest = {},
            onLoginRequest = {},
            registerPageRequest = {},
            onEmailChange = {},
            onPasswordChange = {},
            uiState = LoginUiState()
        )
    }
}