package org.fattili.easypopup.view.pop

import android.app.Activity
import android.view.Gravity
import android.view.View
import org.fattili.easypopup.view.base.BasePopView

/**
 * 2021/2/22
 * 右侧弹出窗
 * @author dengzhenli
 */
abstract class RightPop : BasePopView {
    constructor(activity: Activity?) : super(activity!!) {
        gravity = Gravity.RIGHT
    }

    constructor(activity: Activity?, view: View?) : super(activity!!, view!!) {}

    override fun initView(view: View?) {}
    override fun initData() {}
    override fun onPopDismiss() {}
}