package org.fattili.easypopup_demo.java;

import android.app.Activity;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;

import org.fattili.easypopup.view.card.CardPopup;
import org.fattili.easypopup_demo.R;
import org.jetbrains.annotations.Nullable;

/**
 * 2021/2/22
 *
 * @author dengzhenli
 */
public class CardTestPop extends CardPopup {


    public CardTestPop(@Nullable Activity activity) {
        super(activity);
    }

    public CardTestPop(@Nullable Activity activity, int gravity, int width, int height) {
        super(activity, gravity, width, height);
    }

    @Override
    public boolean outClickable() {
        return true;
    }

    @Override
    public int getContentLayoutId() {
        return R.layout.card_pop_test;
    }

}
