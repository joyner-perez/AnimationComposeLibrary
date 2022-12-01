package com.joyner.animationcomposelibrary.rotation

import androidx.compose.animation.core.Easing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue

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
 *  @param infinity [Boolean] optional flag to indicate if the animation should be infinite.
 *  @param delayInfinityMillis [Long] optional delay in millis between repeat animations.
 *  @param initialDegreeValue [Float] optional initial degree value.
 *  @param targetDegreeValue [Float] optional target degree value.
 *  @param durationRotationMillis [Int] optional duration of animation.
 *  @param initialDelayMillis [Int] optional delay to start animation.
 *  @param easingValue [Easing] optional easing of animation.
 *  @param content [Composable] the composable element you want to animate.
 *
 *  @author Joyner (https://github.com/joyner-perez)
 */
@Composable
fun AngleRotationAnimation(
    infinity: Boolean = false,
    delayInfinityMillis: Int = 0,
    initialDegreeValue: Float = 0f,
    targetDegreeValue: Float = 360f,
    durationRotationMillis: Int = 1000,
    initialDelayMillis: Int = 0,
    easingValue: Easing = LinearEasing,
    rotationDegreeTo: Boolean,
    onRotateDegreeTo: (Boolean) -> Unit,
    content: @Composable (rotationDegree: Float) -> Unit
) {
    var endAnimation by rememberSaveable { mutableStateOf(false) }
    val animationRotationDegreeTo by animateFloatAsState(
        targetValue = if (rotationDegreeTo) targetDegreeValue else initialDegreeValue,
        animationSpec = tween(
            durationMillis = durationRotationMillis,
            delayMillis = if (endAnimation && infinity) delayInfinityMillis else initialDelayMillis,
            easing = easingValue
        ),
        finishedListener = {
            endAnimation = it == initialDegreeValue
            if (infinity) {
                onRotateDegreeTo(!rotationDegreeTo)
            } else if (rotationDegreeTo) {
                onRotateDegreeTo(!rotationDegreeTo)
            }
        }
    )

    content(rotationDegree = animationRotationDegreeTo)
}