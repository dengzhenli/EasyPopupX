package org.fattili.easypopup_demo;

import android.app.Activity;
import android.view.View;

import org.fattili.easypopup.BasePopView;

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
}
