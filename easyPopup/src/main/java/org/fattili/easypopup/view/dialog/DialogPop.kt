package org.fattili.easypopup.view.dialog

import android.app.Activity
import android.os.Build
import android.view.View
import kotlinx.android.synthetic.main.ep_pop_dialog.view.*
import org.fattili.easypopup.R
import org.fattili.easypopup.view.EasyPop

/**
 * 2021/2/22
 * 通用dialog
 * @author dengzhenli
 */
abstract class DialogPop : EasyPop {
    private var dataView: View? = null


    override fun initData() {}

    constructor(activity: Activity) : super(activity) {
    }


    override fun getLayoutId(): Int {
        return R.layout.ep_pop_dialog
    }

   constructor(activity: Activity, gravity: Int, width: Int, height: Int) : super(activity) {
        this.gravity = gravity
        setWidth(width)
        setHeight(height)
        if (!useBackGround()){
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                backBackground = resources.getDrawable(R.color.ep_transparent,activity.theme)
            }
        }
    }


    abstract fun getContentLayoutId(): Int


    override fun initView(view: View?) {
        dataView = activity?.layoutInflater?.inflate(getContentLayoutId(), null)
        if (!useBackGround()){
            ep_pop_dialog_v.setBackgroundResource(R.color.ep_transparent)
        }
        ep_pop_dialog_v.addView(dataView)
    }

    abstract fun useBackGround(): Boolean

}