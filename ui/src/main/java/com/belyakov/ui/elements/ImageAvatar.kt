package com.belyakov.ui.elements

import android.graphics.Matrix
import android.graphics.RectF
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.asComposePath
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
//import androidx.core.graphics.PathParser

//val SquireShape: Shape = object : Shape {
//    override fun createOutline(
//        size: Size,
//        layoutDirection: LayoutDirection,
//        density: Density
//    ): Outline {
//        val path = pathFromSvgData(
//            size,
//            "M0,10C0,1.765 1.765,0 10,0C18.235,0 20,1.765 20,10C20,18.235 18.235,20 10,20C1.765,20 0,18.235 0,10Z",
//            false,
//            0f)
//        return Outline.Generic(path = path)
//    }
//}

//fun pathFromSvgData(
//    size: Size,
//    pathData: String,
//    proportional: Boolean = true,
//    rotate: Float,
//): Path {
//    val path = PathParser.createPathFromPathData(pathData)
//    val rectF = RectF()
//    path.computeBounds(rectF, true)
//    val matrix = Matrix()
//    val scale = minOf(size.width / rectF.width(), size.height / rectF.height())
//    if (proportional) {
//        matrix.setScale(scale, scale)
//        // matrix.postRotate(rotate)
//    } else {
//        matrix.setScale(size.width / rectF.width(), size.height / rectF.height())
//        // matrix.postRotate(rotate)
//    }
//    path.transform(matrix)
//    return path.asComposePath()
//}