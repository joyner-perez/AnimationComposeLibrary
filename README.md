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
			implementation 'com.github.joyner-perez:AnimationComposeLibrary:(insert latest version)'
		}

🕹 How to use:
--
      LazyColumn(
        modifier = Modifier
            .fillMaxSize(),
        contentPadding = PaddingValues(16.dp)
    ) {
        item {
            var rotationDegreeTo by rememberSaveable { mutableStateOf(false) }
            ItemListDemo {
                AxisRotationAnimation(
                    defaultValuesAnimation = DefaultValuesAnimation(
                        animate = rotationDegreeTo,
                        initValue = 0f,
                        targetValue = 360f,
                        onAnimateTo = { rotationDegreeTo = it },
                    )
                ) {
                    Text(
                        modifier = Modifier
                            .clickable { rotationDegreeTo = !rotationDegreeTo }
                            .graphicsLayer(rotationY = it),
                        text = "Axis Y Rotation Animation"
                    )
                }
            }
        }
        item {
            var rotationDegreeTo by rememberSaveable { mutableStateOf(false) }
            ItemListDemo {
                AxisRotationAnimation(
                    defaultValuesAnimation = DefaultValuesAnimation(
                        animate = rotationDegreeTo,
                        initValue = 0f,
                        targetValue = 360f,
                        onAnimateTo = { rotationDegreeTo = it }
                    )
                ) {
                    Text(
                        modifier = Modifier
                            .clickable { rotationDegreeTo = !rotationDegreeTo }
                            .graphicsLayer(rotationX = it),
                        text = "Axis X Rotation Animation"
                    )
                }
            }
        }
        item {
            var rotationDegreeTo by rememberSaveable { mutableStateOf(false) }
            ItemListDemo {
                AxisRotationAnimation(
                    defaultValuesAnimation = DefaultValuesAnimation(
                        infinity = false,
                        animate = rotationDegreeTo,
                        initValue = 0f,
                        targetValue = 45f,
                        onAnimateTo = { rotationDegreeTo = it }
                    )
                ) {
                    Text(
                        modifier = Modifier
                            .clickable { rotationDegreeTo = !rotationDegreeTo }
                            .graphicsLayer(rotationZ = it),
                        text = "Angle Rotation Animation"
                    )
                }
            }
        }
        item {
            var rotationDegreeTo by rememberSaveable { mutableStateOf(false) }
            ItemListDemo {
                AxisXYRotationAnimation(
                    defaultValuesAnimation = DefaultComplexAnimation(
                        infinity = false,
                        animate = rotationDegreeTo,
                        onAnimateTo = { rotationDegreeTo = it }
                    )
                ) { xRotation: Float, yRotation: Float ->
                    Text(
                        modifier = Modifier
                            .clickable { rotationDegreeTo = !rotationDegreeTo }
                            .graphicsLayer(
                                rotationX = xRotation,
                                rotationY = yRotation),
                        text = "Axis XY Rotation Animation"
                    )
                }
            }
        }
        item {
            var rotationDegreeTo by rememberSaveable { mutableStateOf(false) }
            ItemListDemo {
                MiddleExeXYRotationAnimation(
                    defaultValuesAnimation = DefaultComplexAnimation(
                        infinity = false,
                        animate = rotationDegreeTo,
                        onAnimateTo = { rotationDegreeTo = it }
                    )
                ) { xRotation: Float, yRotation: Float ->
                    Text(
                        modifier = Modifier
                            .clickable { rotationDegreeTo = !rotationDegreeTo }
                            .graphicsLayer(
                                rotationX = xRotation,
                                rotationY = yRotation),
                        text = "Middle Axis XY Rotation Animation")
                }
            }
        }
        item {
            var moveTo by rememberSaveable { mutableStateOf(false) }
            ItemListDemo {
                MoveWithComeBackAnimation(
                    defaultValuesAnimation = DefaultValuesAnimation(
                        infinity = false,
                        animate = moveTo,
                        initValue = 0f,
                        targetValue = 16f,
                        onAnimateTo = { moveTo = it }
                    ),
                    direction = EnumMoveDirection.RIGHT
                ) {
                    Text(
                        modifier = Modifier
                            .clickable { moveTo = !moveTo }
                            .padding(it),
                        text = "Move With Come Back Animation"
                    )
                }
            }
        }
        item {
            var moveTo by rememberSaveable { mutableStateOf(false) }
            ItemListDemo {
                MoveAnimation(
                    defaultValuesAnimation = DefaultValuesAnimation(
                        animate = moveTo,
                        initValue = 0f,
                        targetValue = 16f,
                        onAnimateTo = {}
                    ),
                    direction = EnumMoveDirection.RIGHT
                ) {
                    Text(
                        modifier = Modifier
                            .clickable { moveTo = !moveTo }
                            .padding(it),
                        text = "MoveAnimation"
                    )
                }
            }
        }
        item {
            var expandTo by rememberSaveable { mutableStateOf(false) }
            ItemListDemo {
                ExpandAnimation(
                    defaultValuesAnimation = DefaultValuesAnimation(
                        animate = expandTo,
                        initValue = 24f,
                        targetValue = 48f,
                        onAnimateTo = {}
                    )
                ) {
                    Icon(
                        modifier = Modifier
                            .clickable { expandTo = !expandTo }
                            .size(it.dp),
                        imageVector = Icons.Outlined.Settings,
                        contentDescription = "Config"
                    )
                }
            }
        }
        item {
            var expandTo by rememberSaveable { mutableStateOf(false) }
            var infinity by rememberSaveable { mutableStateOf(false) }
            ItemListDemo {
                ExpandWithComeBackAnimation(
                    defaultValuesAnimation = DefaultValuesAnimation(
                        infinity = infinity,
                        animate = expandTo,
                        initValue = 24f,
                        targetValue = 48f,
                        onAnimateTo = { expandTo = it }
                    )
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            modifier = Modifier
                                .clickable { infinity = !infinity }
                                .size(it.dp),
                            imageVector = Icons.Outlined.Settings,
                            contentDescription = "Config"
                        )

                        Text(
                            modifier = Modifier
                                .clickable { expandTo = !expandTo },
                            fontSize = it.sp,
                            text = "Hello World!"
                        )
                    }
                }
            }
        }
    }        
