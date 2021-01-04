package com.adnahcodes.customviewapp

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.util.Log
import android.util.TypedValue
import androidx.appcompat.R

class LabelledRatingBar @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null,
                                                  defStyleAttr: Int = R.attr.ratingBarStyle):
        androidx.appcompat.widget.AppCompatRatingBar(context, attrs, defStyleAttr) {

    private var currentNumStars: Int = 0
    private var labels: ArrayList<Int> = arrayListOf()
    private var labelPaint: Paint

    private var horizontalX = 50f
    private var verticalY = 150f
    private var spacing = toDp(43)
    private var spacing2 = 0
    private var labelSize = toDp(20)
    private var labelColor = Color.BLACK


    init {
        currentNumStars = numStars

        for (i in 1..currentNumStars){
            labels.add(i)
        }

        labelPaint = Paint()
        labelPaint.apply {
            style = Paint.Style.FILL
            isAntiAlias = true
            setColor(labelColor)
            textSize = labelSize
        }

        spacing2 = width
        Log.i(Companion.TAG, "WIDTH: $spacing2")
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.let {
            val saveCount = canvas.save()
            for (i in 0 until currentNumStars){
                if (i == 0) {
                    canvas.drawText(labels[i].toString(), spacing/2, verticalY, labelPaint)
                    canvas.translate(spacing + toDp(20), 0f)
                } else {
                    canvas.drawText(labels[i].toString(), 0f, verticalY, labelPaint)
                    canvas.translate(spacing + toDp(5), 0f)
                }
//                spacing += 30

            }
        }
    }

    private fun toDp(value: Int): Float {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, value.toFloat(),
        context.resources.displayMetrics)
    }

    companion object {
        private const val TAG = "LabelledRatingBar"
    }
}