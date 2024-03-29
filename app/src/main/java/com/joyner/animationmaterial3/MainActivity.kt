package com.joyner.animationmaterial3

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.joyner.animationcomposelibrary.color.AlphaAnimation
import com.joyner.animationcomposelibrary.color.AlphaWithComeBackAnimation
import com.joyner.animationcomposelibrary.core.DefaultComplexAnimation
import com.joyner.animationcomposelibrary.core.DefaultValuesAnimation
import com.joyner.animationcomposelibrary.expand.ExpandAnimation
import com.joyner.animationcomposelibrary.expand.ExpandWithComeBackAnimation
import com.joyner.animationcomposelibrary.move.EnumMoveDirection
import com.joyner.animationcomposelibrary.move.MoveAnimation
import com.joyner.animationcomposelibrary.move.MoveWithComeBackAnimation
import com.joyner.animationcomposelibrary.rotation.AxisRotationAnimation
import com.joyner.animationcomposelibrary.rotation.AxisXYRotationAnimation
import com.joyner.animationcomposelibrary.rotation.MiddleExeXYRotationAnimation
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
                    AnimationExamples(LocalContext.current)
                }
            }
        }
    }
}

@Composable
fun AnimationExamples(context: Context) {
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
                        onAnimationEnd = { Toast.makeText(context, "Animation end", Toast.LENGTH_SHORT).show() }
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
                        onAnimateTo = { rotationDegreeTo = it },
                        onAnimationEnd = { Toast.makeText(context, "Animation end", Toast.LENGTH_SHORT).show() }
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
                        animate = rotationDegreeTo,
                        initValue = 0f,
                        targetValue = 45f,
                        onAnimateTo = { rotationDegreeTo = it },
                        onAnimationEnd = { Toast.makeText(context, "Animation end", Toast.LENGTH_SHORT).show() }
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
                        animate = rotationDegreeTo,
                        onAnimateTo = { rotationDegreeTo = it },
                        onAnimationEnd = { Toast.makeText(context, "Animation end", Toast.LENGTH_SHORT).show() }
                    )
                ) { xRotation: Float, yRotation: Float ->
                    Text(
                        modifier = Modifier
                            .clickable { rotationDegreeTo = !rotationDegreeTo }
                            .graphicsLayer(
                                rotationX = xRotation,
                                rotationY = yRotation
                            ),
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
                        animate = rotationDegreeTo,
                        onAnimateTo = { rotationDegreeTo = it },
                        onAnimationEnd = { Toast.makeText(context, "Animation end", Toast.LENGTH_SHORT).show() }
                    )
                ) { xRotation: Float, yRotation: Float ->
                    Text(
                        modifier = Modifier
                            .clickable { rotationDegreeTo = !rotationDegreeTo }
                            .graphicsLayer(
                                rotationX = xRotation,
                                rotationY = yRotation
                            ),
                        text = "Middle Axis XY Rotation Animation")
                }
            }
        }
        item {
            var moveTo by rememberSaveable { mutableStateOf(false) }
            ItemListDemo {
                MoveWithComeBackAnimation(
                    defaultValuesAnimation = DefaultValuesAnimation(
                        animate = moveTo,
                        initValue = 0f,
                        targetValue = 16f,
                        onAnimateTo = { moveTo = it },
                        onAnimationEnd = { Toast.makeText(context, "Animation end", Toast.LENGTH_SHORT).show() }
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
                        onAnimateTo = {},
                        onAnimationEnd = { Toast.makeText(context, "Animation end", Toast.LENGTH_SHORT).show() }
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
                        onAnimateTo = {},
                        onAnimationEnd = { Toast.makeText(context, "Animation end", Toast.LENGTH_SHORT).show() }
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
                        onAnimateTo = { expandTo = it },
                        onAnimationEnd = { Toast.makeText(context, "Animation end", Toast.LENGTH_SHORT).show() }
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
        item {
            var expandTo by rememberSaveable { mutableStateOf(false) }
            ItemListDemo {
                AlphaAnimation(
                    defaultValuesAnimation = DefaultValuesAnimation(
                        infinity = true,
                        animate = expandTo,
                        initValue = 1f,
                        targetValue = 0f,
                        onAnimateTo = {},
                        onAnimationEnd = { Toast.makeText(context, "Animation end", Toast.LENGTH_SHORT).show() }
                    )
                ) {
                    Icon(
                        modifier = Modifier
                            .clickable { expandTo = !expandTo }
                            .alpha(it),
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
                AlphaWithComeBackAnimation(
                    defaultValuesAnimation = DefaultValuesAnimation(
                        infinity = infinity,
                        animate = expandTo,
                        initValue = 1f,
                        targetValue = 0f,
                        onAnimateTo = { expandTo = it },
                        onAnimationEnd = {}
                    )
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            modifier = Modifier
                                .clickable { infinity = !infinity }
                                .alpha(it),
                            imageVector = Icons.Outlined.Settings,
                            contentDescription = "Config"
                        )

                        Text(
                            modifier = Modifier
                                .clickable { expandTo = !expandTo }
                                .alpha(it),
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
    Row(
        modifier = Modifier
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
        AnimationExamples(LocalContext.current)
    }
}