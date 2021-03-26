package org.fattili.easypopup_demo.kt

import android.app.Activity
import android.view.View
import org.fattili.easypopup.view.pop.TopPop
import org.fattili.easypopup_demo.R

/**
 * 2021/2/22
 *
 * @author dengzhenli
 */
class TopTestPop : TopPop {
    constructor(activity: Activity?) : super(activity) {}
    constructor(activity: Activity?, view: View?) : super(activity, view) {}

    override fun initView(view: View?) {}
    override fun initData() {}
    override fun getLayoutId(): Int {
        return R.layout.top_pop_test
    }

    override fun onPopDismiss() {}
    override fun outClickable(): Boolean {
        return true
    }
}