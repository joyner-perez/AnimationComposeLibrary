package com.joyner.animationcomposelibrary.rotation

import androidx.compose.animation.core.Easing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animate
import androidx.compose.animation.core.tween
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import kotlinx.coroutines.delay

/**
 *  A rotate vertical animation for composable views.
 *
 *  Examples usage:
 *
 * ```
 *  MiddleHorVerRotationAnimation() {
 *      Text(text = "Hello World!")
 *  }
 *  ```
 *
 *  ```
 *  MiddleHorVerRotationAnimation(
 *      infinity = true,
 *      delayInfinityMillis = 1000
 *  ) {
 *      Text(text = "Hello World!")
 *  }
 *  ```
 *
 *  ```
 *  MiddleHorVerRotationAnimation(
 *      infinity = true,
 *      delayInfinityMillis = 1000,
 *      initialLeftRotationValue = 180f,
 *      targetLeftRotationValue = 0f
 *  ) {
 *      Text(text = "Hello World!")
 *  }
 *  ```
 *
 *  @param infinity [Boolean] optional flag to indicate if the animation should be infinite.
 *  @param firstHorizontal [Boolean] optional flag to indicate if the animation start in vertical or horizontal.
 *  @param delayInfinityMillis [Long] optional delay in millis between repeat animations.
 *  @param initialRightRotationValue [Float] optional initial rotation value for the right side.
 *  @param targetRightRotationValue [Float] optional target rotation value for the right side.
 *  @param initialLeftRotationValue [Float] optional initial rotation value for the left side.
 *  @param targetLeftRotationValue [Float] optional target rotation value for the left side.
 *  @param durationRightRotationMillis [Int] optional duration to rotate to the right side.
 *  @param durationLeftRotationMillis [Int] optional duration to rotate to the left side.
 *  @param initialDelayMillis [Int] optional delay to start animation.
 *  @param endDelayMillis [Int] optional delay to start the second part of animation.
 *  @param easingRightRotation [Easing] optional easing rotation value for the right side.
 *  @param easingLeftRotation [Easing] optional easing rotation value for the left side.
 *  @param content [Composable] the composable element you want to animate.
 *
 *  @author Joyner (https://github.com/joyner-perez)
 */
