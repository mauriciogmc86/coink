package com.coink.widgets

import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import com.coink.R

class NumberPad @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : ConstraintLayout(context, attrs) {

    private val itemNumberRowOne by lazy { findViewById<ItemNumberRow>(R.id.itemNumberRow_one) }
    private val itemNumberRowTwo by lazy { findViewById<ItemNumberRow>(R.id.itemNumberRow_two) }
    private val itemNumberRowThree by lazy { findViewById<ItemNumberRow>(R.id.itemNumberRow_three) }
    private val itemNumberButtonsRow by lazy { findViewById<ItemNumberButtonsRow>(R.id.layout_buttons) }

    private var onValidate: () -> Unit = {}
    private var onTextValue: (value: String) -> Unit = {}

    private var currentValue: StringBuilder = StringBuilder("")

    init {
        inflate(context, R.layout.item_number_pad, this)
        setListeners()
    }

    fun setListener(
        onTextValue: (value: String) -> Unit,
        onValidate: () -> Unit,
        initValue: String = ""
    ) {
        this.onValidate = onValidate
        this.onTextValue = onTextValue
        this.currentValue = StringBuilder(initValue)
    }

    private fun setListeners() {
        itemNumberRowOne.onTextValue { setNumber(it) }
        itemNumberRowTwo.onTextValue { setNumber(it) }
        itemNumberRowThree.onTextValue { setNumber(it) }
        itemNumberButtonsRow.setListener(
            onClear = {
                currentValue.setLength(if (currentValue.length > 1) currentValue.length - 1 else currentValue.length)
                onTextValue.invoke(currentValue.toString())
            },
            onTextValue = { setNumber(it) },
            onValidate = { onValidate.invoke() }
        )
    }

    private fun setNumber(value: String) {
        currentValue.append(value)
        onTextValue.invoke(currentValue.toString())
    }
}