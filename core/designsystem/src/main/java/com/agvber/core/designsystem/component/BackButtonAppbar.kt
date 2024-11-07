package com.agvber.core.designsystem.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.agvber.core.designsystem.R
import com.agvber.core.designsystem.theme.OnboardingTheme

@Composable
fun BackButtonAppbar(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        contentAlignment = Alignment.CenterStart,
        modifier = Modifier
            .padding(horizontal = 36.dp)
            .height(72.dp)
            .then(modifier)
    ) {
        Image(
            painter = painterResource(R.drawable.ic_back_appbar),
            contentDescription = "back button",
            modifier = Modifier.clickable(onClick = onClick)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun BackButtonAppbarPreview() {
    OnboardingTheme {
        BackButtonAppbar({}, modifier = Modifier.fillMaxWidth())
    }
}