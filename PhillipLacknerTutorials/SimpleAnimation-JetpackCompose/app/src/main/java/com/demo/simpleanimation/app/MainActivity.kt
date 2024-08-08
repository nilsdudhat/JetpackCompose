package com.demo.simpleanimation.app

import android.annotation.SuppressLint
import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.demo.simpleanimation.app.ui.theme.SimpleAnimationJetpackComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SimpleAnimationJetpackComposeTheme {

            }
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Animate() {
    val sizeState = remember {
        mutableStateOf(200.dp)
    }
    val actualSize = animateDpAsState(
        targetValue = sizeState.value,
        label = "size",
        animationSpec = /*tween(
            durationMillis = 1000,
            delayMillis = 100,
            easing = FastOutLinearInEasing,
        )*/
        spring(
            dampingRatio = Spring.DampingRatioHighBouncy,
        )
    )

    SimpleAnimationJetpackComposeTheme {
        Scaffold {
            Surface {
                Box(
                    modifier = Modifier
                        .size(actualSize.value)
                        .background(Color.Red),
                    contentAlignment = Alignment.Center,
                ) {
                    Button(onClick = {
                        sizeState.value += 50.dp
                    }) {
                        Text(text = "Increase Size")
                    }
                }
            }
        }
    }
}

@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO,
)
@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
@Composable
fun AnimatePreview() {
    Animate()
}