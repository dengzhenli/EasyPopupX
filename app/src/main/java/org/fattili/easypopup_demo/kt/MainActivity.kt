package org.fattili.easypopup_demo.kt

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleOwner
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.pop_example.view.*
import org.fattili.easypopup.except.ViewUnCreatedException
import org.fattili.easypopup.manager.EasyPopManager
import org.fattili.easypopup.util.ScreenUtil
import org.fattili.easypopup.view.EasyPop
import org.fattili.easypopup.view.base.BasePopupWindow
import org.fattili.easypopup.view.dialog.AlertDialogPop
import org.fattili.easypopup_demo.R


class MainActivity : AppCompatActivity(), LifecycleOwner {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        TestPop(this).show()
    }

    fun topPop(view: View) {
        TopTestPop(this).show()
    }

    fun dialogPop(view: View) {
        DialogTest(
            this, Gravity.CENTER,
            ScreenUtil.dip2px(this, 300F),
            ScreenUtil.dip2px(this, 400F)
        )
            .show()
    }

    fun alertDialogPop(view: View) {
        AlertDialogPop.Builder(this)
            .setTitle("标题")
            .setMessage("是否启动自毁程序")
            .setMeasureButton(true, null, View.OnClickListener { finish() })
            .setCancelButton(true, null, null)
            .show()
    }

    fun cardPop(view: View) {
        CardTestPop(this).show()
    }

    fun normalPop(view: View) {
        object : EasyPop(this@MainActivity) {

            override fun onPopCreated(view: View?) {
                pop_example_text.text = "我是普通弹出窗"
            }

            override fun getLayoutId(): Int {
                return R.layout.pop_test
            }

        }.show()
    }

    @RequiresApi(Build.VERSION_CODES.KITKAT)
    fun viewLeft(view: View) {
//        object : EasyPop(this@MainActivity) {
//
//            override fun onPopCreated(view: View?) {
//            }
//
//            override fun getLayoutId(): Int {
//                return R.layout.pop_test1
//            }
//
//        }
//            .showOnView(dddBt)
//            .setGravity(Gravity.LEFT)
//            .setWidth(200)
////            .setMarginWidth(200)
//            .setHeight(100)
//        .show()

        val viewWidth = 200
        val viewHeight = 200
        val popGravity = Gravity.BOTTOM or Gravity.END
        val popMarginWidth = 100
        val popMarginHeight = 200
        val inflate = LayoutInflater.from(this)
        val view = inflate?.inflate(R.layout.pop_test1, null)

        //内容，高度，宽度
        val popupWindow = BasePopupWindow(
            view,
            viewWidth,
            viewHeight,
            true
        )


        //动画效果
        popupWindow?.animationStyle = org.fattili.easypopup.R.style.ep_common_pop_theme
        //宽度
        popupWindow?.width = viewWidth
        //高度
        popupWindow?.height = viewHeight
        //显示位置
        popupWindow?.isOutsideTouchable = true
        popupWindow?.setBackgroundDrawable(ColorDrawable(Color.RED))


        try {
//            popupWindow?.showAtLocation(
//                view,
//                popGravity,
//                popMarginWidth,
//                popMarginHeight
//            )

            val g = EasyPopGravity.ALIGN_RIGHT or EasyPopGravity.TO_ABOVE
            popupWindow.showAtLocation(
                dddBt,
                Gravity.TOP or Gravity.START,
                EasyPopGravity.getXOff(dddBt,g,viewWidth ,0),
                EasyPopGravity.getYOff(dddBt,g,viewHeight,0)

            )
        } catch (e: Exception) {
            e.printStackTrace()
            val parent = view?.parent as ViewGroup
            parent.removeView(view)
            throw ViewUnCreatedException("easyPop调用失败，请先等待你的页面渲染完成")
        }
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        EasyPopManager.onWindowFocusChanged(this, hasFocus)
    }
}