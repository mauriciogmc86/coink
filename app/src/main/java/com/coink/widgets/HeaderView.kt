package com.coink.widgets

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageButton
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import com.coink.R
import com.google.android.material.appbar.AppBarLayout

class HeaderView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : AppBarLayout(context, attrs) {

    private val textViewTitle by lazy { findViewById<AppCompatTextView>(R.id.textView_title) }
    private val imageViewIcon by lazy { findViewById<AppCompatImageView>(R.id.imageView_step_bar) }
    private val imageButtonBack by lazy { findViewById<AppCompatImageButton>(R.id.imageButton_back) }

    init {
        inflate(context, R.layout.item_header, this)
        if (attrs != null) {
            val style = context.obtainStyledAttributes(attrs, R.styleable.headerView)
            if (style.hasValue(R.styleable.headerView_header_title)) {
                textViewTitle.text = style.getString(R.styleable.headerView_header_title)
            }
            if (style.hasValue(R.styleable.headerView_header_drawable)) {
                val drawable = style.getResourceId(R.styleable.headerView_header_drawable, -1)
                if (drawable != -1) {
                    imageViewIcon.setImageResource(drawable)
                }
            }
            style.recycle()
        }
    }

    fun onBackPressed(action: () -> Unit) {
        imageButtonBack.setOnClickListener { action.invoke() }
    }
}