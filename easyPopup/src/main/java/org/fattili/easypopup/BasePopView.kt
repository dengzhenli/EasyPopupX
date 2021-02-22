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
     * 上下边距
     */
    private var marginWidth = 0f
    private var marginHeight = 0f

    //pop本地属性
    private var activity: Activity = activity
    private var inflate: LayoutInflater = activity.layoutInflater
    private var popupWindow: BasePopupWindow? = null
    private var popupDismissListener: PopupDismissListener? = null

    /**********************************     抽象方法   **********************************/

    abstract fun getLayoutId(): Int

    abstract fun initView(view: View?)

    abstract fun initData()

    private fun reShowData() { initData() }


    companion object {
        private const val TAG = "BasePopView"
    }

    /***********************************PopupWindow相关 **********************************/
    /**
     * 设置添加屏幕的背景透明度
     * @param bgAlpha
     */
    private fun backgroundAlpha(bgAlpha: Float) {
        val lp = activity.window.attributes
        lp.alpha = bgAlpha //0.0-1.0
        activity.window.attributes = lp
    }


    val isShow: Boolean
        get() = if (popupWindow == null) false else popupWindow!!.isShowing


    /**
     * 初始化
     */
    private fun initPopupWindow() {
        layoutId = getLayoutId()
        val popupWindowView = inflate.inflate(layoutId, null)
        //内容，高度，宽度
        popupWindow = BasePopupWindow(
            popupWindowView,
            WindowManager.LayoutParams.WRAP_CONTENT,
            WindowManager.LayoutParams.MATCH_PARENT,
            true
        )
        //动画效果
        popupWindow!!.animationStyle = R.style.dialog_theme

        //菜单背景色
        val dw = ColorDrawable(-0x1000000)
        val parent = activity.window.decorView
            .findViewById<View>(android.R.id.content)
        val rect = Rect()
        val window = activity.window
        window.decorView.getWindowVisibleDisplayFrame(rect)
        val height = rect.height()

        popupWidth = 1000
        popupHeight = height + rect.top
        //宽度
        popupWindow!!.width = popupWidth
        //高度
        popupWindow!!.height = popupHeight
        //显示位置
        popupWindow!!.isOutsideTouchable = true
        popupWindow!!.setBackgroundDrawable(dw)
        popupWindow!!.showAtLocation(
            parent,
            Gravity.TOP or Gravity.LEFT,
            marginWidth.toInt(),
            marginHeight.toInt()
        )

        //设置背景半透明
        backgroundAlpha(0.5f)
        popupWindow!!.setOnDismissListener { onPopDismiss() }
        initView(popupWindowView)
        initData()
    }

    /**
     * 显示view
     */
    fun show() {
        Log.d(TAG, "findResume: basepop：show")
        if (popupWindow == null) {
            initPopupWindow()
        } else {
            val parent = activity.window.decorView
                .findViewById<View>(android.R.id.content)
            val rect = Rect()
            val window = activity.window
            window.decorView.getWindowVisibleDisplayFrame(rect)
            popupWindow!!.showAtLocation(
                parent,
                Gravity.TOP or Gravity.LEFT,
                marginWidth.toInt(),
                marginHeight.toInt()
            )
            reShowData()
        }
    }

    interface PopupDismissListener {
        fun onDismiss()
    }

    //隐藏
    fun dismiss() {
        if (popupWindow == null) return
        popupWindow!!.dismiss()
    }

    fun onPopDismiss() {
        backgroundAlpha(1f)
        if (popupDismissListener != null) {
            popupDismissListener!!.onDismiss()
        }
    }

    fun finish() {
        if (popupWindow == null) return
        if (popupWindow!!.isShowing) {
            popupWindow!!.dismiss()
        }
        popupWindow = null
    }

    fun showPW(popView: BasePopView) {
        popView.show()
    }

    fun showMsg(msg: String?) {}

    //设置隐藏监听
    fun setPopupDismissListener(popupDismissListener: PopupDismissListener?) {
        this.popupDismissListener = popupDismissListener
    }


}