package com.poulastaa.canvasconcepts

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.poulastaa.canvasconcepts.ui.theme.CanvasConceptsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CanvasConceptsTheme {

            }
        }
    }
}


@Composable
fun CanvasConcepts() {
    Canvas(
        modifier = Modifier
            .background(color = Color(0xFF004661))
            .padding(20.dp)
            .size(300.dp)
    ) {
//        drawRect(
//            color = Color.Black,
//            size = size
//        )
//
//        drawRect(
//            color = Color.Cyan,
//            size = Size(100f, 100f),
//            topLeft = Offset(100f, 100f),
//            style = Stroke(
//                width = 2f
//            )
//        )

        drawCircle(
            brush = Brush.linearGradient(
                colors = listOf(Color.Red, Color.Blue)
            ),
            center = center,
            radius = 250f
        )
    }
}


@Preview(showBackground = true)
@Composable
private fun Preview() {
    CanvasConcepts()
}