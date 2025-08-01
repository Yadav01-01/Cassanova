package com.bussiness.komposecountrycodepicker.utils

import android.telephony.PhoneNumberUtils
import android.text.Selection
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import com.google.i18n.phonenumbers.PhoneNumberUtil
import com.bussiness.komposecountrycodepicker.data.Transformation


internal class PhoneNumberTransformation(
    private val countryCode: String,
) : VisualTransformation {

    private val phoneNumberFormatter =
        PhoneNumberUtil.getInstance().getAsYouTypeFormatter(countryCode)


    override fun filter(text: AnnotatedString): TransformedText {
        val transformation = try {
            reformat(text, Selection.getSelectionEnd(text))
        } catch (e: Exception) {
            // Fallback to the original text if reformatting fails
            Transformation(text.text, List(text.length) { it }, List(text.length) { it })
        }

        return TransformedText(
            AnnotatedString(transformation.formatted ?: text.text),
            object : OffsetMapping {
                override fun originalToTransformed(offset: Int): Int {
                    // Return the original offset if out of bounds
                    return transformation.originalToTransformed[offset.coerceIn(transformation.originalToTransformed.indices)]
                }

                override fun transformedToOriginal(offset: Int): Int = transformation.transformedToOriginal[offset.coerceIn(transformation.transformedToOriginal.indices)]
            },
        )
    }

    /**
     * [reformat] Returns the formatted phone number.
     *
     * @param s The phone number.
     * @param cursor The cursor position.
     */
    private fun reformat(s: CharSequence, cursor: Int): Transformation = try {
        phoneNumberFormatter.clear()

        val curIndex = cursor - 1
        var formatted: String? = null
        var lastNonSeparator = 0.toChar()
        var hasCursor = false

        s.forEachIndexed { index, char ->
            if (PhoneNumberUtils.isNonSeparator(char)) {
                if (lastNonSeparator.code != 0) {
                    formatted = getFormattedNumber(lastNonSeparator, hasCursor)
                    hasCursor = false
                }
                lastNonSeparator = char
            }
            if (index == curIndex) {
                hasCursor = true
            }
        }

        if (lastNonSeparator.code != 0) {
            formatted = getFormattedNumber(lastNonSeparator, hasCursor)
        }

        val originalToTransformed = mutableListOf<Int>()
        val transformedToOriginal = mutableListOf<Int>()
        var specialCharsCount = 0

        formatted?.forEachIndexed { index, char ->
            if (!PhoneNumberUtils.isNonSeparator(char)) {
                specialCharsCount++
            } else {
                originalToTransformed.add(index)
            }
            transformedToOriginal.add(maxOf(index - specialCharsCount, 0))
        }

        // Ensure both lists have a proper end boundary offset
        val lastOriginalOffset = s.length
        val lastTransformedOffset = formatted?.length ?: 0

        originalToTransformed.add(lastTransformedOffset)
        transformedToOriginal.add(lastOriginalOffset)

        Transformation(formatted, originalToTransformed, transformedToOriginal)
    } catch (e: Exception) {
        // Fallback to the original text if reformatting fails
        Transformation(s.toString(), List(s.length) { it }, List(s.length) { it })
    }

    /**
     * [getFormattedNumber] Returns the formatted phone number.
     *
     * @param lastNonSeparator The last non separator character of the phone
     *    number.
     * @param hasCursor If true, the cursor is at the end of the phone number.
     */
    private fun getFormattedNumber(lastNonSeparator: Char, hasCursor: Boolean): String? = if (hasCursor) {
        phoneNumberFormatter.inputDigitAndRememberPosition(lastNonSeparator)
    } else {
        phoneNumberFormatter.inputDigit(lastNonSeparator)
    }
}
