package org.fattili.easypopup

import android.view.View
import android.widget.PopupWindow

/**
 * 自定义PopupWindow
 * Created by dengzhenli on 2021/01/23.
 */
class BasePopupWindow : PopupWindow {

    constructor(
        contentView: View?,
        width: Int,
        height: Int,
        focusable: Boolean
    ) : super(contentView, width, height, focusable) {
    }
}