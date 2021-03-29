package org.fattili.easypopup.view.dialog

import android.app.Activity
import android.view.View
import org.fattili.easypopup.view.base.BasePopView

/**
 * 2021/2/22
 * 通用dialog
 * @author dengzhenli
 */
abstract class DialogPop : BasePopView {
    override fun initView(view: View?) {}

    override fun initData() {}

    override fun onPopDismiss() {}

    constructor(activity: Activity?) : super(activity!!) {
    }

    constructor(activity: Activity?, gravity: Int, width: Int, height: Int) : super(activity!!) {
        this.gravity = gravity
        popupWidth = width
        popupHeight = height
        marginWidth = width
        marginHeight = height
    }

}