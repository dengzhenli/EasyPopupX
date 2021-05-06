package org.fattili.easypopup_demo.java;

import android.app.Activity;
import android.view.View;
import android.widget.TextView;

import org.fattili.easypopup.view.pop.TopPop;
import org.fattili.easypopup_demo.R;

/**
 * 2021/2/22
 *
 * @author dengzhenli
 */
public class TopTestPop extends TopPop {

    public TopTestPop(Activity activity) {
        super(activity);
    }



    @Override
    public void onPopCreated(View view) {
        TextView textView = findViewById(R.id.pop_example_text);
        textView.setText("我是顶部弹出窗");
    }


    @Override
    public int getLayoutId() {
        return R.layout.top_pop_test;
    }


}
