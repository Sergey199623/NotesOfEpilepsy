package com.belyakov.notesforepilepsy.utils

import android.app.Activity
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.onSizeChanged
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.belyakov.ui.elements.DefaultToolbar

fun hideSystemUi(activity: Activity) {
    val window = activity.window
    WindowCompat.setDecorFitsSystemWindows(window, false)
    WindowInsetsControllerCompat(window, window.decorView).let { controller ->
        controller.hide(WindowInsetsCompat.Type.systemBars())
        controller.systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
    }
}

fun showSystemUi(activity: Activity) {
    val window = activity.window ?: return
    WindowCompat.setDecorFitsSystemWindows(window, true)
    WindowInsetsControllerCompat(
        window,
        window.decorView
    ).show(WindowInsetsCompat.Type.systemBars())
}

@Composable
fun MeasureDefaultToolbarHeight(onToolbarHeightMeasured: (Int) -> Unit) {
    Layout(
        content = { DefaultToolbar(isMainScreen = false, isShowBackIconNeeded = false) },
        measurePolicy = { measurables, constraints ->
            val placeable = measurables.first().measure(constraints)
            layout(placeable.width, placeable.height) {
                placeable.placeRelative(0, 0)
            }
        },
        modifier = Modifier
            .fillMaxWidth()
            .onSizeChanged { onToolbarHeightMeasured(it.height) }
    )
}