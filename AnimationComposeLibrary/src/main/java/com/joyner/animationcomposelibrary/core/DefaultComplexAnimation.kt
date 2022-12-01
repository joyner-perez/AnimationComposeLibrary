package com.joyner.animationcomposelibrary.core

import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.Easing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween

/**
 * Default values configuration complex animation
 *
 * @property infinity[Boolean] optional flag to indicate if the animation should be infinite.
 * @property delayInfinityMillis[Int] optional delay in millis between animations.
 * @property durationInMillis[Int] optional duration in millis of animation.
 * @property delayInitInMillis[Int] optional delay in millis init animation.
 * @property easingValue[Easing] optional easing move value of animation.
 * @property animate[Boolean] required flag to animate the content parameter.
 * @property onAnimateTo[([Boolean]) -> Unit] required callback when animation is
 *     expanded.
 * @constructor Default values for configuration of animation.
 *
 * @author Joyner (https://github.com/joyner-perez)
 */
data class DefaultComplexAnimation(
    val infinity: Boolean = false,
    val delayInfinityMillis: Int = 0,
    val durationInMillis: Int = 1000,
    val delayInitInMillis: Int = 0,
    val easingValue: Easing = LinearEasing,
    val animate: Boolean,
    val onAnimateTo: (result: Boolean) -> Unit,
)

fun getAnimationSpec(
    defaultValuesAnimation: DefaultComplexAnimation,
): AnimationSpec<Float> = tween(
    durationMillis = defaultValuesAnimation.durationInMillis,
    delayMillis = defaultValuesAnimation.delayInitInMillis,
    easing = defaultValuesAnimation.easingValue
)