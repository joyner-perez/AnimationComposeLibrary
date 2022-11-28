package com.joyner.animationmaterial3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.joyner.animationcomposelibrary.move.MoveAnimation
import com.joyner.animationcomposelibrary.move.MoveWithComeBackAnimation
import com.joyner.animationcomposelibrary.rotation.*
import com.joyner.animationmaterial3.ui.theme.AnimationMaterial3Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AnimationMaterial3Theme {
                // A surface container using the 'background' color from the theme
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Greeting("Android")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    ExeYRotationAnimation(
        infinity = true,
        delayInfinityMillis = 1000
    ) {
        Text(text = "Hello $name!")
    }

    ExeXRotationAnimation(
        infinity = true,
        delayInfinityMillis = 1000
    ) {
        Text(text = "Hello $name!")
    }

    ExeXYRotationAnimation(
        infinity = true,
        delayInfinityMillis = 1000
    ) {
        Text(text = "Hello $name!")
    }

    MiddleExeXYRotationAnimation(
        infinity = true,
        delayInfinityMillis = 1000
    ) {
        Text(text = "Hello $name!")
    }

    AngleRotationAnimation(
        infinity = true,
        delayInfinityMillis = 1000
    ) {
        Text(text = "Hello $name!")
    }

    MoveWithComeBackAnimation(
        infinity = true,
        delayInfinityMillis = 2000
    ) {
        Text(text = "Hello $name!")
    }

    MoveAnimation() {
        Text(text = "Hello $name!")
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    AnimationMaterial3Theme {
        Greeting("Android")
    }
}