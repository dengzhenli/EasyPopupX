package org.fattili.easypopup.view.dialog

import android.app.Activity
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.os.Build
import android.view.Gravity
import android.view.View
import kotlinx.android.synthetic.main.ep_pop_dialog.view.*
import org.fattili.easypopup.R
import org.fattili.easypopup.util.ScreenUtil
import org.fattili.easypopup.view.EasyPop

/**
 * 2021/2/22
 * 通用dialog
 * @author dengzhenli
 */
abstract class DialogPop : EasyPop {
    private var dataView: View? = null


    constructor(activity: Activity) : super(activity) {
        setAnimationStyle(R.style.ep_common_pop_scale_theme)
    }


    override fun getLayoutId(): Int {
        return R.layout.ep_pop_dialog
    }

   constructor(activity: Activity, gravity: Int, width: Int, height: Int) : super(activity) {
        setGravity(gravity)
        setWidth(width)
        setHeight(height)
    }

    override fun onPopInit() {
        super.onPopInit()
        if (!useBackGround()){
            setBackGround(ColorDrawable(Color.TRANSPARENT))
        }
    }

    abstract fun getContentLayoutId(): Int

    override fun onPopCreated(view: View?)  {
        dataView = activity?.layoutInflater?.inflate(getContentLayoutId(), null)
        if (!useBackGround()){
            ep_pop_dialog_v.background = ColorDrawable(Color.TRANSPARENT)
        } else {
            val padding = ScreenUtil.dip2px(context, 4f)
            ep_pop_dialog_v?.setPadding(padding,padding,padding,padding)
        }
        ep_pop_dialog_v.addView(dataView)
    }


    abstract fun useBackGround(): Boolean

}