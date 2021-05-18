package org.fattili.easypopup.view.pop

import android.app.Activity
import android.view.Gravity
import org.fattili.easypopup.R
import org.fattili.easypopup.view.EasyPop

/**
 * 2021/2/22
 * 底部弹出窗
 * @author dengzhenli
 */
abstract class BottomPop : EasyPop {
    constructor(activity: Activity) : super(activity) {
        setGravity(Gravity.BOTTOM)
        setAnimationStyle(R.style.ep_common_pop_bottom_theme)
    }
}