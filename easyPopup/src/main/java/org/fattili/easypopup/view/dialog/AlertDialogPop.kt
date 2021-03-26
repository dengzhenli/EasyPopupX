package org.fattili.easypopup.view.dialog

import android.app.Activity
import android.content.Context
import android.text.TextUtils
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.LifecycleOwner
import org.fattili.easypopup.R

/**
 * 2021/2/22
 *
 * @author dengzhenli
 */
class AlertDialogPop(activity: Activity?) : DialogPop(activity) {
    private var titleView: ViewGroup? = null
    private var contentView: ViewGroup? = null
    private var titleTv: TextView? = null
    private var msgTv: TextView? = null
    private var measureBt: Button? = null
    private var cancelBt: Button? = null
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
        titleView = view?.findViewById(R.id.id_ep_pop_alert_dialog_title_view)
        contentView = view?.findViewById(R.id.id_ep_pop_alert_dialog_content_view)
        titleTv = view?.findViewById(R.id.id_ep_pop_alert_dialog_title_tv)
        msgTv = view?.findViewById(R.id.id_ep_pop_alert_dialog_msg_tv)
        measureBt = view?.findViewById(R.id.id_ep_pop_alert_dialog_measure_bt)
        cancelBt = view?.findViewById(R.id.id_ep_pop_alert_dialog_cancel_bt)
        cancelBt?.setOnClickListener(View.OnClickListener { dismiss() })
        titleView?.setVisibility(View.GONE)
    }

    override fun initData() {
        if (!TextUtils.isEmpty(paramMsg)) {
            msgTv?.text = paramMsg
        }
        if (!TextUtils.isEmpty(paramTitle)) {
            titleTv?.text = paramTitle
            titleView?.visibility = View.VISIBLE
        }
        if (paramTitleView != null) {
            titleView?.addView(paramTitleView)
            titleView?.visibility = View.VISIBLE
        }
        if (paramContentView != null) {
            contentView?.addView(paramContentView)
        }
        cancelBt?.visibility = if (paramCancelBtShow) View.VISIBLE else View.GONE
        measureBt?.visibility =
            if (paramMeasureBtShow) View.VISIBLE else View.GONE
        if (!TextUtils.isEmpty(paramCancelBtText)) {
            cancelBt?.text = paramCancelBtText
        }
        if (!TextUtils.isEmpty(paramMeasureBtText)) {
            measureBt?.text = paramMeasureBtText
        }
        if (paramCancelBtListener != null) {
            cancelBt?.setOnClickListener(paramCancelBtListener)
        }
        if (paramMeasureBtListener != null) {
            measureBt?.setOnClickListener(paramMeasureBtListener)
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