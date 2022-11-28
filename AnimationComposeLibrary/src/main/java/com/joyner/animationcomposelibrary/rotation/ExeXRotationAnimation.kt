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
 *  A rotate vertical animation for composable views.
 *
 *  Examples usage:
 *
 * ```
 *  VerticalRotationAnimation() {
 *      Text(text = "Hello World!")
 *  }
 *  ```
 *
 *  ```
 *  VerticalRotationAnimation(
 *      infinity = true,
 *      delayInfinityMillis = 1000
 *  ) {
 *      Text(text = "Hello World!")
 *  }
 *  ```
 *
 *  ```
 *  VerticalRotationAnimation(
 *      infinity = true,
 *      delayInfinityMillis = 1000,
 *      initialUpRotationValue = 180f,
 *      targetDownRotationValue = 0f
 *  ) {
 *      Text(text = "Hello World!")
 *  }
 *  ```
 *
 *  @param modifier [Modifier] optional modifier for the animation.
 *  @param infinity [Boolean] optional flag to indicate if the animation should be infinite.
 *  @param delayInfinityMillis [Long] optional delay in millis between repeat animations.
 *  @param initialUpRotationValue [Float] optional initial rotation value for the up side.
 *  @param targetUpRotationValue [Float] optional target rotation value for the up side.
 *  @param initialDownRotationValue [Float] optional initial rotation value for the down side.
 *  @param targetDownRotationValue [Float] optional target rotation value for the down side.
 *  @param durationUpRotationMillis [Int] optional duration to rotate to the up side.
 *  @param durationDownRotationMillis [Int] optional duration to rotate to the down side.
 *  @param initialDelayMillis [Int] optional delay to start animation.
 *  @param endDelayMillis [Int] optional delay to start the second part of animation.
 *  @param easingUpRotation [Easing] optional easing rotation value for the up side.
 *  @param easingDownRotation [Easing] optional easing rotation value for the down side.
 *  @param content [Composable] the composable element you want to animate.
 *
 *  @author Joyner Pérez Echevarria (https://github.com/joyner-perez)
 */
@Composable
fun ExeXRotationAnimation(
    modifier: Modifier = Modifier,
    infinity: Boolean = false,
    delayInfinityMillis: Long = 0,
    initialUpRotationValue: Float = 0f,
    targetUpRotationValue: Float = 180f,
    initialDownRotationValue: Float = 180f,
    targetDownRotationValue: Float = 360f,
    durationUpRotationMillis: Int = 1000,
    durationDownRotationMillis: Int = 1000,
    initialDelayMillis: Int = 0,
    endDelayMillis: Int = 0,
    easingUpRotation: Easing = LinearEasing,
    easingDownRotation: Easing = LinearEasing,
    content: @Composable () -> Unit
) {
    var xRotation by rememberSaveable {
        mutableStateOf(0f)
    }

    LaunchedEffect(key1 = Unit) {
        if (infinity) {
            while (true) {
                animate(
                    initialValue = initialUpRotationValue,
                    targetValue = targetUpRotationValue,
                    animationSpec = tween(
                        durationMillis = durationUpRotationMillis,
                        delayMillis = initialDelayMillis,
                        easing = easingUpRotation
                    ),
                    block = { value, _ -> xRotation = value }
                )
                animate(
                    initialValue = initialDownRotationValue,
                    targetValue = targetDownRotationValue,
                    animationSpec = tween(
                        durationMillis = durationDownRotationMillis,
                        delayMillis = endDelayMillis,
                        easing = easingDownRotation
                    ),
                    block = { value, _ -> xRotation = value }
                )
                delay(timeMillis = delayInfinityMillis)
            }
        } else {
            animate(
                initialValue = 0f,
                targetValue = 180f,
                animationSpec = tween(
                    durationMillis = 1000,
                    delayMillis = 1000,
                    easing = LinearEasing
                ),
                block = { value, _ -> xRotation = value }
            )
            animate(
                initialValue = 180f,
                targetValue = 360f,
                animationSpec = tween(
                    durationMillis = 1000,
                    delayMillis = endDelayMillis,
                    easing = LinearEasing
                ),
                block = { value, _ -> xRotation = value }
            )
        }
    }

    Box(modifier = modifier
        .wrapContentSize()
        .graphicsLayer {
            rotationX = xRotation
        }
    ) {
        content()
    }
}