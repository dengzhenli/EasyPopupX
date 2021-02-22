package org.fattili.easypopup

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.PopupWindow

/**
 * 自定义PopupWindow
 * Created by dengzhenli on 2021/01/23.
 */
class BasePopupWindow : PopupWindow {

    constructor() {}

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

    constructor(
        context: Context?,
        attrs: AttributeSet?,
        defStyleAttr: Int,
        defStyleRes: Int
    ) : super(context, attrs, defStyleAttr, defStyleRes) {
    }

    constructor(contentView: View?) : super(contentView) {}
    constructor(width: Int, height: Int) : super(width, height) {}
    constructor(contentView: View?, width: Int, height: Int) : super(
        contentView,
        width,
        height
    ) {
    }

    constructor(
        contentView: View?,
        width: Int,
        height: Int,
        focusable: Boolean
    ) : super(contentView, width, height, focusable) {
    }
}