package org.fattili.easypopup;

import android.app.Activity;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.Toast;




/**
 * Created by dengzhenli on 2021/01/23.
 * 自定义的左侧浮窗
 */

public abstract class BasePopView {
    //修改的属性
    private int layoutId;
    private int popupWidth;
    private int popupHeight;

    private float marginWidth = 0;
    private float marginHeight = 0;


    //pop本地属性
    public BasePopupWindow popupWindow;
    public LayoutInflater inflate;
    public Activity activity;
    public View.OnClickListener onClickListener;
    private PopupDismissListener popupDismissListener;


    public BasePopView(Activity activity, View.OnClickListener clickListener) {
        this(activity);
        onClickListener = clickListener;
    }

    public BasePopView(Activity activity) {
        this.inflate = activity.getLayoutInflater();
        this.activity = activity;
    }


    /***********************************PopupWindow相关*********************************************/
    /**

    /**
     * 设置添加屏幕的背景透明度   * @param bgAlpha
     */
    private void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        activity.getWindow().setAttributes(lp);
    }


    protected void initPopupWindowData(int popupWidth, int popupHeight, float ratiomarginWidth, float ratiomarginHeight) {
        this.popupHeight = popupHeight;
        this.popupWidth = popupWidth;
        this.marginHeight = ratiomarginHeight;
        this.marginWidth = ratiomarginWidth;
    }


    public boolean isShow() {
        if (popupWindow == null) return false;
        return popupWindow.isShowing();
    }


    /**
     * 初始化
     *
     * @param popupWidth
     * @param popupHeight
     * @param marginWidth
     * @param marginHeight
     */
    private void initPopupWindow(int popupWidth, int popupHeight, float marginWidth, float marginHeight) {
        initPopupWindowData(popupWidth, popupHeight, marginWidth, marginHeight);
        initPopupWindow();
    }

    /**
     * 初始化
     */
    private void initPopupWindow() {

        Log.d(TAG, "findResume: basepop：initPopupWindow");
        layoutId = getLayoutId();
        initPopupWindowData(popupWidth, popupHeight, marginWidth, marginHeight);
        final View popupWindowView = inflate.inflate(layoutId, null);
        //内容，高度，宽度
        popupWindow = new BasePopupWindow(popupWindowView, WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.FILL_PARENT, true);
        //动画效果
        popupWindow.setAnimationStyle(
                R.style.dialog_theme);

        //菜单背景色
        ColorDrawable dw = new ColorDrawable(0xff000000);

        View parent = activity.getWindow().getDecorView().findViewById(android.R.id.content);
        Rect rect = new Rect();
        Window window = activity.getWindow();
        window.getDecorView().getWindowVisibleDisplayFrame(rect);
        int height = rect.height();

//        popupWidth = activity.getResources().getDimensionPixelSize(LR.dimen.umipay_pop_width);
        popupWidth = 1000;
        popupHeight = height + rect.top;
        //宽度
        popupWindow.setWidth(popupWidth);
        //高度
        popupWindow.setHeight(popupHeight);
        //显示位置
        popupWindow.setOutsideTouchable(true);
        popupWindow.setBackgroundDrawable(dw);
        popupWindow.showAtLocation(parent, Gravity.TOP | Gravity.LEFT, (int) marginWidth, (int) marginHeight);

        //设置背景半透明
        backgroundAlpha(0.5f);


        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                onPopDismiss();
            }
        });

        initView(popupWindowView);
        initData();
    }

    private static final String TAG = "BasePopView";

    /**
     * 显示view
     */
    public void show() {
        Log.d(TAG, "findResume: basepop：show");

        if (popupWindow == null) {
            initPopupWindow();
        } else {
            View parent = activity.getWindow().getDecorView().findViewById(android.R.id.content);
            Rect rect = new Rect();
            Window window = activity.getWindow();
            window.getDecorView().getWindowVisibleDisplayFrame(rect);
            popupWindow.showAtLocation(parent, Gravity.TOP | Gravity.LEFT, (int) marginWidth, (int) marginHeight);
            reShowData();
        }
    }

    public interface PopupDismissListener {
        void onDismiss();
    }

    //隐藏
    public void dismiss() {
        if (popupWindow == null) return;
        popupWindow.dismiss();
    }

    public void onPopDismiss(){
        backgroundAlpha(1f);
        if (popupDismissListener != null) {
            popupDismissListener.onDismiss();
        }
    }

    public void finish() {
        if (popupWindow == null) return;
        if (popupWindow.isShowing()) {
            popupWindow.dismiss();
        }
        popupWindow = null;
    }

    public void showPW(BasePopView popView) {
        popView.show();
    }

    public void showMsg(String msg) {
    }

    //设置隐藏监听
    public void setPopupDismissListener(PopupDismissListener popupDismissListener) {
        this.popupDismissListener = popupDismissListener;
    }


    /**********************************抽象方法***********************************************************/
    public abstract void initView(View view);

    public abstract void initData();

    public void reShowData() {
        initData();
    }

    public abstract int getLayoutId();

}
