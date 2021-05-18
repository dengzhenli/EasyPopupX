package org.fattili.easypopup.view.pop

import android.app.Activity
import android.view.Gravity
import org.fattili.easypopup.R
import org.fattili.easypopup.view.EasyPop

/**
 * 2021/2/22
 * 右侧弹出窗
 * @author dengzhenli
 */
abstract class RightPop : EasyPop {
    constructor(activity: Activity) : super(activity) {
        setGravity(Gravity.RIGHT)
        setAnimationStyle(R.style.ep_common_pop_right_theme)
    }
}