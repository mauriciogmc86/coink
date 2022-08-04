package com.coink.widgets

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.coink.R

class ItemCoinkTextHorizontal @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : ConstraintLayout(context, attrs) {

    private val textViewTitle by lazy { findViewById<AppCompatTextView>(R.id.textView_title) }
    private val textViewMessage by lazy { findViewById<AppCompatTextView>(R.id.textView_message) }
    private val imageViewPic by lazy { findViewById<AppCompatImageView>(R.id.imageView_pic) }

    init {
        inflate(context, R.layout.item_coink_text_horizontal, this)
        if (attrs != null) {
            val style = context.obtainStyledAttributes(attrs, R.styleable.coinkTextView)
            if (style.hasValue(R.styleable.coinkTextView_text_title)) {
                textViewTitle.text = style.getString(R.styleable.coinkTextView_text_title)
            } else {
                textViewTitle.visibility = GONE
            }
            if (style.hasValue(R.styleable.coinkTextView_text_message)) {
                textViewMessage.text = style.getString(R.styleable.coinkTextView_text_message)
            }
            if (style.hasValue(R.styleable.coinkTextView_icon_drawable)) {
                val drawable = style.getResourceId(R.styleable.coinkTextView_icon_drawable, 0)
                imageViewPic.setImageResource(drawable)
            }
            style.recycle()
        }
    }
}