package com.agvber.core.designsystem.component

import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.agvber.core.designsystem.theme.Black
import com.agvber.core.designsystem.theme.HintGray
import com.agvber.core.designsystem.theme.InterFontFamily
import com.agvber.core.designsystem.theme.MainBlue
import com.agvber.core.designsystem.theme.OnboardingTheme

@Composable
fun DefaultTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    title: (@Composable () -> Unit)? = null,
    placeholder: (@Composable () -> Unit)? = null,
    navigation: (@Composable () -> Unit)? = null,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    textStyle: TextStyle = TextStyle.Default,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    singleLine: Boolean = true,
    maxLines: Int = if (singleLine) 1 else Int.MAX_VALUE,
    minLines: Int = 1,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    onTextLayout: (TextLayoutResult) -> Unit = {},
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    cursorBrush: Brush = SolidColor(Color.Black),
) {
    Column {
        if (title != null) {
            CompositionLocalProvider(
                LocalTextStyle provides LocalTextStyle.current.merge(
                    color = Black,
                    fontFamily = InterFontFamily,
                    fontWeight = FontWeight.Normal,
                    fontStyle = FontStyle.Normal,
                    fontSize = 16.sp,
                )
            ) {
                title()
            }
        }
        BasicTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = modifier,
            enabled = enabled,
            readOnly = readOnly,
            textStyle = textStyle,
            keyboardOptions = keyboardOptions,
            keyboardActions = keyboardActions,
            singleLine = singleLine,
            maxLines = maxLines,
            minLines = minLines,
            visualTransformation = visualTransformation,
            onTextLayout = onTextLayout,
            interactionSource = interactionSource,
            cursorBrush = cursorBrush,
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .height(56.dp)
                    .border(1.5.dp, MainBlue, RoundedCornerShape(16.dp))
                    .padding(horizontal = 20.dp)
            ) {
                if (navigation != null) {
                    navigation()
                }

                Box {
                    if (value.isEmpty() && placeholder != null) {
                        CompositionLocalProvider(
                            LocalTextStyle provides LocalTextStyle.current.merge(
                                color = HintGray,
                                fontFamily = InterFontFamily,
                                fontWeight = FontWeight.Normal,
                                fontStyle = FontStyle.Italic,
                                fontSize = 16.sp
                            )
                        ) {
                            placeholder()
                        }
                    }
                    it()
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun DefaultTextFieldPreview() {
    OnboardingTheme {
        DefaultTextField(
            value = "",
            onValueChange = {},
            placeholder = { Text("힌트") },
            modifier = Modifier.fillMaxWidth()
        )
    }
}