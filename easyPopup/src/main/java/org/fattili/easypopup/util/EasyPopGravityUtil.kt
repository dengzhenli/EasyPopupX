package org.fattili.easypopup.util

import android.view.View
import org.fattili.easypopup.constant.EasyPopGravity

/**
 *  2021/5/7
 * @author dengzhenli
 */
object EasyPopGravityUtil {

    fun getXOff(view: View, gravity: Int, popWidth: Int, margin: Int): Int {
        val location = IntArray(2)
        view.getLocationOnScreen(location)
        val x = location[0] // view距离 屏幕左边的距离（即x轴方向）

        val w = view.width

        return if (gravity and EasyPopGravity.TO_LEFT.code > 0) {
            x - popWidth - margin
        } else if (gravity and EasyPopGravity.TO_RIGHT.code > 0) {
            x + w + margin
        } else if (gravity and EasyPopGravity.ALIGN_LEFT.code > 0) {
            x + margin
        } else if (gravity and EasyPopGravity.ALIGN_RIGHT.code > 0) {
            x + w - -margin
        } else {
            x - (popWidth - w) / 2 + margin
        }
    }

    fun getYOff(view: View, gravity: Int, popHeight: Int, margin: Int): Int {
        val location = IntArray(2)
        view.getLocationOnScreen(location)
        val y = location[1] // view距离 屏幕顶边的距离（即y轴方向）
        val h = view.height

        return if (gravity and EasyPopGravity.TO_ABOVE.code > 0) {
            y - popHeight - margin
        } else if (gravity and EasyPopGravity.TO_BELOW.code > 0) {
            y + h + margin
        } else if (gravity and EasyPopGravity.ALIGN_TOP.code > 0) {
            y + margin
        } else if (gravity and EasyPopGravity.ALIGN_BOTTOM.code > 0) {
            y + h - popHeight - margin
        } else {
            y - (popHeight - h) / 2 + margin
        }
    }

}
