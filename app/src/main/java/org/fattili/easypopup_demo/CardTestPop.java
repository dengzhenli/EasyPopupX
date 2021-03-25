package org.fattili.easypopup_demo;

import android.app.Activity;
import android.view.View;

import org.fattili.easypopup.view.card.CardPopup;

/**
 * 2021/2/22
 *
 * @author dengzhenli
 */
public class CardTestPop extends CardPopup {

    public CardTestPop(Activity activity) {
        super(activity);
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
