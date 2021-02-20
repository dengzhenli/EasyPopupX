package org.fattili.easypopup;



import java.util.Stack;

/**
 * 文件描述：用栈存储PopupWindow
 * 作者：DZL
 * 创建时间：2018/9/26
 * 更改时间：2018/9/26
 * 版本号：1
 */
public class PopViewManager {
    private static Stack<BasePopView> popStack = new Stack<>();
    private static Stack<BasePopView> tempPopStack = new Stack<>();

    public static Stack<BasePopView> getPopStack() {
        return popStack;
    }

    public static void setPopStack(Stack<BasePopView> popStack) {
        PopViewManager.popStack = popStack;
    }

    /**
     * 添加视图
     *
     * @param loginDialog
     * @return
     */
    public static BasePopView addView(BasePopView loginDialog) {
        return popStack.push(loginDialog);
    }

    /**
     * 移除视图
     *
     * @param loginDialog
     * @return
     */
    public static boolean removeView(BasePopView loginDialog) {
        if (popStack.empty()) return false;
        if (popStack.peek() == loginDialog) {
            popStack.pop();
            return true;
        }
        return false;
    }

    /**
     * 移除最后一个视图
     *
     * @return
     */

    public static BasePopView removeLastView() {
        if (popStack.empty()) return null;
        BasePopView loginDialog = popStack.pop();
        loginDialog.finish();
        return loginDialog;

    }

    /**
     * 清空全部视图
     */
    public static void clearView() {
        while (!popStack.empty()) {
            BasePopView dialog = popStack.pop();
            if (dialog.isShow()) {
                dialog.finish();
            }
        }

    }

    /**
     * 添加视图
     *
     * @param
     * @return
     */
    public static BasePopView removeViewToTemp() {
        if (popStack.empty()) return null;
        BasePopView loginDialog = popStack.pop();
        tempPopStack.push(loginDialog);
        return loginDialog;
    }

    /**
     * 从临时栈读取视图
     *
     * @param
     * @return
     */
    public static BasePopView getViewFromTemp() {
        if (tempPopStack.empty()) return null;
        BasePopView loginDialog = tempPopStack.pop();
        popStack.push(loginDialog);
        return loginDialog;
    }

    /**
     * 隐藏全部视图至临时栈
     */
    public static void hideViewsToTemp() {
        //先清空临时栈
        clearTemp();
        while (!popStack.empty()) {
            BasePopView dialog = removeViewToTemp();
            if (dialog.isShow()) {
                dialog.dismiss();
            }
        }
    }
    /**
     * 移除全部视图至临时栈
     */
    public static void finishViewsToTemp() {
        //先清空临时栈
        clearTemp();
        while (!popStack.empty()) {
            BasePopView dialog = removeViewToTemp();
            if (dialog.isShow()) {
                dialog.finish();
            }
        }
    }

    /**
     * 从临时栈读取视图
     */
    public static void getViewsFromTemp() {
        while (!tempPopStack.empty()) {
            BasePopView dialog = getViewFromTemp();
            showPW(dialog);
        }
    }

    public static void clearTemp() {
        tempPopStack.clear();
    }

    private static void showPW(BasePopView popView) {
        if (popView != null) {
            popView.show();
        }
    }

    private static final String TAG = "PopViewManager";
}
