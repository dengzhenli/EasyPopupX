package org.fattili.easypopup_demo.kt

import android.app.Activity
import android.util.Log
import org.fattili.easypopup.view.card.CardPopup
import org.fattili.easypopup_demo.R

/**
 * 2021/2/22
 *
 * @author dengzhenli
 */
class CardTestPop : CardPopup {
    constructor(activity: Activity) : super(activity) {}
    constructor(activity: Activity, gravity: Int, width: Int, height: Int) : super(
        activity,
        gravity,
        width,
        height
    ) {
    }



    override fun getContentLayoutId(): Int {
        return R.layout.card_pop_test
    }

    private val TAG = "CardTestPop"

}