package org.fattili.easypopup.constant

import android.view.View

/**
 *  2021/5/7
 * @author dengzhenli
 */
enum class EasyPopGravity(var code: Int) {

    CENTER(0),       // 0000 0000
    TO_ABOVE(1),     // 0000 0001
    TO_BELOW(2),     // 0000 0010
    TO_LEFT(4),      // 0000 0100
    TO_RIGHT(8),     // 0000 1000

    ALIGN_TOP(16),    //  0001 0000
    ALIGN_BOTTOM(32), //  0010 0000
    ALIGN_LEFT(64),   //  0100 0000
    ALIGN_RIGHT(128); //  1000 0000


    operator fun plus(b: EasyPopGravity): Int {
        return this.code or b.code
    }
}
