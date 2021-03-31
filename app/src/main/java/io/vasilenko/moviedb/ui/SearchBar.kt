package io.vasilenko.moviedb.ui

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import android.widget.FrameLayout
import androidx.core.view.isVisible
import kotlinx.android.synthetic.main.search_toolbar.view.*
import io.vasilenko.moviedb.R

class SearchBar @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : FrameLayout(context, attrs, defStyle) {

    private val editText: EditText by lazy { searchEditText }

    private var hint: String = ""
    private var isCancelVisible: Boolean = true

    init {
        LayoutInflater.from(context).inflate(R.layout.search_toolbar, this)
        if (attrs != null) {
            context.obtainStyledAttributes(attrs, R.styleable.SearchBar).apply {
                hint = getString(R.styleable.SearchBar_hint).orEmpty()
                isCancelVisible = getBoolean(R.styleable.SearchBar_cancel_visible, true)
                recycle()
            }
        }
    }

    fun setText(text: String?) {
        this.editText.setText(text)
    }

    fun clear() {
        this.editText.setText("")
    }

    override fun onFinishInflate() {
        super.onFinishInflate()
        searchEditText.hint = hint
        deleteTextButton.setOnClickListener {
            searchEditText.text.clear()
        }
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        searchEditText.afterTextChanged { text ->
            if (!text.isNullOrEmpty() && !deleteTextButton.isVisible) {
                deleteTextButton.visibility = View.VISIBLE
            }
            if (text.isNullOrEmpty() && deleteTextButton.isVisible) {
                deleteTextButton.visibility = View.GONE
            }
        }
    }
}
