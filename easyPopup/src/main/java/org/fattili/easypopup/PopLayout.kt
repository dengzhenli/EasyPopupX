package org.fattili.easypopup

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout

/**
 * 2021/2/23
 *
 * @author dengzhenli
 */
class PopLayout : LinearLayout {
    constructor(context: Context?) : super(context) {}
    constructor(context: Context?, attrs: AttributeSet?) : super(
        context,
        attrs
    ) {
    }

    constructor(
        context: Context?,
        attrs: AttributeSet?,
        defStyleAttr: Int
    ) : super(context, attrs, defStyleAttr) {
    }
}