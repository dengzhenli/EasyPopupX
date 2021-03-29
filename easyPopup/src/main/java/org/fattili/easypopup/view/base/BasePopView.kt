package org.fattili.easypopup.view.base

import android.app.Activity
import android.util.Log
import android.view.*
import android.widget.FrameLayout
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent
import org.fattili.easypopup.R
import org.fattili.easypopup.util.XmlUtil

/**
 * Created by dengzhenli on 2021/01/23.
 * 自定义的Popup
 */
abstract class BasePopView :FrameLayout, LifecycleObserver {

    var activity: Activity

    // 向右对其标志 0000 0100
    private val GRAVITY_RIGHT_FLAG = 0x4

    // 向下对其标志 0100 0000
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
     * 布局ID
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
     * 窗体内部view的长宽
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

    var bgAlpha = DEFAULT_ALPHA
        set(value) {
            field = value
            SET_ALPHA = true
        }

    /**
     * 是否占据焦点
     */
    var popFocusable = DEFAULT_FOCUSABLE
        set(value) {
            field = value
            SET_FOCUSABLE = true
        }

    /**
     * 外部点击
     */
    var isOutsideTouchable = DEFAULT_ISOUTSIDETOUCHABLE
        set(value) {
            field = value
            SET_ISOUTSIDETOUCHABLE = true
        }


    var popView: View? = null

    /**
     * 依托view 没用设置则使用activity根布局
     */
    private var showAtView: View? = null
    private var popupWindow: BasePopupWindow? = null




    constructor(activity: Activity) : super(activity) {
        this.activity = activity
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
        onReShowPop()
    }


    val isShow: Boolean
        get() = popupWindow?.isShowing ?: false

    companion object {
        private const val TAG = "BasePopView"
    }

    /***********************************PopupWindow相关 **********************************/
    /**
     * 初始化窗体参数
     */
    private fun initPopData() {
        initViewParam()
        if (!SET_FOCUSABLE) {
            popFocusable = outClickable()
        }

        if (!SET_ISOUTSIDETOUCHABLE) {
            isOutsideTouchable = outClickable()
        }
    }


    /**
     * 初始化布局参数
     */
    private fun initViewParam() {
        val param = XmlUtil.parse(context, layoutId)
        param.entries.forEach {
            val value = it.value.strValue ?: ""
            when (it.key) {
                "layout_width" -> {
                    val width = XmlUtil.getPx(context, value)
                    if (!SET_VIEW_WIDTH) {
                        viewWidth = width
                    }
                    if (!SET_POP_WIDTH) {
                        popupWidth = width
                    }
                }

                "layout_height" -> {
                    val height = XmlUtil.getPx(context, value)
                    if (!SET_VIEW_HEIGHT) {
                        viewHeight = height
                    }
                    if (!SET_POP_HEIGHT) {
                        popupHeight = height
                    }
                }

                "layout_gravity" -> {
                    val v: Int = it.value.intValue ?: DEFAULT_GRAVITY
                    if (!SET_GRAVITY) {
                        gravity = v
                    }
                }

                "layout_marginLeft" -> {
                    if (gravity and GRAVITY_RIGHT_FLAG > 0) {
                        marginWidth = XmlUtil.getPx(context, value)
                    }
                }
                "layout_marginRight" -> {
                    if (gravity and GRAVITY_RIGHT_FLAG == 0) {
                        marginWidth = XmlUtil.getPx(context, value)
                    }
                }
                "layout_marginHorizontal" -> {
                    marginWidth = XmlUtil.getPx(context, value)
                }
                "layout_marginStart" -> {
                    if (gravity and GRAVITY_RIGHT_FLAG > 0) {
                        marginWidth = XmlUtil.getPx(context, value)
                    }
                }
                "layout_marginEnd" -> {
                    if (gravity and GRAVITY_RIGHT_FLAG == 0) {
                        marginWidth = XmlUtil.getPx(context, value)
                    }
                }
                "layout_marginBottom" -> {
                    if (gravity and GRAVITY_BOTTOM_FLAG == 0) {
                        marginHeight = XmlUtil.getPx(context, value)
                    }
                }
                "layout_marginTop" -> {
                    if (gravity and GRAVITY_BOTTOM_FLAG == 0) {
                        marginHeight = XmlUtil.getPx(context, value)
                    }
                }
                "layout_marginVertical" -> {
                    marginHeight = XmlUtil.getPx(context, value)
                }
                "layout_margin" -> {
                    marginWidth = XmlUtil.getPx(context, value)
                    marginHeight = XmlUtil.getPx(context, value)
                }
            }
        }
    }

    /**
     * 初始化窗体
     */
    private fun initPopupWindow() {
        val inflate = LayoutInflater.from(context)

        popView = inflate.inflate(layoutId, this)
        if (showAtView == null) showAtView = popView
        //内容，高度，宽度
        popupWindow = BasePopupWindow(
            popView,
            viewWidth,
            viewHeight,
            popFocusable
        )

        //动画效果
        popupWindow?.animationStyle = R.style.ep_common_pop_theme
        //宽度
        popupWindow?.width = popupWidth
        //高度
        popupWindow?.height = popupHeight
        //显示位置
        popupWindow?.isOutsideTouchable = isOutsideTouchable
        popupWindow?.setBackgroundDrawable(popView?.background)

        popupWindow?.showAtLocation(
            showAtView,
            gravity,
            marginWidth.toInt(),
            marginHeight.toInt()
        )

        //设置背景半透明
        backgroundAlpha(bgAlpha)
        popupWindow?.setOnDismissListener {
            backgroundAlpha(1f)
            onPopDismiss()
        }

        initView(popView)
        initData()

    }


    /**
     * 显示view
     */
    fun show(): BasePopView {
        Log.d(TAG, "findResume: basepop：show")
        if (popupWindow == null) {
            onCreatePop()
            layoutId = getLayoutId()
            initPopData()
            initPopupWindow()
            onCreatePop()
        } else {
            popupWindow?.showAtLocation(
                popView,
                gravity,
                marginWidth,
                marginHeight
            )
            reShowData()
        }
        return this
    }


    /**
     * 关闭页面
     */
    fun finish() {
        popupWindow?.let {
            if (it.isShowing) {
                it.dismiss()
            }
        }
        popupWindow = null
    }

    /**
     * 隐藏
     */
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

    /**
     * 设置宽度，pop与view一起
     */
    fun setWidth(width: Int) {
        popupWidth = width
        viewWidth = width
    }

    /**
     * 设置高度，pop与view一起
     */
    fun setHeight(height: Int) {
        popupHeight = height
        viewHeight = height
    }

    /**
     * 注册lifecycle
     */
    fun register(owner: LifecycleOwner): BasePopView {
        owner.lifecycle.addObserver(this)
        return this
    }

    /**
     * pop重新加载
     */
    open fun onReShowPop() {}

    /**
     * pop初次加载
     */
    open fun onCreatePop() {}

    /**
     * activity生命周期
     */
    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    open fun onDestroy() {
    }

    /**
     * activity生命周期
     */
    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    open fun onResume() {
    }

    /**
     * activity生命周期
     */
    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    open fun onPause() {
    }

    /**
     * activity生命周期
     */
    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    open fun onStop() {
    }

}
