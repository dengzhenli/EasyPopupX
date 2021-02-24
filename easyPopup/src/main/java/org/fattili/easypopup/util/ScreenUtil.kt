package org.fattili.easypopup.util

import android.content.Context

/**
 * 2021/2/24
 *
 * @author dengzhenli
 */
object ScreenUtil{

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    open fun dip2px(context: Context, dpValue: Float): Int {
        val scale = context.resources.displayMetrics.density
        return (dpValue * scale + 0.5f).toInt()
    }


    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    open fun px2dip(context: Context, pxValue: Float): Int {
        val scale = context.resources.displayMetrics.density
        return (pxValue / scale + 0.5f).toInt()
    }

}