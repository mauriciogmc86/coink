package com.coink.widgets

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import com.coink.R
import com.google.android.material.button.MaterialButton

class ItemNumberRow @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : LinearLayout(context, attrs) {

    private val buttonPadOne by lazy { findViewById<MaterialButton>(R.id.button_pad_one) }
    private val buttonPadTwo by lazy { findViewById<MaterialButton>(R.id.button_pad_two) }
    private val buttonPadThree by lazy { findViewById<MaterialButton>(R.id.button_pad_three) }

    private var onTextValue: (value: String) -> Unit = {}

    init {
        inflate(context, R.layout.item_number_row, this)
        if (attrs != null) {
            val style = context.obtainStyledAttributes(attrs, R.styleable.itemNumberRow)
            if (style.hasValue(R.styleable.itemNumberRow_text_position_one)) {
                buttonPadTwo.text = style.getString(R.styleable.itemNumberRow_text_position_one)
            }
            if (style.hasValue(R.styleable.itemNumberRow_text_position_two)) {
                buttonPadThree.text = style.getString(R.styleable.itemNumberRow_text_position_two)
            }
            if (style.hasValue(R.styleable.itemNumberRow_text_position_zero)) {
                buttonPadOne.text = style.getString(R.styleable.itemNumberRow_text_position_zero)
            }
        }
        setListeners()
    }

    fun onTextValue(onTextValue: (value: String) -> Unit) {
        this.onTextValue = onTextValue
    }

    private fun setListeners() {
        buttonPadOne.setOnClickListener { onTextValue.invoke(buttonPadOne.text.toString()) }
        buttonPadTwo.setOnClickListener { onTextValue.invoke(buttonPadTwo.text.toString()) }
        buttonPadThree.setOnClickListener { onTextValue.invoke(buttonPadThree.text.toString()) }
    }
}