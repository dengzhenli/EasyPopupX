package org.fattili.easypopup_demo.kt

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleOwner
import org.fattili.easypopup.manager.EasyPopManager
import org.fattili.easypopup.view.EasyPop
import org.fattili.easypopup.view.dialog.AlertDialogPop
import org.fattili.easypopup_demo.R


class MainActivity : AppCompatActivity(), LifecycleOwner {

    var pop: EasyPop? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        EasyPopManager.register(this, this)
        pop = TestPop(this).show()
    }

    fun topPop(view: View) {

        TopTestPop(this).show()
    }

    fun dialogPop(view: View) {
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
        TestPop(this).show()
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        EasyPopManager.onWindowFocusChanged(this, hasFocus)
    }
}