# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

This is an Android Jetpack Compose animation library published via JitPack. It provides reusable `@Composable` wrapper functions that simplify applying animations to arbitrary Compose content. The repo has two modules:

- **`:AnimationComposeLibrary`** — the library module (namespace: `com.joyner.animationcomposelibrary`)
- **`:app`** — a demo app showing all animations in action (namespace: `com.joyner.animationmaterial3`)

## Build Commands

```bash
# Build the library module
./gradlew :AnimationComposeLibrary:assembleRelease

# Build the demo app
./gradlew :app:assembleDebug

# Run unit tests
./gradlew :AnimationComposeLibrary:test

# Run instrumented tests (requires connected device/emulator)
./gradlew :AnimationComposeLibrary:connectedAndroidTest

# Run a single unit test class
./gradlew :AnimationComposeLibrary:test --tests "com.joyner.animationcomposelibrary.ExampleUnitTest"
```

## Architecture

### Core configuration objects (`core/`)

All animations are configured via one of two data classes:

- **`DefaultValuesAnimation`** — for single-value animations (rotation degree, size, alpha, padding). Requires `animate`, `initValue`, `targetValue`, `onAnimateTo`, and `onAnimationEnd`. Optional: `infinity`, `delayInfinityMillis`, `durationInMillis`, `delayInitInMillis`, `easingValue`.
- **`DefaultComplexAnimation`** — for multi-value animations (XY rotation). Same optional fields but no `initValue`/`targetValue` (values are hardcoded per animation).

`getAnimationSpec()` is a helper that creates a `tween` `AnimationSpec<Float>` from these config objects.

### Animation categories

| Package | Composables | Output passed to `content` |
|---|---|---|
| `rotation` | `AxisRotationAnimation`, `AxisXYRotationAnimation`, `MiddleExeXYRotationAnimation` | `Float` (degree) or `(Float, Float)` (xRotation, yRotation) |
| `expand` | `ExpandAnimation`, `ExpandWithComeBackAnimation` | `Float` (size) |
| `move` | `MoveAnimation`, `MoveWithComeBackAnimation` | `PaddingValues` |
| `color` | `AlphaAnimation`, `AlphaWithComeBackAnimation` | `Float` (alpha) |

### Pattern: slot-based API

Every animation composable follows this slot pattern:

```kotlin
AnimationFoo(defaultValuesAnimation = ...) { animatedValue ->
    // Apply animatedValue to your composable here
    YourContent(modifier = Modifier.someModifier(animatedValue))
}
```

The library does **not** apply any `Modifier` itself — it computes the animated value and passes it to the caller's `content` lambda. This keeps it flexible and works best inside `Box`, `Column`, or `Row`.

### Infinity vs one-shot

- `infinity = true`: The animation auto-reverses by calling `onAnimateTo(!animate)` in the `finishedListener`, looping indefinitely. `onAnimationEnd` is **not** called in this mode.
- `infinity = false` (default): `onAnimationEnd` is called once when the animation finishes. `onAnimateTo` is used by the caller to flip `animate` state.

Complex (XY) animations use `LaunchedEffect` + `animate()` coroutine blocks instead of `animateFloatAsState`.

## Publishing

The library is distributed through JitPack. Version tags on the `main` branch are picked up automatically. The consumer adds:

```groovy
maven { url 'https://jitpack.io' }
// then:
implementation 'com.github.joyner-perez:AnimationComposeLibrary:<version>'
```

## Adding a New Animation

1. Create a new package under `AnimationComposeLibrary/src/main/java/com/joyner/animationcomposelibrary/<category>/`.
2. Use `DefaultValuesAnimation` (single animated float) or `DefaultComplexAnimation` (multiple floats via coroutine).
3. Follow the slot pattern: compute the animated value, pass it to `content`.
4. Add a demo item to the `LazyColumn` in the `app` module.
