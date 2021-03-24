package org.fattili.easypopup.manager

import org.fattili.easypopup.view.base.BasePopView
import java.util.*

/**
 * 文件描述：用栈存储PopupWindow
 * 作者：DZL
 * 创建时间：2018/9/26
 * 更改时间：2018/9/26
 * 版本号：1
 */
object PopViewManager {
    private var popStack = Stack<BasePopView>()
    private val tempPopStack = Stack<BasePopView>()

    fun getPopStack(): Stack<BasePopView> {
        return popStack
    }

    fun setPopStack(popStack: Stack<BasePopView>) {
        PopViewManager.popStack = popStack
    }

    /**
     * 添加视图
     *
     * @param loginDialog
     * @return
     */
    fun addView(loginDialog: BasePopView): BasePopView {
        return popStack.push(loginDialog)
    }

    /**
     * 移除视图
     *
     * @param loginDialog
     * @return
     */
    fun removeView(loginDialog: BasePopView): Boolean {
        if (popStack.empty()) return false
        if (popStack.peek() === loginDialog) {
            popStack.pop()
            return true
        }
        return false
    }

    /**
     * 移除最后一个视图
     *
     * @return
     */
    fun removeLastView(): BasePopView? {
        if (popStack.empty()) return null
        val loginDialog = popStack.pop()
        loginDialog.finish()
        return loginDialog
    }

    /**
     * 清空全部视图
     */
    fun clearView() {
        while (!popStack.empty()) {
            val dialog = popStack.pop()
            if (dialog.isShow) {
                dialog.finish()
            }
        }
    }

    /**
     * 添加视图
     *
     * @param
     * @return
     */
    private fun removeViewToTemp(): BasePopView? {
        if (popStack.empty()) return null
        val loginDialog = popStack.pop()
        tempPopStack.push(loginDialog)
        return loginDialog
    }

    /**
     * 从临时栈读取视图
     *
     * @param
     * @return
     */
    private val viewFromTemp: BasePopView?
        get() {
            if (tempPopStack.empty()) return null
            val loginDialog = tempPopStack.pop()
            popStack.push(loginDialog)
            return loginDialog
        }

    /**
     * 隐藏全部视图至临时栈
     */
    fun hideViewsToTemp() {
        //先清空临时栈
        clearTemp()
        while (!popStack.empty()) {
            val dialog =
                removeViewToTemp()
            if (dialog!!.isShow) {
                dialog.dismiss()
            }
        }
    }

    /**
     * 移除全部视图至临时栈
     */
    fun finishViewsToTemp() {
        //先清空临时栈
        clearTemp()
        while (!popStack.empty()) {
            val dialog =
                removeViewToTemp()
            if (dialog!!.isShow) {
                dialog.finish()
            }
        }
    }

    /**
     * 从临时栈读取视图
     */
    val viewsFromTemp: Unit
        get() {
            while (!tempPopStack.empty()) {
                val dialog =
                    viewFromTemp
                showPW(dialog)
            }
        }

    private fun clearTemp() {
        tempPopStack.clear()
    }

    private fun showPW(popView: BasePopView?) {
        popView?.show()
    }

    private const val TAG = "PopViewManager"
}