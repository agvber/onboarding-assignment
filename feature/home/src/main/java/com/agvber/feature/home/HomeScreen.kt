package com.agvber.feature.home

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.agvber.core.designsystem.theme.Black
import com.agvber.core.designsystem.theme.InterFontFamily
import com.agvber.core.designsystem.theme.OnboardingTheme
import com.agvber.core.designsystem.theme.Whit
import com.agvber.feature.home.model.HomeSideEffect
import com.agvber.feature.home.model.HomeUiState
import kotlinx.serialization.Serializable

@Serializable
data class HomeRoute(val uid: String)

@Composable
fun HomeRoute() {
    val context = LocalContext.current
    val viewModel: HomeViewModel = hiltViewModel()
    val uiState = viewModel.uiState

    when (uiState.isLoading) {
        true -> {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxSize()
                    .background(Whit)
            ) {
                CircularProgressIndicator()
            }
        }

        false -> HomeScreen(viewModel.uiState)
    }

    LaunchedEffect(Unit) {
        viewModel.sideEffect.collect {
            when (it) {
                HomeSideEffect.LoadError ->
                    Toast.makeText(context, "데이터 로드 실패", Toast.LENGTH_SHORT).show()
            }
        }
    }
}

@Composable
internal fun HomeScreen(
    uiState: HomeUiState
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(Whit)
    ) {
        Text(
            text = "이름 : ${uiState.name}",
            fontFamily = InterFontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            color = Black,
        )
        Text(
            text = "이메일 : ${uiState.email}",
            fontFamily = InterFontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            color = Black,
        )
        Text(
            text = "패스워드 : ${uiState.password}",
            fontFamily = InterFontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            color = Black,
        )
    }
}

@Preview
@Composable
private fun HomeScreenPreview() {
    OnboardingTheme {
        HomeScreen(HomeUiState("민준"))
    }
}