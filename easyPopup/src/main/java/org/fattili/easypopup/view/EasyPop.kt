package org.fattili.easypopup.view

import android.app.Activity
import android.graphics.drawable.Drawable
import android.view.*
import android.widget.FrameLayout
import androidx.lifecycle.LifecycleObserver
import org.fattili.easypopup.R
import org.fattili.easypopup.except.ViewUnCreatedException
import org.fattili.easypopup.manager.EasyPopManager
import org.fattili.easypopup.util.XmlUtil
import org.fattili.easypopup.view.base.BasePopupWindow


/**
 * Created by dengzhenli on 2021/01/23.
 * 自定义的Popup
 */
abstract class EasyPop(activity: Activity) : FrameLayout(activity), LifecycleObserver {

    var view: View? = null

    var activity: Activity? = activity

    private var needReload = false


    /**
     * 向右对齐标志 0000 0100
     */
    private val GRAVITY_RIGHT_FLAG = 0x4

    /**
     * 向下对齐标志 0100 0000
     */
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
    private var popupWidth = DEFAULT_POP_WIDTH
        set(value) {
            field = value
            SET_POP_WIDTH = true
        }

    private var popupHeight = DEFAULT_POP_HEIGHT
        set(value) {
            field = value
            SET_POP_HEIGHT = true
        }

    /**
     * 窗体内部view的长宽
     */
    private var viewWidth = DEFAULT_VIEW_WIDTH
        set(value) {
            field = value
            SET_VIEW_WIDTH = true
        }

    private var viewHeight = DEFAULT_VIEW_HEIGHT
        set(value) {
            field = value
            SET_VIEW_HEIGHT = true
        }


    /**
     * 是否占据焦点
     */
    private var popFocusable = DEFAULT_FOCUSABLE
        set(value) {
            field = value
            SET_FOCUSABLE = true
        }

    /**
     * 外部点击
     */
    private var isOutsideTouchable = DEFAULT_ISOUTSIDETOUCHABLE
        set(value) {
            field = value
            SET_ISOUTSIDETOUCHABLE = true
        }

    /**
     * 上下边距
     */
    private var popMarginWidth = DEFAULT_MARGIN_WIDTH
        set(value) {
            field = value
            SET_MARGIN_WIDTH = true
        }

    private var popMarginHeight = DEFAULT_MARGIN_HEIGHT
        set(value) {
            field = value
            SET_MARGIN_HEIGHT = true
        }

    private var popGravity = DEFAULT_GRAVITY
        set(value) {
            field = value
            SET_GRAVITY = true
        }


    private var popBgAlpha = DEFAULT_ALPHA
        set(value) {
            field = value
            SET_ALPHA = true
        }


    /**
     * 依托view 没用设置则使用activity根布局
     */
    private var showAtView: View? = null
    private var popupWindow: BasePopupWindow? = null


