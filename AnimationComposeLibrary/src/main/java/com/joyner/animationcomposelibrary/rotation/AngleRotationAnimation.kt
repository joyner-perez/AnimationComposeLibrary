package com.joyner.animationcomposelibrary.rotation

import androidx.compose.animation.core.Easing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animate
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import kotlinx.coroutines.delay

/**
 *  A rotate horizontal animation for composable views.
 *
 *  Examples usage:
 *
 * ```
 *  AngleRotationAnimation() {
 *      Text(text = "Hello World!")
 *  }
 *  ```
 *
 *  ```
 *  AngleRotationAnimation(
 *      infinity = true,
 *      delayInfinityMillis = 1000
 *  ) {
 *      Text(text = "Hello World!")
 *  }
 *  ```
 *
 *  ```
 *  HorizontalRotationAnimation(
 *      infinity = true,
 *      delayInfinityMillis = 1000,
 *      initialDegreeValue = 360f,
 *      targetDegreeValue = 0f
 *  ) {
 *      Text(text = "Hello World!")
 *  }
 *  ```
 *
 *  @param modifier [Modifier] optional modifier for the animation.
 *  @param infinity [Boolean] optional flag to indicate if the animation should be infinite.
 *  @param delayInfinityMillis [Long] optional delay in millis between repeat animations.
 *  @param initialDegreeValue [Float] optional initial degree value.
 *  @param targetDegreeValue [Float] optional target degree value.
 *  @param durationRotationMillis [Int] optional duration of animation.
 *  @param initialDelayMillis [Int] optional delay to start animation.
 *  @param easingRotation [Easing] optional easing of animation.
 *  @param content [Composable] the composable element you want to animate.
 *
 *  @author Joyner Pérez Echevarria (https://github.com/joyner-perez)
 */
@Composable
fun AngleRotationAnimation(
    modifier: Modifier = Modifier,
    infinity: Boolean = false,
    delayInfinityMillis: Long = 0,
    initialDegreeValue: Float = 0f,
    targetDegreeValue: Float = 360f,
    durationRotationMillis: Int = 1000,
    initialDelayMillis: Int = 0,
    easingRotation: Easing = LinearEasing,
    content: @Composable () -> Unit
) {
    var rotationDegree by rememberSaveable {
        mutableStateOf(0f)
    }

    LaunchedEffect(key1 = Unit) {
        if (infinity) {
            while (true) {
                animate(
                    initialValue = initialDegreeValue,
                    targetValue = targetDegreeValue,
                    animationSpec = tween(
                        durationMillis = durationRotationMillis,
                        delayMillis = initialDelayMillis,
                        easing = easingRotation
                    ),
                    block = { value, _ -> rotationDegree = value }
                )
                delay(timeMillis = delayInfinityMillis)
            }
        } else {
            animate(
                initialValue = initialDegreeValue,
                targetValue = targetDegreeValue,
                animationSpec = tween(
                    durationMillis = durationRotationMillis,
                    delayMillis = initialDelayMillis,
                    easing = easingRotation
                ),
                block = { value, _ -> rotationDegree = value }
            )
        }
    }

    Box(modifier = modifier
        .wrapContentSize()
        .graphicsLayer(rotationZ = rotationDegree)
    ) {
        content()
    }
}