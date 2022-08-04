package com.coink.widgets

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import androidx.appcompat.widget.AppCompatImageButton
import com.coink.R
import com.google.android.material.button.MaterialButton
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ItemNumberButtonsRow @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : LinearLayout(context, attrs) {

    private val buttonCheck by lazy { findViewById<FloatingActionButton>(R.id.button_check) }
    private val buttonPadTwo by lazy { findViewById<MaterialButton>(R.id.button_pad_two) }
    private val buttonPadRemove by lazy { findViewById<AppCompatImageButton>(R.id.button_pad_remove) }

    private var onClear: () -> Unit = {}
    private var onValidate: () -> Unit = {}
    private var onTextValue: (value: String) -> Unit = {}

    init {
        inflate(context, R.layout.item_number_buttons_row, this)
        setListeners()
    }

    fun setListener(
        onTextValue: (value: String) -> Unit,
        onValidate: () -> Unit,
        onClear: () -> Unit
    ) {
        this.onClear = onClear
        this.onValidate = onValidate
        this.onTextValue = onTextValue
    }

    private fun setListeners() {
        buttonCheck.setOnClickListener { onValidate.invoke() }
        buttonPadTwo.setOnClickListener { onTextValue.invoke(buttonPadTwo.text.toString()) }
        buttonPadRemove.setOnClickListener { onClear.invoke() }
    }
}