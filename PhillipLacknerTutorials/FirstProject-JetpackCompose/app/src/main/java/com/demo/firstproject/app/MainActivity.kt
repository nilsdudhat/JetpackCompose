package com.demo.firstproject.app

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults.buttonColors
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import androidx.constraintlayout.compose.layoutId
import com.demo.firstproject.app.ui.theme.FirstProjectJetpackComposeTheme
import kotlinx.coroutines.launch
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ListItems()
        }
    }
}

@Composable
fun Greeting() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Gray),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = "Nilesh",
            modifier = Modifier
                .offset(0.dp, 10.dp)
                .background(Color.Red)
        )
        Text(
            text = "Manoj Dudhat",
        )
    }
}

val fontFamily = FontFamily(
    Font(R.font.oswald_extralight, weight = FontWeight.ExtraLight),
    Font(R.font.oswald_light, weight = FontWeight.Light),
    Font(R.font.oswald_regular, weight = FontWeight.Normal),
    Font(R.font.oswald_medium, weight = FontWeight.Medium),
    Font(R.font.oswald_semibold, weight = FontWeight.SemiBold),
    Font(R.font.oswald_bold, weight = FontWeight.Bold),
)

@Composable
fun MyTextStyle() {
    FirstProjectJetpackComposeTheme {
        Surface {
            Box(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = buildAnnotatedString {
                        withStyle(
                            style = SpanStyle(
                                color = Color.Blue,
                                fontSize = 36.sp,
                                fontWeight = FontWeight.SemiBold,
                                fontStyle = FontStyle.Italic,
                            )
                        ) {
                            append("J")
                        }
                        withStyle(
                            style = SpanStyle(
                                textDecoration = TextDecoration.Underline,
                                fontStyle = FontStyle.Italic,
                            )
                        ) {
                            append("etpack Compose")
                        }
                    },
                    fontFamily = fontFamily,
                    fontWeight = FontWeight.Medium,
                    fontSize = 16.sp,
                    textAlign = TextAlign.Start,
                )
            }
        }
    }
}

val random = Random

@Composable
fun ColorBox() {
    val color = remember {
        mutableStateOf(Color.Red)
    }

    FirstProjectJetpackComposeTheme {
        Surface {
            Box(modifier = Modifier
                .height(100.dp)
                .aspectRatio(1f)
                .background(color = color.value)
                .clickable {
                    color.value =
                        Color(
                            random.nextFloat(),
                            random.nextFloat(),
                            random.nextFloat(),
                            1f,
                        )
                })
        }
    }
}

@Composable
fun EditText() {
    val textFieldState = remember {
        mutableStateOf("Nilesh")
    }
    val snackBarHostState = remember {
        SnackbarHostState()
    }
    val coroutineScope = rememberCoroutineScope()

    FirstProjectJetpackComposeTheme {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            snackbarHost = {
                SnackbarHost(hostState = snackBarHostState)
            }
        ) { paddingValues ->
            Column(
                modifier = Modifier.padding(paddingValues),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Box(modifier = Modifier.padding(16.dp)) {
                    Column {
                        OutlinedTextField(
                            value = textFieldState.value,
                            label = {
                                Text(text = "Enter your name")
                            },
                            shape = RoundedCornerShape(12.dp),
                            onValueChange = {
                                textFieldState.value = it
                            },
                            singleLine = true,
                            modifier = Modifier.fillMaxWidth(),
                        )
                        Spacer(modifier = Modifier.height(24.dp))
                        Button(
                            onClick = {
                                coroutineScope.launch {
                                    snackBarHostState.showSnackbar("Hello ${textFieldState.value}")
                                }
                            },
                            colors = buttonColors(
                                containerColor = Color.Gray,
                                contentColor = Color.White,
                            ),
                            shape = RoundedCornerShape(12.dp),
                            modifier = Modifier.fillMaxWidth(),
                        ) {
                            Text(text = "Submit", Modifier.padding(8.dp))
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ListItems() {
    FirstProjectJetpackComposeTheme {
        Surface {
            LazyColumn {
                items(5000) {
                    Text(
                        text = "Item $it",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun ConstraintView() {
    FirstProjectJetpackComposeTheme {
        Surface {
            val constraintSet = ConstraintSet {
                val greenBox = createRefFor("green")
                val redBox = createRefFor("red")
                val yellowBox = createRefFor("yellow")

                val guideLine = createGuidelineFromTop(0.5f)

                constrain(greenBox) {
                    top.linkTo(guideLine)
                    start.linkTo(parent.start)
                    end.linkTo(redBox.start)
                    width = Dimension.value(100.dp)
                    height = Dimension.value(100.dp)
                }
                constrain(redBox) {
                    top.linkTo(greenBox.bottom)
                    start.linkTo(greenBox.end)
                    end.linkTo(yellowBox.start)
                    width = Dimension.value(100.dp)
                    height = Dimension.value(100.dp)
                }
                constrain(yellowBox) {
                    top.linkTo(guideLine)
                    start.linkTo(redBox.end)
                    end.linkTo(parent.end)
                    width = Dimension.value(100.dp)
                    height = Dimension.value(100.dp)
                }
                createHorizontalChain(greenBox, redBox, yellowBox, chainStyle = ChainStyle.Packed)
            }
            ConstraintLayout(constraintSet = constraintSet, modifier = Modifier.fillMaxSize()) {
                Box(modifier = Modifier
                    .layoutId("green")
                    .background(Color.Green))
                Box(modifier = Modifier
                    .layoutId("red")
                    .background(Color.Red))
                Box(modifier = Modifier
                    .layoutId("yellow")
                    .background(Color.Yellow))
            }
        }
    }
}

@Preview(
    name = "Light",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    showBackground = true,
)
@Preview(
    name = "Dark",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true,
)
@Composable
fun ConstraintPreview() {
    ConstraintView()
}

/*@Preview(
    name = "Light",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    showBackground = true,
)
@Preview(
    name = "Dark",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true,
)
@Composable
fun ListPreview() {
    ListItems()
}*/

/*@Preview(
    name = "Light",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    showBackground = true,
)
@Preview(
    name = "Dark",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true,
)
@Composable
fun EditTextPreview() {
    EditText()
}*/

/*@Preview(
    name = "Light",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    showBackground = true,
)
@Preview(
    name = "Dark",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true,
)
@Composable
fun ColorBoxPreview() {
    ColorBox()
}*/

/*@Preview(
    name = "Light",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    showBackground = true,
)
@Preview(
    name = "Dark",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true,
)
@Composable
fun MyTextStylePreview() {
    FirstProjectJetpackComposeTheme {
        Surface {
            Box(modifier = Modifier.padding(16.dp)) {
                MyTextStyle()
            }
        }
    }
}*/

/*
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Greeting()
}*/
