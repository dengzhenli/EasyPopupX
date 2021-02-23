package org.fattili.easypopup

import android.app.Activity
import android.content.Context
import android.graphics.Rect
import android.graphics.drawable.ColorDrawable
import android.util.Log
import android.view.*
import androidx.core.view.get

/**
 * Created by dengzhenli on 2021/01/23.
 * 自定义的左侧浮窗
 */
abstract class BasePopView(activity: Activity) {

    /**
     * layoutId
     */
    private var layoutId = 0

    /**
     * 窗体的长宽
     */
    private var popupWidth = WindowManager.LayoutParams.MATCH_PARENT
    private var popupHeight = WindowManager.LayoutParams.MATCH_PARENT

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


    private var gravity = Gravity.TOP or Gravity.LEFT

    private var alpha = 0.5f

    /**
     * 是否占据焦点
     */
    private var focusable = true

    /**
     *
     */
    private var isOutsideTouchable = true


    //pop本地属性
    private val activity: Activity = activity
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

    }

    /**
     * 初始化
     */
    private fun initPopupWindow() {
        layoutId = getLayoutId()
        val inflate = LayoutInflater.from(context)
        view = inflate.inflate(layoutId, null)
//        Log.d(TAG, "initPopupWindow: " + (view as ViewGroup).getChildAt(0).width)
        view?.let {


            val v = it.findViewWithTag<PopLayout>("PopLayout");
            viewWidth = it.measuredWidth
            viewHeight = it.measuredHeight
            popupWidth = it.measuredWidth
            popupHeight = it.measuredHeight
            Log.d(TAG, "initPopupWindow: " + v.width + ":" + it.width)
        }
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
