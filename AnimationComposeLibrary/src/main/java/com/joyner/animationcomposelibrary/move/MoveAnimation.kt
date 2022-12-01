package com.joyner.animationcomposelibrary.move

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.unit.dp
import com.joyner.animationcomposelibrary.core.DefaultValuesAnimation
import com.joyner.animationcomposelibrary.move.EnumMoveDirection.*

/**
 * Move animation
 *
 * @param defaultValuesAnimation[DefaultValuesAnimation] required default
 *     configuration values of animation.
 * @param direction[EnumMoveDirection] required direction of animation.
 * @param content[Composable] required composable element you want to
 *     animate.
 * @author Joyner (https://github.com/joyner-perez)
 */
@Composable
fun MoveAnimation(
    defaultValuesAnimation: DefaultValuesAnimation,
    direction: EnumMoveDirection,
    content: @Composable (paddingValue: PaddingValues) -> Unit
) {
    val animationMoveTo by animateDpAsState(
        targetValue = if (defaultValuesAnimation.animate) {
            defaultValuesAnimation.targetValue.dp
        } else {
            defaultValuesAnimation.initValue.dp
        },
        animationSpec = tween(
            durationMillis = defaultValuesAnimation.durationInMillis,
            delayMillis = defaultValuesAnimation.delayInitInMillis,
            easing = defaultValuesAnimation.easingValue
        )
    )

    content(
        paddingValue = when (direction) {
            RIGHT -> PaddingValues(start = animationMoveTo)
            LEFT -> PaddingValues(end = animationMoveTo)
            UP -> PaddingValues(bottom = animationMoveTo)
            DOWN -> PaddingValues(top = animationMoveTo)
        }
    )
}