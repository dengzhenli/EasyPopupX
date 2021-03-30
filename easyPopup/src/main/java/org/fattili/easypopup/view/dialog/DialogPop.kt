package org.fattili.easypopup.view.dialog

import android.app.Activity
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

    override fun onPopDismiss() {}

    constructor(activity: Activity?) : super(activity!!) {
    }


    override fun getLayoutId(): Int {
        return R.layout.ep_pop_dialog
    }

    constructor(activity: Activity?, gravity: Int, width: Int, height: Int) : super(activity!!) {
        this.gravity = gravity
        popupWidth = width
        popupHeight = height
        marginWidth = width
        marginHeight = height
    }


    abstract fun getContentLayoutId(): Int

    override fun initView(view: View?) {
        dataView = activity?.layoutInflater?.inflate(getContentLayoutId(), null)
        ep_pop_dialog_v.addView(dataView)
    }

}