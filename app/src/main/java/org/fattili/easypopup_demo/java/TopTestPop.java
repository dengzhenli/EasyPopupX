package org.fattili.easypopup_demo;

import android.app.Activity;
import android.view.View;

import org.fattili.easypopup.view.base.BasePopView;
import org.fattili.easypopup.view.pop.TopPop;

/**
 * 2021/2/22
 *
 * @author dengzhenli
 */
public class TopTestPop extends TopPop {

    public TopTestPop(Activity activity) {
        super(activity);
    }

    public TopTestPop(Activity activity,View view) {
        super(activity, view);
    }


    @Override
    public void initView(View view) {

    }

    @Override
    public void initData() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.top_pop_test;
    }

    @Override
    public void onPopDismiss() {

    }

    @Override
    public boolean outClickable() {
        return true;
    }
}
