package org.fattili.easypopup_demo.kt

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.pop_example.view.*
import org.fattili.easypopup.constant.EasyPopGravity
import org.fattili.easypopup.manager.EasyPopManager
import org.fattili.easypopup.util.ScreenUtil
import org.fattili.easypopup.view.EasyPop
import org.fattili.easypopup.view.dialog.AlertDialogPop
import org.fattili.easypopup_demo.R


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        TestPop(this).show()
    }

    fun topPop(view: View) {
        TopTestPop(this)
            .show()
    }

    fun dialogPop(view: View) {
        DialogTest(
            this, Gravity.CENTER,
            ScreenUtil.dip2px(this, 300F),
            ScreenUtil.dip2px(this, 400F)
        ).show()
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

        }.setAnimationStyle(R.style.bottomEnter).show()
    }

    @RequiresApi(Build.VERSION_CODES.KITKAT)
    fun viewLeft(view: View) {
        object : EasyPop(this@MainActivity) {

            override fun onPopCreated(view: View?) {
            }

            override fun getLayoutId(): Int {
                return R.layout.pop_test1
            }

        }
        .showOnView(viewLeft, EasyPopGravity.CENTER, EasyPopGravity.TO_LEFT)
        .show()
    }
    @RequiresApi(Build.VERSION_CODES.KITKAT)
    fun viewAboveLeft(view: View) {
        object : EasyPop(this@MainActivity) {

            override fun onPopCreated(view: View?) {
            }

            override fun getLayoutId(): Int {
                return R.layout.pop_test1
            }

        }.showOnView(viewAboveLeft, EasyPopGravity.ALIGN_LEFT, EasyPopGravity.TO_ABOVE).show()
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        EasyPopManager.onWindowFocusChanged(this, hasFocus)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        Log.e("EasyPopGravity", "onTouchEvent: " + event?.y )
        return super.onTouchEvent(event)
    }
}