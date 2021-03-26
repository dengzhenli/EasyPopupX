package org.fattili.easypopup_demo.java;

import android.app.Activity;
import android.view.View;

import org.fattili.easypopup.view.base.BasePopView;
import org.fattili.easypopup_demo.R;

/**
 * 2021/2/22
 *
 * @author dengzhenli
 */
public class TestPop extends BasePopView {

    public TestPop(Activity activity) {
        super(activity);
    }

    @Override
    public void initView(View view) {

    }

    @Override
    public void initData() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.pop_test;
    }

    @Override
    public void onPopDismiss() {

    }

    @Override
    public boolean outClickable() {
        return false;
    }


}
