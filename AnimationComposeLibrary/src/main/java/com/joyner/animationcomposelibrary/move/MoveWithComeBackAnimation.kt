package com.joyner.animationcomposelibrary.move

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.dp
import com.joyner.animationcomposelibrary.core.DefaultValuesAnimation

/**
 * Move with come back animation
 *
 * @param defaultValuesAnimation[DefaultValuesAnimation] required default
 *     configuration values of animation.
 * @param direction[EnumMoveDirection] required direction of animation.
 * @param content[Composable] required composable element you want to
 *     animate.
 * @author Joyner (https://github.com/joyner-perez)
 */
@Composable
fun MoveWithComeBackAnimation(
    defaultValuesAnimation: DefaultValuesAnimation,
    direction: EnumMoveDirection,
    content: @Composable (paddingValue: PaddingValues) -> Unit
) {
    var endAnimation by rememberSaveable { mutableStateOf(value = false) }

    val animationMoveTo by animateDpAsState(
        targetValue = if (defaultValuesAnimation.animate) {
            defaultValuesAnimation.targetValue.dp
        } else {
            defaultValuesAnimation.initValue.dp
        },
        animationSpec = tween(
            durationMillis = defaultValuesAnimation.durationInMillis / 2,
            delayMillis = if (endAnimation && defaultValuesAnimation.infinity) {
                defaultValuesAnimation.delayInitInMillis
            } else {
                0
            },
            easing = defaultValuesAnimation.easingValue
        ),
        finishedListener = {
            endAnimation = it.value == defaultValuesAnimation.initValue
            if (defaultValuesAnimation.infinity) {
                defaultValuesAnimation.onAnimateTo(defaultValuesAnimation.animate.not())
            } else if (defaultValuesAnimation.animate) {
                defaultValuesAnimation.onAnimateTo(defaultValuesAnimation.animate.not())
            }
            if (defaultValuesAnimation.infinity.not() && endAnimation) {
                defaultValuesAnimation.onAnimationEnd()
            }
        }
    )

    content(
        when (direction) {
            EnumMoveDirection.RIGHT -> PaddingValues(start = animationMoveTo)
            EnumMoveDirection.LEFT -> PaddingValues(end = animationMoveTo)
            EnumMoveDirection.UP -> PaddingValues(bottom = animationMoveTo)
            EnumMoveDirection.DOWN -> PaddingValues(top = animationMoveTo)
        }
    )
}
