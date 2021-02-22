package org.fattili.easypopup

import android.app.Activity
import android.graphics.Rect
import android.graphics.drawable.ColorDrawable
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager

/**
 * Created by dengzhenli on 2021/01/23.
 * 自定义的左侧浮窗
 */
abstract class BasePopView(activity: Activity) {
    //修改的属性
    /**
     * layoutId
     */
    private var layoutId = 0

    /**
     * 窗体的长宽
     */
    private var popupWidth = 0
    private var popupHeight = 0

    /**
     * 窗体的长宽
     */
    private var viewWidth = WindowManager.LayoutParams.MATCH_PARENT
    private var viewHeight = WindowManager.LayoutParams.MATCH_PARENT

    /**
     * 上下边距
     */
    private var marginWidth = 0f
    private var marginHeight = 0f

    /**
     * 是否占据焦点
     */
    private var focusable = true

    /**
     *
     */
    private var isOutsideTouchable = true

    private var gravity = Gravity.TOP or Gravity.LEFT

    private var alpha = 0.5f

    //pop本地属性
    private var activity: Activity = activity
    private var view: View? = null
    private var inflate: LayoutInflater = activity.layoutInflater
    private var popupWindow: BasePopupWindow? = null
    private var popupDismissListener: PopupDismissListener? = null

    /**********************************     抽象方法   **********************************/

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


    /**
     * 初始化
     */
    private fun initPopupWindow() {


        layoutId = getLayoutId()
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

        //菜单背景色
        val parent = activity.window.decorView.findViewById<View>(android.R.id.content)

        popupWindow?.showAtLocation(
            parent,
            gravity,
            marginWidth.toInt(),
            marginHeight.toInt()
        )

        //设置背景半透明
        backgroundAlpha(alpha)
        popupWindow?.setOnDismissListener { onPopDismiss() }

        initView(view)
        initData()
    }


    /**
     * 显示view
     */
    fun show() {
        Log.d(TAG, "findResume: basepop：show")
        if (popupWindow == null) {
            initPopData()
            initPopupWindow()
        } else {
            val parent = activity.window.decorView.findViewById<View>(android.R.id.content)
            popupWindow?.showAtLocation(
                parent,
                gravity,
                marginWidth.toInt(),
                marginHeight.toInt()
            )
            reShowData()
        }
    }

    private fun initPopData() {
        val rect = Rect()
        val window = activity.window
        window.decorView.getWindowVisibleDisplayFrame(rect)
        val height = rect.height()
        popupWidth = 1000
        popupHeight = height + rect.top
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

    fun onPopDismiss() {
        backgroundAlpha(1f)
        popupDismissListener?.onDismiss()
    }

    //设置隐藏监听
    fun setPopupDismissListener(popupDismissListener: PopupDismissListener?) {
        this.popupDismissListener = popupDismissListener
    }

    interface PopupDismissListener {
        fun onDismiss()
    }

    /**
     * 设置添加屏幕的背景透明度
     * @param bgAlpha
     */
    private fun backgroundAlpha(bgAlpha: Float) {
        val lp = activity.window.attributes
        lp.alpha = bgAlpha //0.0-1.0
        activity.window.attributes = lp
    }


}