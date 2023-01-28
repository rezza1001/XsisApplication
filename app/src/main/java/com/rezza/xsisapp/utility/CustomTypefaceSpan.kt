package com.rezza.xsisapp.utility

import android.graphics.Color
import android.graphics.Paint
import android.text.style.TypefaceSpan
import android.graphics.Typeface
import com.rezza.xsisapp.utility.CustomTypefaceSpan
import android.text.TextPaint

class CustomTypefaceSpan : TypefaceSpan {
    private val newType: Typeface

    constructor(family: String?, type: Typeface) : super(family) {
        newType = type
    }

    constructor(family: String?, type: Typeface, color: Int) : super(family) {
        newType = type
        Companion.color = color
    }

    override fun updateDrawState(ds: TextPaint) {
        applyCustomTypeFace(ds, newType)
    }

    override fun updateMeasureState(paint: TextPaint) {
        applyCustomTypeFace(paint, newType)
    }

    companion object {
        private var color = Color.BLACK
        private fun applyCustomTypeFace(paint: Paint, tf: Typeface) {
            val oldStyle: Int
            val old = paint.typeface
            oldStyle = old?.style ?: 0
            val fake = oldStyle and tf.style.inv()
            if (fake and Typeface.BOLD != 0) {
                paint.isFakeBoldText = true
            }
            if (fake and Typeface.ITALIC != 0) {
                paint.textSkewX = -0.25f
            }
            paint.color = color
            paint.typeface = tf
        }
    }
}