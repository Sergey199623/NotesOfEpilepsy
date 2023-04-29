package com.belyakov.auth.util

object PhoneNumberFormatter {
    private const val MASK = "+X-(XXX)-XXX-XX-XX"
    private const val DIGIT = 'X'

    fun applyMask(value: String): String {
        var newValue = ""
        var maskIndex = 0
        value.forEach { char ->
            if (MASK[maskIndex] == DIGIT) {
                newValue += char
            } else {
                newValue += MASK[maskIndex]
                maskIndex++
                newValue += char
            }
            maskIndex++
        }
        while (maskIndex < MASK.length) {
            if (MASK[maskIndex] == DIGIT) {
                break
            }
            newValue += MASK[maskIndex]
            maskIndex++
        }
        return newValue
    }
}