package com.pepela.opendota.widget.error

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.RelativeLayout
import com.pepela.opendota.R
import kotlinx.android.synthetic.main.view_error.view.*

class ErrorView : RelativeLayout {

    var listener: ErrorListener? = null

    constructor(context: Context?) : super(context) {
        init()
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        init()
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int)
            : super(context, attrs, defStyleAttr) {
        init()
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int)
            : super(context, attrs, defStyleAttr, defStyleRes) {
        init()
    }

    private fun init() {
        LayoutInflater.from(context).inflate(R.layout.view_error, this)
        retry_btn.setOnClickListener { listener?.onRetryClicked() }
    }
}
