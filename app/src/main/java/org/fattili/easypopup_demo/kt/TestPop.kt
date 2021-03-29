package org.fattili.easypopup_demo.kt

import android.app.Activity
import android.view.View
import kotlinx.android.synthetic.main.pop_example.view.*
import org.fattili.easypopup.view.base.BasePopView
import org.fattili.easypopup_demo.R

/**
 * 2021/2/22
 *
 * @author dengzhenli
 */
class TestPop(activity: Activity) : BasePopView(activity) {
    override fun initView(view: View?) {
        pop_example_text.text = "我是普通弹出窗"
    }
    override fun initData() {}
    override fun getLayoutId(): Int {
        return R.layout.pop_test
    }

    override fun onPopDismiss() {}
    override fun outClickable(): Boolean {
        return true
    }
}