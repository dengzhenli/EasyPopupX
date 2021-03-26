package org.fattili.easypopup.view.dialog;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import org.fattili.easypopup.R;

/**
 * 2021/2/22
 *
 * @author dengzhenli
 */
public class AlertDialogPop extends DialogPop {


    private ViewGroup titleView;
    private ViewGroup contentView;

    private TextView titleTv;
    private TextView msgTv;

    private Button measureBt;
    private Button cancelBt;


    private String paramMeasureBtText;
    private String paramCancelBtText;

    private boolean paramMeasureBtShow;
    private boolean paramCancelBtShow;

    private View.OnClickListener paramMeasureBtListener;
    private View.OnClickListener paramCancelBtListener;

    private String paramTitle;
    private String paramMsg;

    private View paramTitleView;
    private View paramContentView;


    public AlertDialogPop(Activity activity) {
        super(activity);
    }


    @Override
    public void initView(View view) {
        titleView = view.findViewById(R.id.id_ep_pop_alert_dialog_title_view);
        contentView = view.findViewById(R.id.id_ep_pop_alert_dialog_content_view);

        titleTv = view.findViewById(R.id.id_ep_pop_alert_dialog_title_tv);
        msgTv = view.findViewById(R.id.id_ep_pop_alert_dialog_msg_tv);

        measureBt = view.findViewById(R.id.id_ep_pop_alert_dialog_measure_bt);
        cancelBt = view.findViewById(R.id.id_ep_pop_alert_dialog_cancel_bt);

        cancelBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        titleView.setVisibility(View.GONE);
    }

    @Override
    public void initData() {
        if (!TextUtils.isEmpty(paramMsg)) {
            msgTv.setText(paramMsg);
        }
        if (!TextUtils.isEmpty(paramTitle)) {
            titleTv.setText(paramTitle);
            titleView.setVisibility(View.VISIBLE);
        }
        if (paramTitleView != null) {
            titleView.addView(paramTitleView);
            titleView.setVisibility(View.VISIBLE);
        }

        if (paramContentView != null) {
            contentView.addView(paramContentView);
        }


        cancelBt.setVisibility(paramCancelBtShow ? View.VISIBLE : View.GONE);
        measureBt.setVisibility(paramMeasureBtShow ? View.VISIBLE : View.GONE);

        if (!TextUtils.isEmpty(paramCancelBtText)) {
            cancelBt.setText(paramCancelBtText);
        }
        if (!TextUtils.isEmpty(paramMeasureBtText)) {
            measureBt.setText(paramMeasureBtText);
        }
        if (paramCancelBtListener != null) {
            cancelBt.setOnClickListener(paramCancelBtListener);
        }
        if (paramMeasureBtListener != null) {
            measureBt.setOnClickListener(paramMeasureBtListener);
        }

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


        public Builder setTitle(String title) {
            pop.paramTitle = title;
            return this;
        }

        public Builder setTitleView(View titleView) {
            pop.paramTitleView = titleView;
            return this;
        }

        public Builder setMessage(String msg) {
            pop.paramMsg = msg;
            return this;
        }

        public Builder setContentView(View contentView) {
            pop.paramContentView = contentView;
            return this;
        }

        public Builder setMeasureButton(boolean show, String text, View.OnClickListener clickListener) {
            pop.paramMeasureBtShow = show;
            pop.paramMeasureBtText = text;
            pop.paramMeasureBtListener = clickListener;
            return this;
        }

        public Builder setCancelButton(boolean show, String text, View.OnClickListener clickListener) {
            pop.paramCancelBtShow = show;
            pop.paramCancelBtText = text;
            pop.paramCancelBtListener = clickListener;
            return this;
        }

        public AlertDialogPop show() {
            pop.show();
            return pop;
        }
    }

    @Override
    public boolean outClickable() {
        return false;
    }
}
