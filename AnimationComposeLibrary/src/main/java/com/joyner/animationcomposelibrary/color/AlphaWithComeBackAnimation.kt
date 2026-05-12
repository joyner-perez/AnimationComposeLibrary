package com.joyner.animationcomposelibrary.color

import androidx.compose.runtime.Composable
import com.joyner.animationcomposelibrary.core.DefaultValuesAnimation
import com.joyner.animationcomposelibrary.core.FloatWithComeBackAnimation

/**
 * Alpha with come back animation
 *
 * @param defaultValuesAnimation[DefaultValuesAnimation] required default
 *     configuration values of animation.
 * @param content[Composable] required composable element you want to
 *     animate.
 * @author Joyner (https://github.com/joyner-perez)
 */
@Composable
fun AlphaWithComeBackAnimation(
    defaultValuesAnimation: DefaultValuesAnimation,
    content: @Composable (alpha: Float) -> Unit
) = FloatWithComeBackAnimation(defaultValuesAnimation, content)