    /***********************************PopupWindow相关 **********************************/
    /**
     * 初始化窗体参数
     */
    private fun initPopData() {
        initViewParam()
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
                        popGravity = v
                    }
                }

                "layout_marginLeft" -> {
                    if (popGravity and GRAVITY_RIGHT_FLAG > 0) {
                        popMarginWidth = XmlUtil.getPx(context, value)
                    }
                }
                "layout_marginRight" -> {
                    if (popGravity and GRAVITY_RIGHT_FLAG == 0) {
                        popMarginWidth = XmlUtil.getPx(context, value)
                    }
                }
                "layout_marginHorizontal" -> {
                    popMarginWidth = XmlUtil.getPx(context, value)
                }
                "layout_marginStart" -> {
                    if (popGravity and GRAVITY_RIGHT_FLAG > 0) {
                        popMarginWidth = XmlUtil.getPx(context, value)
                    }
                }
                "layout_marginEnd" -> {
                    if (popGravity and GRAVITY_RIGHT_FLAG == 0) {
                        popMarginWidth = XmlUtil.getPx(context, value)
                    }
                }
                "layout_marginBottom" -> {
                    if (popGravity and GRAVITY_BOTTOM_FLAG == 0) {
                        popMarginHeight = XmlUtil.getPx(context, value)
                    }
                }
                "layout_marginTop" -> {
                    if (popGravity and GRAVITY_BOTTOM_FLAG == 0) {
                        popMarginHeight = XmlUtil.getPx(context, value)
                    }
                }
                "layout_marginVertical" -> {
                    popMarginHeight = XmlUtil.getPx(context, value)
                }
                "layout_margin" -> {
                    popMarginWidth = XmlUtil.getPx(context, value)
                    popMarginHeight = XmlUtil.getPx(context, value)
                }
            }
        }
    }

    /**
     * 初始化窗体
     */
    @Throws(ViewUnCreatedException::class)
    private fun initPopupWindow() {

        if (view == null) {
            val inflate = LayoutInflater.from(context)
            view = inflate.inflate(layoutId, this)
            if (showAtView == null) showAtView = view
            if (backBackground != null) {
                view?.background = backBackground
            }
        }
        //内容，高度，宽度
        popupWindow = BasePopupWindow(
            view,
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
        popupWindow?.setBackgroundDrawable(view?.background)

        try {
            popupWindow?.showAtLocation(
                showAtView,
                popGravity,
                popMarginWidth,
                popMarginHeight
            )
        } catch (e: Exception) {
            e.printStackTrace()
            val parent = view?.parent as ViewGroup
            parent.removeView(view)
            throw ViewUnCreatedException("easyPop调用失败，请先等待你的页面渲染完成")
        }

        //设置背景半透明
        backgroundAlpha(popBgAlpha)
        popupWindow?.setOnDismissListener {
            backgroundAlpha(1f)
            activity?.let { it1 -> EasyPopManager.remove(it1, this) }
            onPopDismiss()
        }
        onPopCreated(view)
    }


    /**
     * 显示view
     */
    private fun showPop(): EasyPop {
        activity?.let { EasyPopManager.add(it, this) }
        if (popupWindow == null) {
            layoutId = getLayoutId()
            onPopInit()
            initPopData()
            try {
                initPopupWindow()
                onPopCreated(view)
            } catch (e: ViewUnCreatedException) {
                needReload = true
                e.printStackTrace()
            }
        } else {
            popupWindow?.showAtLocation(
                view,
                popGravity,
                popMarginWidth,
                popMarginHeight
            )
            onPopReShow()
        }

        return this
    }


    /**
     * 关闭页面
     */
    private fun finishPop() {
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
    private fun dismissPop() {
        popupWindow?.dismiss()
    }


    /**
     * 设置添加屏幕的背景透明度
     * @param bgAlpha
     */
    private fun backgroundAlpha(bgAlpha: Float) {
        val lp = activity?.window?.attributes
        lp?.alpha = bgAlpha
        activity?.window?.attributes = lp
    }


    override fun onWindowFocusChanged(hasWindowFocus: Boolean) {
        super.onWindowFocusChanged(hasWindowFocus)
        if (hasWindowFocus && needReload) {
            try {
                initPopupWindow()
                onPopCreated(view)
                needReload = false
            } catch (e: ViewUnCreatedException) {
                e.printStackTrace()
            }
        }
    }

    /**********************************     抽象方法   **********************************/


    abstract fun getLayoutId(): Int

    open fun onPopInit() {}

    abstract fun onPopCreated(view: View?)

    /**
     * pop重新加载
     */
    open fun onPopReShow() {}

    open fun onPopDismiss() {}


    val isShow: Boolean
        get() = popupWindow?.isShowing ?: false


    /**-----------------------------对外API-------------------------------------------**/


    private var backBackground: Drawable? = null

    fun setBackGround(value: Drawable): EasyPop {
        backBackground = value
        return this
    }

    fun getBackGround(): Drawable? {
        return backBackground
    }

    fun setMarginWidth(value: Int): EasyPop {
        popMarginWidth = value
        return this
    }

    fun setMarginHeight(value: Int): EasyPop {
        popMarginHeight = value
        return this
    }

    fun setGravity(value: Int): EasyPop {
        popGravity = value
        return this
    }


    fun setBgAlpha(value: Float): EasyPop {
        popBgAlpha = value
        return this
    }


    /**
     * 设置宽度，pop与view一起
     */
    fun setWidth(width: Int): EasyPop {
        popupWidth = width
        viewWidth = width
        return this
    }

    /**
     * 设置高度，pop与view一起
     */
    fun setHeight(height: Int): EasyPop {
        popupHeight = height
        viewHeight = height
        return this
    }


    fun showOnView(view: View): EasyPop {
        showAtView = view
        return this
    }

    fun outClickable(clickable: Boolean): EasyPop {
        popFocusable = clickable
        isOutsideTouchable = clickable
        return this
    }

    /**
     * 显示view
     */
    fun show(): EasyPop {
        return showPop()
    }

    fun finish(): EasyPop {
        finishPop()
        return this
    }

    fun dismiss(): EasyPop {
        dismissPop()
        return this
    }

}
