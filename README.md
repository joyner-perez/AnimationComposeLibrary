[![](https://jitpack.io/v/joyner-perez/AnimationComposeLibrary.svg)](https://jitpack.io/#joyner-perez/AnimationComposeLibrary)
# Android animation library for jetpack compose

Android animations for compose, it will help you to create your animations and reduce the code you have to write.
In the future I will be adding more animations, if you have any suggestion let me know and I will analyze it.

**Note: For better performance use the library objects inside Box, Column, Row.**

**Show app folder example for more help**

**With Kotlin**

⚙️ How to install
--
1. Add it in your root build.gradle at the end of repositories:

		allprojects {
			repositories {
				...
				maven { url 'https://jitpack.io' }
			}
		}

2. Add the dependency:

		dependencies {
			implementation 'com.github.joyner-perez:AnimationComposeLibrary:1.0.0'
		}

🕹 How to use:
--
      ExeYRotationAnimation(
          infinity = true,
          delayInfinityMillis = 1000
       ) {
            Text(
                 modifier = Modifier
                                .graphicsLayer(rotationY = it),
                 text = "ExeYRotationAnimation"
            )
       }
       
       ExeXRotationAnimation(
                    infinity = true,
                    delayInfinityMillis = 1000
                ) {
                    Text(
                        modifier = Modifier
                            .graphicsLayer(rotationX = it),
                        text = "ExeXRotationAnimation"
                    )
                }
                
       ExeXYRotationAnimation(
                    infinity = true,
                    delayInfinityMillis = 1000
                ) { xRotation: Float, yRotation: Float ->
                    Text(
                        modifier = Modifier
                            .graphicsLayer(
                                rotationX = xRotation,
                                rotationY = yRotation),
                        text = "ExeXYRotationAnimation"
                    )
                }
                
        MiddleExeXYRotationAnimation(
                    infinity = true,
                    delayInfinityMillis = 1000
                ) { xRotation: Float, yRotation: Float ->
                    Text(
                        modifier = Modifier
                            .graphicsLayer(
                                rotationX = xRotation,
                                rotationY = yRotation),
                        text = "MiddleExeXYRotationAnimation")
                }
                
        AngleRotationAnimation(
                    infinity = true,
                    delayInfinityMillis = 1000
                ) {
                    Text(
                        modifier = Modifier
                            .graphicsLayer(rotationZ = it),
                        text = "AngleRotationAnimation"
                    )
                }
                
        MoveWithComeBackAnimation(
                    infinity = true,
                    delayInfinityMillis = 2000
                ) {
                    Text(
                        modifier = Modifier
                            .padding(it),
                        text = "MoveWithComeBackAnimation"
                    )
                }
                
        MoveAnimation {
                    Text(
                        modifier = Modifier
                            .padding(it),
                        text = "MoveAnimation"
                    )
                }
                
        ExpandAnimation(
                    initSize = 24f,
                    targetSize = 48f
                ) {
                    Icon(
                        modifier = Modifier
                            .size(it.dp),
                        imageVector = Icons.Outlined.Settings,
                        contentDescription = "Config"
                    )
                }
                
        ExpandWithComeBackAnimation(
                    infinity = true,
                    initSize = 24f,
                    targetSize = 48f
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            modifier = Modifier
                                .size(it.dp),
                            imageVector = Icons.Outlined.Settings,
                            contentDescription = "Config"
                        )

                        Text(
                            fontSize = it.sp,
                            text = "Hello World!"
                        )
                    }
                }        
