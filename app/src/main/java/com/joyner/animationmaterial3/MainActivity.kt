package com.joyner.animationmaterial3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.joyner.animationcomposelibrary.expand.ExpandAnimation
import com.joyner.animationcomposelibrary.expand.ExpandWithComeBackAnimation
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
            ItemListDemo {
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
            }
        }
        item {
            ItemListDemo {
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
            }
        }
        item {
            ItemListDemo {
                MoveAnimation {
                    Text(
                        modifier = Modifier
                            .padding(it),
                        text = "MoveAnimation"
                    )
                }
            }
        }
        item {
            ItemListDemo {
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
            }
        }
        item {
            ItemListDemo {
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