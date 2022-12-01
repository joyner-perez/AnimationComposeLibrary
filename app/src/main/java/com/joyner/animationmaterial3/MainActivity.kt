package com.joyner.animationmaterial3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.joyner.animationcomposelibrary.core.DefaultValuesAnimation
import com.joyner.animationcomposelibrary.expand.ExpandAnimation
import com.joyner.animationcomposelibrary.expand.ExpandWithComeBackAnimation
import com.joyner.animationcomposelibrary.move.EnumMoveDirection
import com.joyner.animationcomposelibrary.move.MoveAnimation
import com.joyner.animationcomposelibrary.move.MoveWithComeBackAnimation
import com.joyner.animationcomposelibrary.rotation.*
import com.joyner.animationmaterial3.ui.theme.AnimationMaterial3Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AnimationMaterial3Theme {
                // A surface container using the 'background' color from the theme
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    AnimationExamples()
                }
            }
        }
    }
}

@Composable
fun AnimationExamples() {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize(),
        contentPadding = PaddingValues(16.dp)
    ) {
        item {
            ItemListDemo {
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
            }
        }
        item {
            ItemListDemo {
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
            }
        }
        item {
            ItemListDemo {
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
            }
        }
        item {
            ItemListDemo {
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
            }
        }
        item {
            var rotationDegreeTo by rememberSaveable { mutableStateOf(false) }
            var infinity by rememberSaveable { mutableStateOf(false) }
            ItemListDemo {
                AngleRotationAnimation(
                    infinity = infinity,
                    delayInfinityMillis = 1000,
                    rotationDegreeTo = rotationDegreeTo,
                    onRotateDegreeTo = { rotationDegreeTo = it },
                ) {
                    Text(
                        modifier = Modifier
                            .clickable { rotationDegreeTo = !rotationDegreeTo }
                            .graphicsLayer(rotationZ = it),
                        text = "AngleRotationAnimation"
                    )
                }
            }
        }
        item {
            var moveTo by rememberSaveable { mutableStateOf(false) }
            var infinity by rememberSaveable { mutableStateOf(false) }
            ItemListDemo {
                MoveWithComeBackAnimation(
                    defaultValuesAnimation = DefaultValuesAnimation(
                        infinity = infinity,
                        animate = moveTo,
                        initValue = 0f,
                        targetValue = 16f
                    ),
                    direction = EnumMoveDirection.RIGHT,
                    onMoveTo = { moveTo = it },
                ) {
                    Text(
                        modifier = Modifier
                            .clickable { moveTo = !moveTo }
                            .padding(it),
                        text = "MoveWithComeBackAnimation"
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
                        targetValue = 16f
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
                        targetValue = 48f
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
                        targetValue = 48f
                    ),
                    onExpandTo = { expandTo = it }
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
}

@Composable
fun ItemListDemo(content: @Composable () -> Unit) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 16.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        content()
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    AnimationMaterial3Theme {
        AnimationExamples()
    }
}