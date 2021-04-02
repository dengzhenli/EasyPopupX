package org.fattili.easypopup_demo.kt

import android.app.Activity
import org.fattili.easypopup.view.dialog.DialogPop
import org.fattili.easypopup_demo.R

/**
 * 2021/4/2
 *
 * @author dengzhenli
 */
class DialogTest : DialogPop {
    constructor(activity: Activity) : super(activity) {}
    constructor(activity: Activity, gravity: Int, width: Int, height: Int) : super(
        activity,
        gravity,
        width,
        height
    ) {
    }

    override fun getContentLayoutId(): Int {
        return R.layout.pop_example
    }

    override fun outClickable(): Boolean {
        return true
    }

    override fun useBackGround(): Boolean {
        return false
    }
}