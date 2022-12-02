package com.joyner.animationcomposelibrary.move

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.unit.dp
import com.joyner.animationcomposelibrary.core.DefaultValuesAnimation
import com.joyner.animationcomposelibrary.core.getAnimationSpec
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
    val animationMoveTo by animateFloatAsState(
        targetValue = if (defaultValuesAnimation.animate) {
            defaultValuesAnimation.targetValue
        } else {
            defaultValuesAnimation.initValue
        },
        animationSpec = getAnimationSpec(
            defaultValuesAnimation = defaultValuesAnimation
        ),
        finishedListener = {
            defaultValuesAnimation.onAnimationEnd()
        }
    )

    content(
        paddingValue = when (direction) {
            RIGHT -> PaddingValues(start = animationMoveTo.dp)
            LEFT -> PaddingValues(end = animationMoveTo.dp)
            UP -> PaddingValues(bottom = animationMoveTo.dp)
            DOWN -> PaddingValues(top = animationMoveTo.dp)
        }
    )
}