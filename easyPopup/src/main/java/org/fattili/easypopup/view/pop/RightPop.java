package org.fattili.easypopup.view.pop;

import android.app.Activity;
import android.view.Gravity;
import android.view.View;

import org.fattili.easypopup.view.base.BasePopView;

/**
 * 2021/2/22
 *
 * @author dengzhenli
 */
public abstract class RightPop extends BasePopView {

    public RightPop(Activity activity) {
        super(activity);
        setGravity(Gravity.RIGHT);
    }

    public RightPop(Activity activity,View view) {
        super(activity, view);
    }


    @Override
    public void initView(View view) {

    }

    @Override
    public void initData() {

    }


    @Override
    public void onPopDismiss() {

    }
}
