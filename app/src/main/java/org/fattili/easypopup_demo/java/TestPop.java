package org.fattili.easypopup_demo.java;

import android.app.Activity;
import android.view.View;
import android.widget.TextView;

import org.fattili.easypopup.view.EasyPop;
import org.fattili.easypopup_demo.R;

/**
 * 2021/2/22
 *
 * @author dengzhenli
 */
public class TestPop extends EasyPop {

    public TestPop(Activity activity) {
        super(activity);
    }

    @Override
    public void onPopCreated(View view) {
        TextView textView = findViewById(R.id.pop_example_text);
        textView.setText("我是普通弹出窗");
    }

    @Override
    public int getLayoutId() {
        return R.layout.pop_test;
    }


}
