package org.fattili.easypopup.util

import android.content.Context

/**
 * 2021/2/24
 * 资源工具
 * @author dengzhenli
 */
object ResourcesUtil {


    fun getDimens(context: Context, id: Int): Int {
        return context.resources.getDimensionPixelSize(id);
    }

}