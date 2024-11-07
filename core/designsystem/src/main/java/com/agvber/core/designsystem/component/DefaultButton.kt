package com.agvber.core.designsystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.agvber.core.designsystem.theme.InterFontFamily
import com.agvber.core.designsystem.theme.MainBlue
import com.agvber.core.designsystem.theme.OnboardingTheme
import com.agvber.core.designsystem.theme.Whit

@Composable
fun DefaultButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    content: @Composable () -> Unit
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .background(MainBlue, RoundedCornerShape(16.dp))
            .height(56.dp)
            .clickable(enabled = enabled, onClick = onClick)
    ) {
        CompositionLocalProvider(
            LocalTextStyle provides LocalTextStyle.current.merge(
                color = Whit,
                fontFamily = InterFontFamily,
                fontWeight = FontWeight.Bold,
                fontStyle = FontStyle.Normal,
                fontSize = 16.sp,
            )
        ) {
            content()
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun DefaultButtonPreview() {
    OnboardingTheme {
        DefaultButton(
            onClick = {},
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("hello")
        }
    }
}