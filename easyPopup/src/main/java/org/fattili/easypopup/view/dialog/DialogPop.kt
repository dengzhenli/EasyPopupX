package org.fattili.easypopup.view.dialog

import android.app.Activity
import android.graphics.Color
import android.graphics.drawable.ColorDrawable

import android.view.View
import android.view.ViewGroup
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

    private var dialogView: ViewGroup? = null


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
        dialogView = findViewById(R.id.ep_pop_dialog_v)
        if (!useBackGround()){
            dialogView?.background = ColorDrawable(Color.TRANSPARENT)
        } else {
            val padding = ScreenUtil.dip2px(context, 4f)
            dialogView?.setPadding(padding,padding,padding,padding)
        }
        dialogView?.addView(dataView)
    }


    abstract fun useBackGround(): Boolean

}