package org.fattili.easypopup.view.card

import android.app.Activity
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import org.fattili.easypopup.R
import org.fattili.easypopup.view.base.BasePopView

abstract class CardPopup : BasePopView {

    var back: ImageView? = null
    var dataView: View? = null
    var dataLayout: ViewGroup? = null

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
        return R.layout.lm_song_popup_song_add_to_songs
    }

    abstract fun getContentLayoutId(): Int

    override fun initView(view: View?) {
        back = view?.findViewById(R.id.lm_song_song_add_to_songs_back_iv)
        back?.setOnClickListener { dismiss() }

        dataLayout = view?.findViewById(R.id.lm_song_song_add_to_songs_lv)
        dataView = activity.layoutInflater.inflate(getContentLayoutId(), null)
        dataLayout?.addView(dataView)
    }

    override fun initData() {
    }


}