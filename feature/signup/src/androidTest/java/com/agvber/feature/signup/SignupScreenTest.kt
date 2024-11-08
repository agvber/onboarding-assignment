package com.agvber.feature.signup

import android.util.Log
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.isDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTextInput
import androidx.lifecycle.SavedStateHandle
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.agvber.core.domain.model.User
import com.agvber.core.domain.repository.UserRepository
import com.agvber.core.domain.usecase.RegisterAppUseCase
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SignupScreenTest {

    @get:Rule
    val composeRule = createComposeRule()

    private val users = mutableListOf<User>()

    private lateinit var fakeUserRepository: UserRepository
    private lateinit var fakeViewModel: SignupViewModel

    @Before
    fun setup() {
        initUserRepository()
        initViewModel()
        initView()
    }

    private fun initUserRepository() {
        fakeUserRepository = object : UserRepository {

            override suspend fun storeUserInformation(
                email: String,
                name: String,
                password: String
            ) {
                val index = users.lastIndex.coerceAtLeast(0)
                users.add(User(index.toString(), email, name, password))
            }

            override suspend fun getUserInformation(email: String, password: String): User {
                return users.first { email == it.email && password == it.password }
            }

            override suspend fun getUserInformation(uid: String): User {
                return users[uid.toInt()]
            }
        }
    }

    private fun initViewModel() {
        fakeViewModel = SignupViewModel(
            savedStateHandle = SavedStateHandle(),
            registerAppUseCase = RegisterAppUseCase(fakeUserRepository)
        )
    }

    private fun initView() {
        composeRule.setContent {
            SignupRoute(
                onBackRequest = { Log.i(TAG, "onBackRequest") },
                onCompleteRequest = { Log.i(TAG, "onCompleteRequest") },
                loginPageRequest = { Log.i(TAG, "loginPageRequest") },
                viewModel = fakeViewModel
            )
        }
    }

    @Test
    fun `이메일_텍스트_입력값이_정상적으로_작동하는지_확인합니다`() {
        composeRule.onNodeWithTag("email_text_field")
            .performTextInput("agvber@gmail.com")

        composeRule.onNodeWithText("agvber@gmail.com").assertIsDisplayed()
    }

    @Test
    fun `이름_텍스트_입력값이_정상적으로_작동하는지_확인합니다`() {
        composeRule.onNodeWithTag("name_text_field")
            .performTextInput("김민준")

        composeRule.onNodeWithText("김민준").assertIsDisplayed()
    }

    @Test
    fun `비밀번호_텍스트_입력값이_정상적으로_작동하는지_확인합니다`() {
        composeRule.onNodeWithTag("password_text_field")
            .performTextInput("password")

        composeRule.onNodeWithText("password").assertIsDisplayed()
    }

    companion object {
        private const val TAG = "SignupScreenTest"
    }
}