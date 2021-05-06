package org.fattili.easypopup_demo.java;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LifecycleOwner;

import org.fattili.easypopup.manager.EasyPopManager;
import org.fattili.easypopup.util.ScreenUtil;
import org.fattili.easypopup.view.EasyPop;
import org.fattili.easypopup.view.dialog.AlertDialogPop;
import org.fattili.easypopup_demo.R;

/**
 * 2021/3/29
 *
 * @author dengzhenli
 */
public class MainActivity extends AppCompatActivity implements LifecycleOwner {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        EasyPopManager.INSTANCE.onWindowFocusChanged(this, hasFocus);
    }

    public void topPop(View view) {
        new TopTestPop(this).show();
    }

    public void alertDialogPop(View view) {

        new AlertDialogPop.Builder(this)
                .setTitle("标题")
                .setMessage("是否启动自毁程序")
                .setMeasureButton(true, null, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                })
                .setCancelButton(true, null, null)
                .show();
    }

    public void dialogPop(View view) {

        new DialogTest(this, Gravity.CENTER,
                ScreenUtil.INSTANCE.dip2px(this, 300F),
                ScreenUtil.INSTANCE.dip2px(this, 400F))
                .show();
    }

    public void cardPop(View view) {
        new CardTestPop(this).show();
    }

    public void normalPop(View view) {
//        new TestPop(this).register(this).show();
        new EasyPop(this) {

            @Override
            public void onPopCreated(@org.jetbrains.annotations.Nullable View view) {
                TextView textView = findViewById(R.id.pop_example_text);
                textView.setText("我是普通弹出窗");
            }

            @Override
            public int getLayoutId() {
                return R.layout.pop_test;
            }

        }.show();

    }
}
