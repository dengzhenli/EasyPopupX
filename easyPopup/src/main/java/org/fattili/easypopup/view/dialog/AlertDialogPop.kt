package org.fattili.easypopup.view.dialog

import android.app.Activity
import android.content.Context
import android.text.TextUtils
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import org.fattili.easypopup.R

/**
 * 2021/2/22
 * 标准dialog
 * @author dengzhenli
 */
class AlertDialogPop(activity: Activity) : DialogPop(activity) {


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


    private var dialogView: ViewGroup? = null
    private var contentView: ViewGroup? = null
    private var msgTv: TextView? = null
    private var titleTv: TextView? = null
    private var measureBt: Button? = null
    private var cancelBt: Button? = null


    override fun onPopCreated(view: View?) {

        initView()
        initData()
    }

    private fun initView() {
        dialogView = findViewById(R.id.ep_alert_dialog_view)
        contentView = findViewById(R.id.ep_alert_dialog_content_view)
        msgTv = findViewById(R.id.ep_alert_dialog_msg_tv)
        titleTv = findViewById(R.id.ep_alert_dialog_title_tv)
        measureBt = findViewById(R.id.ep_alert_dialog_measure_bt)
        cancelBt = findViewById(R.id.ep_alert_dialog_cancel_bt)

        cancelBt?.setOnClickListener(View.OnClickListener { finish() })
        dialogView?.visibility = View.GONE
    }

    private fun initData() {
        if (!TextUtils.isEmpty(paramMsg)) {
            msgTv?.text = paramMsg
        }

        if (!TextUtils.isEmpty(paramTitle)) {
            titleTv?.text = paramTitle
            dialogView?.visibility = View.VISIBLE
        }

        if (paramTitleView != null) {
            titleTv?.visibility = View.GONE
            dialogView?.addView(paramTitleView)
            dialogView?.visibility = View.VISIBLE
        }

        if (paramContentView != null) {
            msgTv?.visibility = View.GONE
            contentView?.addView(paramContentView)
        }

        cancelBt?.visibility = if (paramCancelBtShow) View.VISIBLE else View.GONE
        measureBt?.visibility = if (paramMeasureBtShow) View.VISIBLE else View.GONE

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


    override fun getLayoutId(): Int {
        return R.layout.ep_pop_alert_dialog
    }

    override fun getContentLayoutId(): Int {
        return 0
    }

    class Builder(activity: Activity) {
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

    }


    override fun useBackGround(): Boolean {
        return false
    }
}