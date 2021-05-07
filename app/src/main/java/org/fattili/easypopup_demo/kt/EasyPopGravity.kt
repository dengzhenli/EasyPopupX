package org.fattili.easypopup_demo.kt

import android.view.View

/**
 *  2021/5/7
 * @author dengzhenli
 */
object EasyPopGravity {
    const val CENTER = 0;       // 0000 0000
    const val TO_ABOVE = 1;     // 0000 0001
    const val TO_BELOW = 2;     // 0000 0010
    const val TO_LEFT = 4;      // 0000 0100
    const val TO_RIGHT = 8;     // 0000 1000

    const val ALIGN_TOP = 16;    //  0001 0000
    const val ALIGN_BOTTOM = 32; //  0010 0000
    const val ALIGN_LEFT = 64;   //  0100 0000
    const val ALIGN_RIGHT = 128;  //  1000 0000

    private const val TAG = "EasyPopGravity"
    fun getXOff(view: View, gravity: Int, popWidth: Int, margin: Int): Int {

        val x = view.x.toInt()
        val w = view.width

        return if (gravity and TO_LEFT > 0) {
            x - popWidth - margin
        } else if (gravity and TO_RIGHT > 0) {
            x + w + margin
        } else if (gravity and ALIGN_LEFT > 0) {
            x + margin
        } else if (gravity and ALIGN_RIGHT > 0) {
            x + w - popWidth - margin
        } else {
            x + w / 2 + margin
        }
    }

    fun getYOff(view: View, gravity: Int, popHeight: Int, margin: Int): Int {

        val y = view.y.toInt()
        val h = view.height

        return if (gravity and TO_ABOVE > 0) {
            y + h + margin
            y + 500
        } else if (gravity and TO_BELOW > 0) {
            y - margin
        } else if (gravity and ALIGN_TOP > 0) {
            y + margin
        } else if (gravity and ALIGN_BOTTOM > 0) {
            y + h - popHeight - margin
        } else {
            y + h / 2 + margin
        }
    }


}
