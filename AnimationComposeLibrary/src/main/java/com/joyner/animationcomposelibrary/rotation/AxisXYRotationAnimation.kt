package com.joyner.animationcomposelibrary.rotation

import androidx.compose.animation.core.animate
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import com.joyner.animationcomposelibrary.core.DefaultComplexAnimation
import com.joyner.animationcomposelibrary.core.getAnimationSpec
import kotlinx.coroutines.delay

/**
 * Axis x y rotation animation
 *
 * @param defaultValuesAnimation[DefaultComplexAnimation] required default
 *     configuration values of animation.
 * @param firstHorizontal[Boolean] optional first orientation value of
 *     animation.
 * @param content[Composable] required composable element you want to
 *     animate.
 * @author Joyner (https://github.com/joyner-perez)
 */
@Composable
fun AxisXYRotationAnimation(
    defaultValuesAnimation: DefaultComplexAnimation,
    firstHorizontal: Boolean = true,
    content: @Composable (xRotation: Float, yRotation: Float) -> Unit
) {
    var xRotation by rememberSaveable {
        mutableStateOf(0f)
    }
    var yRotation by rememberSaveable {
        mutableStateOf(0f)
    }

    if (defaultValuesAnimation.animate) {
        LaunchedEffect(key1 = Unit) {
            if (defaultValuesAnimation.infinity) {
                while (true) {
                    if (firstHorizontal) {
                        animate(
                            initialValue = 0f,
                            targetValue = 360f,
                            animationSpec = getAnimationSpec(defaultValuesAnimation),
                            block = { value, _ -> yRotation = value }
                        )
                        animate(
                            initialValue = 0f,
                            targetValue = 360f,
                            animationSpec = getAnimationSpec(defaultValuesAnimation),
                            block = { value, _ -> xRotation = value }
                        )
                        delay(timeMillis = defaultValuesAnimation.delayInfinityMillis.toLong())
                    } else {
                        animate(
                            initialValue = 0f,
                            targetValue = 360f,
                            animationSpec = getAnimationSpec(defaultValuesAnimation),
                            block = { value, _ -> xRotation = value }
                        )
                        animate(
                            initialValue = 0f,
                            targetValue = 360f,
                            animationSpec = getAnimationSpec(defaultValuesAnimation),
                            block = { value, _ -> yRotation = value }
                        )
                        delay(timeMillis = defaultValuesAnimation.delayInfinityMillis.toLong())
                    }
                }
            } else {
                if (firstHorizontal) {
                    animate(
                        initialValue = 0f,
                        targetValue = 360f,
                        animationSpec = getAnimationSpec(defaultValuesAnimation),
                        block = { value, _ -> yRotation = value }
                    )
                    animate(
                        initialValue = 0f,
                        targetValue = 360f,
                        animationSpec = getAnimationSpec(defaultValuesAnimation),
                        block = { value, _ -> xRotation = value }
                    )
                } else {
                    animate(
                        initialValue = 0f,
                        targetValue = 360f,
                        animationSpec = getAnimationSpec(defaultValuesAnimation),
                        block = { value, _ -> xRotation = value }
                    )
                    animate(
                        initialValue = 0f,
                        targetValue = 360f,
                        animationSpec = getAnimationSpec(defaultValuesAnimation),
                        block = { value, _ -> yRotation = value }
                    )
                }

                defaultValuesAnimation.onAnimateTo(false)
                defaultValuesAnimation.onAnimationEnd()
            }
        }
    }

    content(
        xRotation = xRotation,
        yRotation = yRotation
    )
}