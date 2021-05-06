package org.fattili.easypopup_demo.kt

import android.app.Activity
import android.view.View
import kotlinx.android.synthetic.main.pop_example.view.*
import org.fattili.easypopup.view.pop.TopPop
import org.fattili.easypopup_demo.R

/**
 * 2021/2/22
 *
 * @author dengzhenli
 */
class TopTestPop : TopPop {
    constructor(activity: Activity) : super(activity) {}

    override fun onPopCreated(view: View?) {
        pop_example_text.text = "我是顶部弹出窗"
    }

    override fun getLayoutId(): Int {
        return R.layout.top_pop_test
    }

}