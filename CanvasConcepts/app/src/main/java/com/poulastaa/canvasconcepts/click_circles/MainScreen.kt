package com.poulastaa.canvasconcepts.click_circles

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import kotlin.math.pow
import kotlin.math.roundToInt
import kotlin.math.sqrt
import kotlin.random.Random

@Composable
fun MainScreen() {
    var points by remember {
        mutableIntStateOf(0)
    }

    var isTimerRunning by remember {
        mutableStateOf(false)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFF003838))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Points: $points",
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold
            )

            OutlinedButton(onClick = {
                isTimerRunning = !isTimerRunning
                points = 0
            }) {
                Text(text = if (isTimerRunning) "Reset" else "Start")
            }

            CountDownTimer(
                isTimerRunning = isTimerRunning
            ) {
                isTimerRunning = false
            }
        }

        ClickableBall(
            enable = isTimerRunning
        ) {
            points++
        }
    }
}

@Composable
fun CountDownTimer(
    time: Int = 30000,
    isTimerRunning: Boolean = false,
    onTimerEnd: () -> Unit
) {
    var curTimer by remember {
        mutableIntStateOf(time)
    }

    LaunchedEffect(key1 = curTimer, key2 = isTimerRunning) {
        if (!isTimerRunning) {
            curTimer = time
            return@LaunchedEffect
        }

        if (curTimer > 0) {
            delay(1000L)
            curTimer -= 1000
        } else onTimerEnd.invoke()
    }

    Text(
        text = (curTimer / 1000).toString(),
        fontSize = 20.sp,
        fontWeight = FontWeight.SemiBold
    )
}

@Composable
fun ClickableBall(
    radius: Float = 100f,
    enable: Boolean = false,
    ballColor: List<Color> = listOf(Color.Blue, Color.Yellow),
    onBallClicked: () -> Unit
) {
    BoxWithConstraints(
        modifier = Modifier.fillMaxSize()
    ) {
        var ballPos by remember {
            mutableStateOf(
                randomOffset(
                    radius,
                    constraints.maxWidth,
                    constraints.maxHeight
                )
            )
        }

        Canvas(
            modifier = Modifier
                .fillMaxSize()
                .pointerInput(key1 = enable) {
                    if (!enable) return@pointerInput

                    detectTapGestures {
                        val distance = sqrt(
                            (it.x - ballPos.x).pow(2) +
                                    (it.y - ballPos.y).pow(2)
                        )


                        if (distance <= radius) {
                            ballPos = randomOffset(
                                radius,
                                constraints.maxWidth,
                                constraints.maxHeight
                            )
                            onBallClicked.invoke()
                        }
                    }
                }
        ) {
            drawCircle(
                brush = Brush.linearGradient(
                    colors = ballColor
                ),
                radius = radius,
                center = ballPos
            )
        }
    }
}


fun randomOffset(radius: Float, width: Int, height: Int): Offset {
    return Offset(
        x = Random.nextInt(radius.roundToInt(), width - radius.roundToInt()).toFloat(),
        y = Random.nextInt(radius.roundToInt(), height - radius.roundToInt()).toFloat()
    )
}

@Preview
@Composable
private fun Preview() {
    MainScreen()
}