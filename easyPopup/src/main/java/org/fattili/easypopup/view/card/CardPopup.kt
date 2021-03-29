package org.fattili.easypopup.view.card

import android.app.Activity
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import org.fattili.easypopup.R
import org.fattili.easypopup.view.base.BasePopView
import kotlinx.android.synthetic.main.ep_pop_card.view.*

/**
 * 卡片式弹出窗
 */
abstract class CardPopup : BasePopView {

    private var dataView: View? = null

    constructor(activity: Activity?) : this(
        activity,
        Gravity.BOTTOM,
        ViewGroup.LayoutParams.MATCH_PARENT,
        ViewGroup.LayoutParams.WRAP_CONTENT
    )

    constructor(activity: Activity?, gravity: Int, width: Int, height: Int) : super(activity!!) {
        this.gravity = gravity
        setWidth(width)
        setHeight(height)
    }


    override fun onPopDismiss() {
    }

    override fun getLayoutId(): Int {
        return R.layout.ep_pop_card
    }

    abstract fun getContentLayoutId(): Int

    override fun initView(view: View?) {

        dataView = activity.layoutInflater.inflate(getContentLayoutId(), null)
        ep_pop_card_lv.addView(dataView)
        ep_pop_card_back_iv.setOnClickListener { dismiss() }
    }

    override fun initData() {
    }

}