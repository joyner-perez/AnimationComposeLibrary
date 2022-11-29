package com.joyner.animationcomposelibrary.expand

import androidx.compose.animation.core.Easing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.unit.Dp

/**
 *  A expand size animation for composable view.
 *  It depends on the type you must change one property or another, for example,
 *  in an Icon you must modify its size but in Text you must modify the size of the font
 *
 *  Examples usage:
 *
 * ```
 *  ExpandWithComeBackAnimation() {
 *      Icon(
 *           modifier = Modifier
 *              .size(it),
 *           imageVector = Icons.Outlined.Settings,
 *           contentDescription = "Config"
 *       )
 *  }
 *  ```
 *
 *  ```
 *  ExpandWithComeBackAnimation(
 *     initSize = 24.dp,
 *     targetSize = 48.dp
 *  ) {
 *      Icon(
 *           modifier = Modifier
 *              .size(it),
 *           imageVector = Icons.Outlined.Settings,
 *           contentDescription = "Config"
 *       )
 *  }
 *  ```
 *
 *  @param infinity [Boolean] optional flag to indicate if the animation should be infinite.
 *  @param delayInfinityMillis [Int] optional delay init animation.
 *  @param initSize [Dp] required init size of element.
 *  @param targetSize [Dp] required target size of element.
 *  @param durationInMillis [Int] optional duration of animation.
 *  @param easingValue [Easing] optional easing move value of animation.
 *  @param content [Composable] the composable element you want to animate.
 *
 *  @author Joyner (https://github.com/joyner-perez)
 */
@Composable
fun ExpandWithComeBackAnimation(
    infinity: Boolean = false,
    delayInfinityMillis: Int = 500,
    initSize: Float,
    targetSize: Float,
    durationInMillis: Int = 500,
    easingValue: Easing = LinearEasing,
    content: @Composable (size: Float) -> Unit
) {
    var endAnimation by rememberSaveable { mutableStateOf(false) }
    var expandTo by rememberSaveable { mutableStateOf(true) }
    val animationExpandTo by animateFloatAsState(
        targetValue = if (expandTo) initSize else targetSize,
        animationSpec = tween(
            durationMillis = durationInMillis / 2,
            delayMillis = if (endAnimation && infinity) delayInfinityMillis else 0,
            easing = easingValue
        ),
        finishedListener = {
            endAnimation = it == initSize
            if (infinity) {
                expandTo = !expandTo
            } else if (!expandTo) {
                expandTo = !expandTo
            }
        }
    )

    content(size = animationExpandTo)

    LaunchedEffect(key1 = Unit) {
        expandTo = !expandTo
    }
}