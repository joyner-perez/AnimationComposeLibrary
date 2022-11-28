package com.joyner.animationcomposelibrary.move

import androidx.compose.animation.core.Easing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.joyner.animationcomposelibrary.move.EnumMoveDirection.*

/**
 *  A move animation for composable views.
 *
 *  Examples usage:
 *
 * ```
 *  MoveWithComeBackAnimation() {
 *      Text(text = "Hello World!")
 *  }
 *  ```
 *
 *  ```
 *  MoveWithComeBackAnimation(
 *      infinity = true,
 *      delayInfinityMillis = 1000,
 *  ) {
 *      Text(text = "Hello World!")
 *  }
 *  ```
 *
 *  ```
 *  MoveWithComeBackAnimation(
 *      infinity = true,
 *      delayInfinityMillis = 1000,
 *      direction = EnumMoveDirection.DOWN
 *  ) {
 *      Text(text = "Hello World!")
 *  }
 *  ```
 *
 *  @param modifier [Modifier] optional modifier for the animation.
 *  @param infinity [Boolean] optional flag to indicate if the animation should be infinite.
 *  @param delayInfinityMillis [Int] optional delay in millis between repeat animation.
 *  @param initialAnimationValue [Dp] optional initial distance of animation.
 *  @param endAnimationValue [Dp] optional target distance of animation.
 *  @param durationInMillis [Int] optional duration of animation.
 *  @param easingValue [Easing] optional easing move value of animation.
 *  @param direction [EnumMoveDirection] optional direction of animation.
 *  @param content [Composable] the composable element you want to animate.
 *
 *  @author Joyner Pérez Echevarria (https://github.com/joyner-perez)
 */
@Composable
fun MoveWithComeBackAnimation(
    modifier: Modifier = Modifier,
    infinity: Boolean = false,
    delayInfinityMillis: Int = 0,
    initialAnimationValue: Dp = 0.dp,
    endAnimationValue: Dp = 16.dp,
    durationInMillis: Int = 1000,
    easingValue: Easing = LinearEasing,
    direction: EnumMoveDirection = RIGHT,
    content: @Composable () -> Unit
) {
    var endAnimation by rememberSaveable { mutableStateOf(false) }
    var moveTo by rememberSaveable { mutableStateOf(true) }
    val animationMoveTo by animateDpAsState(
        targetValue = if (moveTo) initialAnimationValue else endAnimationValue,
        animationSpec = tween(
            durationMillis = durationInMillis / 2,
            delayMillis = if (endAnimation && infinity) delayInfinityMillis else 0,
            easing = easingValue
        ),
        finishedListener = {
            endAnimation = it.value == initialAnimationValue.value
            if (infinity) {
                moveTo = !moveTo
            } else if (!moveTo) {
                moveTo = !moveTo
            }
        }
    )

    Box(modifier = modifier
        .wrapContentSize()
        .padding(paddingValues = when (direction) {
            RIGHT -> PaddingValues(start = animationMoveTo)
            LEFT -> PaddingValues(end = animationMoveTo)
            UP -> PaddingValues(bottom = animationMoveTo)
            DOWN -> PaddingValues(top = animationMoveTo)
        })
    ) {
        content()
    }

    LaunchedEffect(key1 = Unit) {
        moveTo = !moveTo
    }
}