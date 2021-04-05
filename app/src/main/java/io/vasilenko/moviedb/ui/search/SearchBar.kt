package io.vasilenko.moviedb.ui.search

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import android.widget.FrameLayout
import androidx.core.view.isVisible
import io.vasilenko.moviedb.R
import io.vasilenko.moviedb.databinding.SearchToolbarBinding
import io.vasilenko.moviedb.ui.common.afterTextChanged

class SearchBar @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : FrameLayout(context, attrs, defStyle) {

    private val editText: EditText by lazy { binding.searchEditText }

    private var binding: SearchToolbarBinding =
        SearchToolbarBinding.inflate(LayoutInflater.from(context), this, true)

    private var hint: String = ""
    private var isCancelVisible: Boolean = true

    val searchEditText
        get() = binding.searchEditText

    init {
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

        with(binding) {
            searchEditText.hint = hint
            deleteTextButton.setOnClickListener {
                searchEditText.text.clear()
            }
        }
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()

        with(binding) {
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
}
