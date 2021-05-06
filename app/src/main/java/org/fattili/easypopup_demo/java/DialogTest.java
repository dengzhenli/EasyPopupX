package org.fattili.easypopup_demo.java;

import android.app.Activity;
import org.fattili.easypopup.view.dialog.DialogPop;
import org.fattili.easypopup_demo.R;
import org.jetbrains.annotations.NotNull;

/**
 * 2021/4/2
 *
 * @author dengzhenli
 */
public class DialogTest extends DialogPop {


    public DialogTest(@NotNull Activity activity) {
        super(activity);
    }

    public DialogTest(@NotNull Activity activity, int gravity, int width, int height) {
        super(activity, gravity, width, height);
        outClickable(true);
    }

    @Override
    public int getContentLayoutId() {
        return R.layout.pop_test;
    }


    @Override
    public boolean useBackGround() {
        return false;
    }
}
