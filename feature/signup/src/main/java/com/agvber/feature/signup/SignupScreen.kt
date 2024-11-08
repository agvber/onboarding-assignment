package com.agvber.feature.signup

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
import com.agvber.core.designsystem.R.drawable.ic_name_textfield
import com.agvber.core.designsystem.R.drawable.ic_password_textfield
import com.agvber.core.designsystem.component.BackButtonAppbar
import com.agvber.core.designsystem.component.DefaultButton
import com.agvber.core.designsystem.component.DefaultTextField
import com.agvber.core.designsystem.theme.Black
import com.agvber.core.designsystem.theme.InterFontFamily
import com.agvber.core.designsystem.theme.MainBlue
import com.agvber.core.designsystem.theme.OnboardingTheme
import com.agvber.feature.signup.model.SignupSideEffect
import com.agvber.feature.signup.model.SignupUiState
import kotlinx.serialization.Serializable

@Serializable
data object SignupRoute

@Composable
fun SignupRoute(
    onBackRequest: () -> Unit,
    onCompleteRequest: () -> Unit,
    loginPageRequest: () -> Unit,
) {
    val context = LocalContext.current
    val viewModel: SignupViewModel = hiltViewModel()
    val uiState = viewModel.uiState

    SignupScreen(
        onBackRequest = onBackRequest,
        onCompleteRequest = viewModel::registerService,
        loginPageRequest = loginPageRequest,
        onEmailChange = viewModel::updateEmailState,
        onNameChange = viewModel::updateNameState,
        onPasswordChange = viewModel::updatePasswordState,
        uiState = uiState
    )

    LaunchedEffect(Unit) {
        viewModel.sideEffect.collect {
            when(it) {
                SignupSideEffect.EmailFormatError -> {
                    Toast.makeText(context, "이메일 형식에 오류가 있습니다.", Toast.LENGTH_SHORT).show()
                }
                SignupSideEffect.NameFormatError -> {
                    Toast.makeText(context, "이름 형식에 오류가 있습니다.", Toast.LENGTH_SHORT).show()
                }
                SignupSideEffect.PasswordFormatError -> {
                    Toast.makeText(context, "비밀번호는 8자리에서 12자리 사이어야 합니다.", Toast.LENGTH_SHORT).show()
                }
                SignupSideEffect.SignupFail -> {
                    Toast.makeText(context, "알 수 없는 에러가 발생하였습니다.", Toast.LENGTH_SHORT).show()
                }
                SignupSideEffect.SignupSuccess -> {
                    onCompleteRequest()
                }
            }
        }
    }
}

@Composable
internal fun SignupScreen(
    onBackRequest: () -> Unit,
    onCompleteRequest: () -> Unit,
    loginPageRequest: () -> Unit,
    onEmailChange: (String) -> Unit,
    onNameChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    uiState: SignupUiState,
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
                text = "Register",
                color = MainBlue,
                fontFamily = InterFontFamily,
                fontWeight = FontWeight.Bold,
                fontStyle = FontStyle.Normal,
                fontSize = 32.sp,
            )
            Text(
                text = buildAnnotatedString {
                    withStyle(SpanStyle()) {
                        append("Create an ")
                    }
                    withStyle(
                        SpanStyle(
                            fontWeight = FontWeight.Bold,
                            color = MainBlue
                        )
                    ) {
                        append("account")
                    }
                    withStyle(SpanStyle()) {
                        append(" to access all the features of ")
                    }
                    withStyle(
                        SpanStyle(
                            fontWeight = FontWeight.Bold,
                        )
                    ) {
                        append("Maxpense!")
                    }
                },
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
                value = uiState.name,
                onValueChange = onNameChange,
                title = {
                    Text(
                        text = "Your Name",
                        modifier = Modifier
                            .padding(top = 28.dp, bottom = 4.dp)
                    )
                },
                navigation = {
                    Image(
                        painter = painterResource(ic_name_textfield),
                        contentDescription = "email",
                        modifier = Modifier.padding(end = 12.dp)
                    )
                },
                placeholder = {
                    Text(text = "Ex: abc@example.com")
                },
                modifier = Modifier.fillMaxWidth()
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
                onClick = onCompleteRequest,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 60.dp)
            ) {
                Text("Register")
            }
            Text(
                text = buildAnnotatedString {
                    withStyle(SpanStyle(color = Black)) {
                        append("Already have an account? ")
                    }
                    withStyle(
                        SpanStyle(
                            color = MainBlue,
                            fontWeight = FontWeight.Bold,
                            textDecoration = TextDecoration.Underline
                        )
                    ) {
                        append("Login")
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
                    .clickable(onClick = loginPageRequest),
            )
        }
    }
}

@Preview
@Composable
private fun SignupScreenPreview() {
    OnboardingTheme {
        SignupScreen(
            onBackRequest = {},
            onCompleteRequest = {},
            loginPageRequest = {},
            onEmailChange = {},
            onNameChange = {},
            onPasswordChange = {},
            uiState = SignupUiState(),
        )
    }
}