package org.fattili.easypopup.view.pop

import android.app.Activity
import android.view.Gravity
import android.view.View
import org.fattili.easypopup.view.EasyPop

/**
 * 2021/2/22
 * 顶部弹出窗
 * @author dengzhenli
 */
abstract class TopPop : EasyPop {
    constructor(activity: Activity?) : super(activity!!) {
        gravity = Gravity.TOP
    }

    constructor(activity: Activity?, view: View?) : super(activity!!, view!!) {}

    override fun initView(view: View?) {}
    override fun initData() {}
}