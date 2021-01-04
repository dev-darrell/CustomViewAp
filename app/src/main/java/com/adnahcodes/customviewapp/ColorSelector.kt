package com.adnahcodes.customviewapp

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.color_selector.view.*


class ColorSelector @JvmOverloads
constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0,
            defStyleRes: Int = 0): LinearLayout(context, attrs, defStyleAttr, defStyleRes) {

    private var listOfColors = listOf(Color.BLUE, Color.RED, Color.YELLOW, Color.MAGENTA)
    private var selectedColorIndex = 0
    private val colorEnabled = color_enabled

    init {
        orientation = HORIZONTAL
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        inflater.inflate(R.layout.color_selector, this)

        selected_color.setBackgroundColor(listOfColors[selectedColorIndex])

        selector_left_arrow.setOnClickListener {
            selectPreviousColor()
        }
        selector_right_arrow.setOnClickListener {
            selectNextColor()
        }

//        colorEnabled.setOnCheckedChangeListener { buttonView, isChecked ->
//            broadcastColor()
//        }
    }
    var colorSelectListener: ColorSelectListener? = null

    interface ColorSelectListener {
        fun onColorSelected(color: Int)
    }

    var selectedColorValue: Int = android.R.color.transparent
        set(value) {
            var index = listOfColors.indexOf(value)
            if (index == -1) {
                colorEnabled.isChecked = false
                index = 0
            } else {
                colorEnabled.isChecked = true
            }
            selectedColorIndex = index
            selected_color.setBackgroundColor(listOfColors[selectedColorIndex])
        }

    private fun selectPreviousColor() {
        if (selectedColorIndex == 0) {
            selectedColorIndex = listOfColors.lastIndex
        } else {
            selectedColorIndex--
        }
        selected_color.setBackgroundColor(listOfColors[selectedColorIndex])
        broadcastColor()
    }

    private fun selectNextColor() {
        if(selectedColorIndex == listOfColors.lastIndex) {
            selectedColorIndex = 0
        } else {
            selectedColorIndex++
        }
        selected_color.setBackgroundColor(listOfColors[selectedColorIndex])
        broadcastColor()
    }

    private fun broadcastColor() {
        val color = if (colorEnabled.isChecked)
            listOfColors[selectedColorIndex]
        else
            Color.TRANSPARENT

        this.colorSelectListener?.onColorSelected(color)
    }
}