@Composable
fun MiddleExeXYRotationAnimation(
    infinity: Boolean = false,
    firstHorizontal: Boolean = true,
    delayInfinityMillis: Long = 0,
    initialRightRotationValue: Float = 0f,
    targetRightRotationValue: Float = 180f,
    initialLeftRotationValue: Float = 180f,
    targetLeftRotationValue: Float = 360f,
    initialUpRotationValue: Float = 0f,
    targetUpRotationValue: Float = 180f,
    initialDownRotationValue: Float = 180f,
    targetDownRotationValue: Float = 360f,
    durationRightRotationMillis: Int = 1000,
    durationLeftRotationMillis: Int = 1000,
    initialDelayMillis: Int = 0,
    endDelayMillis: Int = 0,
    easingRightRotation: Easing = LinearEasing,
    easingLeftRotation: Easing = LinearEasing,
    content: @Composable (xRotation: Float, yRotation: Float) -> Unit
) {
    var xRotation by rememberSaveable {
        mutableStateOf(0f)
    }
    var yRotation by rememberSaveable {
        mutableStateOf(0f)
    }

    LaunchedEffect(key1 = Unit) {
        if (infinity) {
            while (true) {
                if (firstHorizontal) {
                    animate(
                        initialValue = initialRightRotationValue,
                        targetValue = targetRightRotationValue,
                        animationSpec = tween(
                            durationMillis = durationRightRotationMillis,
                            delayMillis = initialDelayMillis,
                            easing = easingRightRotation
                        ),
                        block = { value, _ -> yRotation = value }
                    )
                    animate(
                        initialValue = initialUpRotationValue,
                        targetValue = targetUpRotationValue,
                        animationSpec = tween(
                            durationMillis = durationRightRotationMillis,
                            delayMillis = initialDelayMillis,
                            easing = easingRightRotation
                        ),
                        block = { value, _ -> xRotation = value }
                    )
                    animate(
                        initialValue = initialLeftRotationValue,
                        targetValue = targetLeftRotationValue,
                        animationSpec = tween(
                            durationMillis = durationLeftRotationMillis,
                            delayMillis = endDelayMillis,
                            easing = easingLeftRotation
                        ),
                        block = { value, _ -> yRotation = value }
                    )
                    animate(
                        initialValue = initialDownRotationValue,
                        targetValue = targetDownRotationValue,
                        animationSpec = tween(
                            durationMillis = durationLeftRotationMillis,
                            delayMillis = endDelayMillis,
                            easing = easingLeftRotation
                        ),
                        block = { value, _ -> xRotation = value }
                    )
                    delay(timeMillis = delayInfinityMillis)
                } else {
                    animate(
                        initialValue = initialUpRotationValue,
                        targetValue = targetUpRotationValue,
                        animationSpec = tween(
                            durationMillis = durationRightRotationMillis,
                            delayMillis = initialDelayMillis,
                            easing = easingRightRotation
                        ),
                        block = { value, _ -> xRotation = value }
                    )
                    animate(
                        initialValue = initialRightRotationValue,
                        targetValue = targetRightRotationValue,
                        animationSpec = tween(
                            durationMillis = durationRightRotationMillis,
                            delayMillis = initialDelayMillis,
                            easing = easingRightRotation
                        ),
                        block = { value, _ -> yRotation = value }
                    )
                    animate(
                        initialValue = initialDownRotationValue,
                        targetValue = targetDownRotationValue,
                        animationSpec = tween(
                            durationMillis = durationLeftRotationMillis,
                            delayMillis = endDelayMillis,
                            easing = easingLeftRotation
                        ),
                        block = { value, _ -> xRotation = value }
                    )
                    animate(
                        initialValue = initialLeftRotationValue,
                        targetValue = targetLeftRotationValue,
                        animationSpec = tween(
                            durationMillis = durationLeftRotationMillis,
                            delayMillis = endDelayMillis,
                            easing = easingLeftRotation
                        ),
                        block = { value, _ -> yRotation = value }
                    )
                    delay(timeMillis = delayInfinityMillis)
                }
            }
        } else {
            if (firstHorizontal) {
                animate(
                    initialValue = initialRightRotationValue,
                    targetValue = targetRightRotationValue,
                    animationSpec = tween(
                        durationMillis = durationRightRotationMillis,
                        delayMillis = initialDelayMillis,
                        easing = easingRightRotation
                    ),
                    block = { value, _ -> yRotation = value }
                )
                animate(
                    initialValue = initialUpRotationValue,
                    targetValue = targetUpRotationValue,
                    animationSpec = tween(
                        durationMillis = durationRightRotationMillis,
                        delayMillis = initialDelayMillis,
                        easing = easingRightRotation
                    ),
                    block = { value, _ -> xRotation = value }
                )
                animate(
                    initialValue = initialLeftRotationValue,
                    targetValue = targetLeftRotationValue,
                    animationSpec = tween(
                        durationMillis = durationLeftRotationMillis,
                        delayMillis = endDelayMillis,
                        easing = easingLeftRotation
                    ),
                    block = { value, _ -> yRotation = value }
                )
                animate(
                    initialValue = initialDownRotationValue,
                    targetValue = targetDownRotationValue,
                    animationSpec = tween(
                        durationMillis = durationLeftRotationMillis,
                        delayMillis = endDelayMillis,
                        easing = easingLeftRotation
                    ),
                    block = { value, _ -> xRotation = value }
                )
                delay(timeMillis = delayInfinityMillis)
            } else {
                animate(
                    initialValue = initialUpRotationValue,
                    targetValue = targetUpRotationValue,
                    animationSpec = tween(
                        durationMillis = durationRightRotationMillis,
                        delayMillis = initialDelayMillis,
                        easing = easingRightRotation
                    ),
                    block = { value, _ -> xRotation = value }
                )
                animate(
                    initialValue = initialRightRotationValue,
                    targetValue = targetRightRotationValue,
                    animationSpec = tween(
                        durationMillis = durationRightRotationMillis,
                        delayMillis = initialDelayMillis,
                        easing = easingRightRotation
                    ),
                    block = { value, _ -> yRotation = value }
                )
                animate(
                    initialValue = initialDownRotationValue,
                    targetValue = targetDownRotationValue,
                    animationSpec = tween(
                        durationMillis = durationLeftRotationMillis,
                        delayMillis = endDelayMillis,
                        easing = easingLeftRotation
                    ),
                    block = { value, _ -> xRotation = value }
                )
                animate(
                    initialValue = initialLeftRotationValue,
                    targetValue = targetLeftRotationValue,
                    animationSpec = tween(
                        durationMillis = durationLeftRotationMillis,
                        delayMillis = endDelayMillis,
                        easing = easingLeftRotation
                    ),
                    block = { value, _ -> yRotation = value }
                )
            }
        }
    }

    content(
        xRotation = xRotation,
        yRotation = yRotation
    )
}