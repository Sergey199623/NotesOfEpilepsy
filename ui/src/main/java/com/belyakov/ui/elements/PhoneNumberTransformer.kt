package com.belyakov.ui.elements

import android.util.Log
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation

class PhoneNumberTransformer(
    private val delim: Char = ' ',
    private val char: Char = 'x',
    val format: String = "xx xxx xxx xx xx"
) : VisualTransformation {
    val TAG = "CustomTransformer"
    private var max = 0
    private var delims = arrayListOf<Int>()
    var limit = 0

    init {
        max = format.count { it == char }

        format.forEachIndexed { index, c ->
            if (c == delim) {
                delims.add(index - delims.size)
            }
        }
    }

    override fun filter(text: AnnotatedString): TransformedText {

        val trimmed =
            if (text.text.length >= max) {
                text.text.substring(
                    0 .. max-1
                )
            } else {
                text.text
            }
        limit = trimmed.length

        val annotatedString = AnnotatedString.Builder().run {
            for (i in trimmed.indices) {
                append(trimmed[i])
                /* if (i == 1 || i == 4 || i == 7 || i == 9) {
                     append(" ")
                 }*/
                if (delims.contains(i + 1) && i != max - 1) {
                    append(delim)
                }
            }

            pushStyle(SpanStyle(color = Color.Transparent))
            append(format.takeLast(format.length - length))
            toAnnotatedString()
        }

        val offsetMapping = object : OffsetMapping {
            override fun originalToTransformed(offset: Int): Int {
                var offsetAddition = 0

                delims.forEach { delimIndex ->

                    if (offset <= delimIndex) {
                        return offset + offsetAddition
                    } else {
                        offsetAddition++
                    }
                }


                Log.d(TAG, "originalToTransformed" + max + offsetAddition)
                return max + offsetAddition
            }

            override fun transformedToOriginal(offset: Int): Int {
                var offsetMinus = 0

                delims.forEach { delimIndex ->

                    if (offset <= delimIndex) {
                        return minOf(limit, offset - offsetMinus)
                        // return
                    } else {
                        offsetMinus--
                    }
                }
                Log.d(TAG, "transformedToOriginal" + max)
                return minOf(limit, max)
            }
        }
        return TransformedText(annotatedString, offsetMapping)
    }
}