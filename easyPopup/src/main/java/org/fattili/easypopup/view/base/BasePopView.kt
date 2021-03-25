package org.fattili.easypopup.view.base

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
abstract class BasePopView {

    var activity: Activity

    private val GRAVITY_RIGHT_FLAG = 0x4
    private val GRAVITY_BOTTOM_FLAG = 0x40

    /**
     * 默认设置
     */
    private val DEFAULT_POP_WIDTH = WindowManager.LayoutParams.MATCH_PARENT
    private val DEFAULT_POP_HEIGHT = WindowManager.LayoutParams.MATCH_PARENT
    private val DEFAULT_VIEW_WIDTH = WindowManager.LayoutParams.MATCH_PARENT
    private val DEFAULT_VIEW_HEIGHT = WindowManager.LayoutParams.MATCH_PARENT
    private val DEFAULT_MARGIN_WIDTH = 0
    private val DEFAULT_MARGIN_HEIGHT = 0
    private val DEFAULT_GRAVITY = Gravity.CENTER
    private val DEFAULT_ALPHA = 0.5f
    private val DEFAULT_FOCUSABLE = true
    private val DEFAULT_ISOUTSIDETOUCHABLE = true

    /**
     * 优先使用代码设置的选项
     */
    private var SET_POP_WIDTH = false
    private var SET_POP_HEIGHT = false
    private var SET_VIEW_WIDTH = false
    private var SET_VIEW_HEIGHT = false
    private var SET_MARGIN_WIDTH = false
    private var SET_MARGIN_HEIGHT = false
    private var SET_GRAVITY = false
    private var SET_ALPHA = false
    private var SET_FOCUSABLE = false
    private var SET_ISOUTSIDETOUCHABLE = false

    /**
     * layoutId
     */
    private var layoutId = 0

    /**
     * 窗体的长宽
     */
    var popupWidth = DEFAULT_POP_WIDTH
        set(value) {
            field = value
            SET_POP_WIDTH = true
        }
    var popupHeight = DEFAULT_POP_HEIGHT
        set(value) {
            field = value
            SET_POP_HEIGHT = true
        }

    /**
     * 窗体的长宽
     */
    var viewWidth = DEFAULT_VIEW_WIDTH
        set(value) {
            field = value
            SET_VIEW_WIDTH = true
        }

    var viewHeight = DEFAULT_VIEW_HEIGHT
        set(value) {
            field = value
            SET_VIEW_HEIGHT = true
        }

    /**
     * 上下边距
     */
    var marginWidth = DEFAULT_MARGIN_WIDTH
        set(value) {
            field = value
            SET_MARGIN_WIDTH = true
        }

    var marginHeight = DEFAULT_MARGIN_HEIGHT
        set(value) {
            field = value
            SET_MARGIN_HEIGHT = true
        }

    var gravity = DEFAULT_GRAVITY
        set(value) {
            field = value
            SET_GRAVITY = true
        }

    var alpha = DEFAULT_ALPHA
        set(value) {
            field = value
            SET_ALPHA = true
        }

    /**
     * 是否占据焦点
     */
    var focusable = DEFAULT_FOCUSABLE
        set(value) {
            field = value
            SET_FOCUSABLE = true
        }

    /**
     *
     */
    var isOutsideTouchable = DEFAULT_ISOUTSIDETOUCHABLE
        set(value) {
            field = value
            SET_ISOUTSIDETOUCHABLE = true
        }


    private var context: Context

    private var view: View? = null
    private var showAtView: View? = null
    private var popupWindow: BasePopupWindow? = null


    fun getContext(): Context {
        return activity
    }


    constructor(activity: Activity) {
        this.activity = activity
        this.context = activity
    }

    constructor(activity: Activity, showAtView: View) : this(activity) {
        this.showAtView = showAtView
    }

    /**********************************     抽象方法   **********************************/


    abstract fun onPopDismiss()

    abstract fun getLayoutId(): Int

    abstract fun initView(view: View?)

    abstract fun initData()

    abstract fun outClickable(): Boolean

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
        while (
            parser.eventType.also { type = it } != XmlPullParser.START_TAG
            && type != XmlPullParser.END_DOCUMENT) {
            parser.next()
        }
        val count = parser.attributeCount

        val attributeSet = Xml.asAttributeSet(parser)
        for (i in 0 until count) {
            val name: String = attributeSet.getAttributeName(i)
            val value: String = attributeSet.getAttributeValue(i)
            when (name) {
                "layout_width" -> {
                    val width = getPx(value)
                    (!SET_VIEW_WIDTH).let { viewWidth = width }
                    (!SET_POP_WIDTH).let { popupWidth = width }
                }

                "layout_height" -> {
                    val height = getPx(value)
                    (!SET_VIEW_HEIGHT).let { viewHeight = height }
                    (!SET_POP_HEIGHT).let { popupHeight = height }
                }

                "layout_gravity" -> {
                    val v: Int = attributeSet.getAttributeIntValue(i, gravity)
                    (!SET_GRAVITY).let { gravity = v }
                }

                "layout_marginLeft" -> {
                    if (gravity and GRAVITY_RIGHT_FLAG > 0) {
                        marginWidth = getPx(value)
                    }
                }
                "layout_marginRight" -> {
                    if (gravity and GRAVITY_RIGHT_FLAG == 0) {
                        marginWidth = getPx(value)
                    }
                }
                "layout_marginHorizontal" -> {
                    marginWidth = getPx(value)
                }
                "layout_marginStart" -> {
                    if (gravity and GRAVITY_RIGHT_FLAG > 0) {
                        marginWidth = getPx(value)
                    }
                }
                "layout_marginEnd" -> {
                    if (gravity and GRAVITY_RIGHT_FLAG == 0) {
                        marginWidth = getPx(value)
                    }
                }
                "layout_marginBottom" -> {
                    if (gravity and GRAVITY_BOTTOM_FLAG == 0) {
                        marginHeight = getPx(value)
                    }
                }
                "layout_marginTop" -> {
                    if (gravity and GRAVITY_BOTTOM_FLAG == 0) {
                        marginHeight = getPx(value)
                    }
                }
                "layout_marginVertical" -> {
                    marginHeight = getPx(value)
                }
                "layout_margin" -> {
                    marginWidth = getPx(value)
                    marginHeight = getPx(value)
                }
            }
        }

        if (!SET_FOCUSABLE) {
            focusable = outClickable()
        }

        if (!SET_ISOUTSIDETOUCHABLE) {
            isOutsideTouchable = outClickable()
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
        if (showAtView == null) showAtView = view
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
            showAtView,
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
        val lp = activity.window.attributes
        lp.alpha = bgAlpha
        activity.window.attributes = lp
    }
}
