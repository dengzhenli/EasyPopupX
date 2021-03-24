package org.fattili.easypopup.view;

import android.app.Activity;
import android.view.View;

import org.fattili.easypopup.view.base.BasePopView;

/**
 * 2021/2/22
 *
 * @author dengzhenli
 */
public abstract class TestPop extends BasePopView {

    public TestPop(Activity activity) {
        super(activity);
    }

    public TestPop(Activity activity,String str) {
        super(activity);
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
