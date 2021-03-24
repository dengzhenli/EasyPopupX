package org.fattili.easypopup.view.dialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.widget.ListAdapter;

import org.fattili.easypopup.R;
import org.fattili.easypopup.view.base.BasePopView;

/**
 * 2021/2/22
 *
 * @author dengzhenli
 */
public class AlertDialogPop extends DialogPop {

    public AlertDialogPop(Activity activity) {
        super(activity);
    }

    public AlertDialogPop(Activity activity, String str) {
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

    @Override
    public int getLayoutId() {
        return R.layout.ep_pop_alert_dialog;
    }


    public static class Builder {

        private AlertDialogPop pop;

        public Builder(Activity activity) {
            pop = new AlertDialogPop(activity);
        }

        public Context getContext() {
            return pop.getContext();
        }


        public Builder setTitle(CharSequence title) {
//            P.mTitle = title;
            return this;
        }
    }
}
