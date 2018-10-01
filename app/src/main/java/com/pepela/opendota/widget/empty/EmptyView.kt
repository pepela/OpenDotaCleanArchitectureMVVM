package com.pepela.opendota.widget.empty

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.RelativeLayout
import com.pepela.opendota.R
import kotlinx.android.synthetic.main.view_empty.view.*

class EmptyView : RelativeLayout {

    var listener: EmptyListener? = null

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
        LayoutInflater.from(context).inflate(R.layout.view_empty, this)
        check_again_btn.setOnClickListener { listener?.onCheckAgainClicked() }
    }

}
