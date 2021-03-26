package org.fattili.easypopup_demo.kt

import android.app.Activity
import android.view.View
import org.fattili.easypopup.view.base.BasePopView
import org.fattili.easypopup_demo.R

/**
 * 2021/2/22
 *
 * @author dengzhenli
 */
class TestPop(activity: Activity?) : BasePopView(activity!!) {
    override fun initView(view: View?) {}
    override fun initData() {}
    override fun getLayoutId(): Int {
        return R.layout.pop_test
    }

    override fun onPopDismiss() {}
    override fun outClickable(): Boolean {
        return false
    }
}