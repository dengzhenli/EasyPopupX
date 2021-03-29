package org.fattili.easypopup.view.dialog

import android.app.Activity
import android.content.Context
import android.text.TextUtils
import android.view.View
import androidx.lifecycle.LifecycleOwner
import kotlinx.android.synthetic.main.ep_pop_alert_dialog.view.*
import org.fattili.easypopup.R

/**
 * 2021/2/22
 * 标准dialog
 * @author dengzhenli
 */
class AlertDialogPop(activity: Activity?) : DialogPop(activity) {


    private var paramMeasureBtText: String? = null
    private var paramCancelBtText: String? = null
    private var paramMeasureBtShow = false
    private var paramCancelBtShow = false
    private var paramMeasureBtListener: View.OnClickListener? = null
    private var paramCancelBtListener: View.OnClickListener? = null
    private var paramTitle: String? = null
    private var paramMsg: String? = null
    private var paramTitleView: View? = null
    private var paramContentView: View? = null

    override fun initView(view: View?) {
        ep_alert_dialog_cancel_bt?.setOnClickListener(View.OnClickListener { dismiss() })
        ep_alert_dialog_view?.visibility = View.GONE
    }

    override fun initData() {
        if (!TextUtils.isEmpty(paramMsg)) {
            ep_alert_dialog_msg_tv?.text = paramMsg
        }

        if (!TextUtils.isEmpty(paramTitle)) {
            ep_alert_dialog_title_tv?.text = paramTitle
            ep_alert_dialog_view?.visibility = View.VISIBLE
        }

        if (paramTitleView != null) {
            ep_alert_dialog_view?.addView(paramTitleView)
            ep_alert_dialog_view?.visibility = View.VISIBLE
        }

        if (paramContentView != null) {
            ep_alert_dialog_content_view?.addView(paramContentView)
        }

        ep_alert_dialog_cancel_bt?.visibility = if (paramCancelBtShow) View.VISIBLE else View.GONE
        ep_alert_dialog_measure_bt?.visibility = if (paramMeasureBtShow) View.VISIBLE else View.GONE

        if (!TextUtils.isEmpty(paramCancelBtText)) {
            ep_alert_dialog_cancel_bt?.text = paramCancelBtText
        }

        if (!TextUtils.isEmpty(paramMeasureBtText)) {
            ep_alert_dialog_measure_bt?.text = paramMeasureBtText
        }

        if (paramCancelBtListener != null) {
            ep_alert_dialog_cancel_bt?.setOnClickListener(paramCancelBtListener)
        }

        if (paramMeasureBtListener != null) {
            ep_alert_dialog_measure_bt?.setOnClickListener(paramMeasureBtListener)
        }
    }

    override fun onPopDismiss() {}
    override fun getLayoutId(): Int {
        return R.layout.ep_pop_alert_dialog
    }

    class Builder(activity: Activity?) {
        private val pop: AlertDialogPop = AlertDialogPop(activity)
        fun getContext(): Context {
            return pop.getContext()
        }

        fun setTitle(title: String?): Builder {
            pop.paramTitle = title
            return this
        }

        fun setTitleView(titleView: View?): Builder {
            pop.paramTitleView = titleView
            return this
        }

        fun setMessage(msg: String?): Builder {
            pop.paramMsg = msg
            return this
        }

        fun setContentView(contentView: View?): Builder {
            pop.paramContentView = contentView
            return this
        }

        fun setMeasureButton(
            show: Boolean,
            text: String?,
            clickListener: View.OnClickListener?
        ): Builder {
            pop.paramMeasureBtShow = show
            pop.paramMeasureBtText = text
            pop.paramMeasureBtListener = clickListener
            return this
        }

        fun setCancelButton(
            show: Boolean,
            text: String?,
            clickListener: View.OnClickListener?
        ): Builder {
            pop.paramCancelBtShow = show
            pop.paramCancelBtText = text
            pop.paramCancelBtListener = clickListener
            return this
        }

        fun show(): AlertDialogPop {
            pop.show()
            return pop
        }

        fun register(owner: LifecycleOwner?): AlertDialogPop {
            owner?.let { pop.register(it) }
            return pop
        }

    }

    override fun outClickable(): Boolean {
        return false
    }
}