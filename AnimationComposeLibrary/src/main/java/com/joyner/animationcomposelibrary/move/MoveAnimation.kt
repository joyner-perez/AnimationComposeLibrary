package com.joyner.animationcomposelibrary.move

import androidx.compose.animation.core.Easing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.joyner.animationcomposelibrary.move.EnumMoveDirection.*

/**
 *  A move animation for composable views.
 *
 *  Examples usage:
 *
 * ```
 *  MoveAnimation() {
 *      Text(text = "Hello World!")
 *  }
 *  ```
 *
 *  ```
 *  MoveAnimation(
 *      delayInfinityMillis = 1000
 *  ) {
 *      Text(text = "Hello World!")
 *  }
 *  ```
 *
 *  ```
 *  MoveAnimation(
 *      delayInfinityMillis = 1000,
 *      direction = EnumMoveDirection.DOWN
 *  ) {
 *      Text(text = "Hello World!")
 *  }
 *  ```
 *
 *  @param delayInitAnimation [Int] optional delay init animation.
 *  @param targetAnimationValue [Dp] optional target distance of animation.
 *  @param durationInMillis [Int] optional duration of animation.
 *  @param easingValue [Easing] optional easing move value of animation.
 *  @param direction [EnumMoveDirection] optional direction of animation.
 *  @param content [Composable] the composable element you want to animate.
 *
 *  @author Joyner (https://github.com/joyner-perez)
 */
@Composable
fun MoveAnimation(
    delayInitAnimation: Int = 500,
    targetAnimationValue: Dp = 16.dp,
    durationInMillis: Int = 500,
    easingValue: Easing = LinearEasing,
    direction: EnumMoveDirection = RIGHT,
    content: @Composable (paddingValue: PaddingValues) -> Unit
) {
    var moveTo by rememberSaveable { mutableStateOf(true) }
    val animationMoveTo by animateDpAsState(
        targetValue = if (moveTo) 0.dp else targetAnimationValue,
        animationSpec = tween(
            durationMillis = durationInMillis,
            delayMillis = delayInitAnimation,
            easing = easingValue
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

    LaunchedEffect(key1 = Unit) {
        moveTo = !moveTo
    }
}