package com.example.linguaguess.ui.extensions

import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavController



fun Modifier.withLinearGradient(color1: Color, color2: Color): Modifier {
    return this
        .graphicsLayer(alpha = 0.99f)
        .drawWithCache {
            onDrawWithContent {
                drawContent()
                drawRect(
                    Brush.linearGradient(
                        colors = listOf(
                            color1,
                            color2,
                        )
                    ), blendMode = BlendMode.SrcAtop
                )
            }
        }
}



fun NavController.safeNavigate(from: LifecycleOwner, route: String) {
    if (from.lifecycle.currentState == Lifecycle.State.RESUMED) {
        this.navigate(route)
    }
}
