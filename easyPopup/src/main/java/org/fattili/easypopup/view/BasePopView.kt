package org.fattili.easypopup.view

import android.app.Activity
import android.content.Context
import android.util.Log
import android.util.Xml
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import org.fattili.easypopup.R
import org.fattili.easypopup.util.ScreenUtil
import org.xmlpull.v1.XmlPullParser

/**
 * Created by dengzhenli on 2021/01/23.
 * 自定义的左侧浮窗
 */
abstract class BasePopView(//pop本地属性
    private val activity: Activity
) {

    /**
     * layoutId
     */
    private var layoutId = 0

    /**
     * 窗体的长宽
     */
    var popupWidth = WindowManager.LayoutParams.MATCH_PARENT

    var popupHeight = WindowManager.LayoutParams.MATCH_PARENT

    /**
     * 窗体的长宽
     */
    var viewWidth = WindowManager.LayoutParams.MATCH_PARENT
    var viewHeight = WindowManager.LayoutParams.MATCH_PARENT

    /**
     * 上下边距
     */
    var marginWidth = 0f
    var marginHeight = 0f


    var gravity = Gravity.CENTER

    var alpha = 0.5f

    /**
     * 是否占据焦点
     */
    var focusable = true

    /**
     *
     */
    var isOutsideTouchable = true


    private val context: Context = activity

    private var view: View? = null
    private var popupWindow: BasePopupWindow? = null

    /**********************************     抽象方法   **********************************/

    abstract fun onPopDismiss()

    abstract fun getLayoutId(): Int

    abstract fun initView(view: View?)

    abstract fun initData()

    private fun reShowData() {
        initData()
    }


    val isShow: Boolean
        get() = popupWindow?.isShowing ?: false

    companion object {
        private const val TAG = "BasePopView"
    }

    /***********************************PopupWindow相关 **********************************/
    private fun initPopData() {
        val parser = activity.resources.getLayout(layoutId)
        var type: Int
        while (parser.eventType.also { type = it } != XmlPullParser.START_TAG
            && type != XmlPullParser.END_DOCUMENT) {
            parser.next()
        }
        val count = parser.attributeCount

        val attributeSet = Xml.asAttributeSet(parser)
        for (i in 0 until count) {
            val name: String = attributeSet.getAttributeName(i)
            val value: String = attributeSet.getAttributeValue(i)
            if ("layout_width" == name) {
                val width = getPx(value)
                viewWidth = width
                popupWidth = width
            }
            if ("layout_height" == name) {
                val height = getPx(value)
                viewHeight = height
                popupHeight = height
            }

            if ("layout_gravity" == name) {
                val v: Int = attributeSet.getAttributeIntValue(i, gravity)
                gravity = v
            }

        }
    }


    private fun getPx(value: String): Int {
        if (value.endsWith("dip")) {
            val dp = value.replace("dip", "").toFloat()
            return ScreenUtil.dip2px(context, dp).toInt()
        }
        if (value.endsWith("px")) {
            val px = value.replace("px", "").toFloat()
            return px.toInt()
        }
        return value.toInt()
    }


    /**
     * 初始化
     */
    private fun initPopupWindow() {
        val inflate = LayoutInflater.from(context)
        view = inflate.inflate(layoutId, null)
        //内容，高度，宽度
        popupWindow = BasePopupWindow(
            view,
            viewWidth,
            viewHeight,
            focusable
        )

        //动画效果
        popupWindow?.animationStyle = R.style.dialog_theme
        //宽度
        popupWindow?.width = popupWidth
        //高度
        popupWindow?.height = popupHeight
        //显示位置
        popupWindow?.isOutsideTouchable = isOutsideTouchable
        popupWindow?.setBackgroundDrawable(view?.background)

        popupWindow?.showAtLocation(
            view,
            gravity,
            marginWidth.toInt(),
            marginHeight.toInt()
        )

        //设置背景半透明
        backgroundAlpha(alpha)
        popupWindow?.setOnDismissListener {
            backgroundAlpha(1f)
            onPopDismiss()
        }

        initView(view)
        initData()
    }


    /**
     * 显示view
     */
    fun show() {
        Log.d(TAG, "findResume: basepop：show")
        if (popupWindow == null) {
            layoutId = getLayoutId()
            initPopData()
            initPopupWindow()
        } else {
            popupWindow?.showAtLocation(
                view,
                gravity,
                marginWidth.toInt(),
                marginHeight.toInt()
            )
            reShowData()
        }
    }


    fun finish() {
        popupWindow?.let {
            if (it.isShowing) {
                it.dismiss()
            }
        }
        popupWindow = null
    }

    //隐藏
    fun dismiss() {
        popupWindow?.dismiss()
    }


    /**
     * 设置添加屏幕的背景透明度
     * @param bgAlpha
     */
    private fun backgroundAlpha(bgAlpha: Float) {
        activity.window.attributes.alpha = bgAlpha
    }
}
