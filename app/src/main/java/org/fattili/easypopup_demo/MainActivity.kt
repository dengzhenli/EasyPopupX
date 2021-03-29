package org.fattili.easypopup_demo

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleOwner
import org.fattili.easypopup.view.dialog.AlertDialogPop
import org.fattili.easypopup_demo.kt.CardTestPop
import org.fattili.easypopup_demo.kt.TopTestPop


class MainActivity : AppCompatActivity(), LifecycleOwner {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onDestroy() {
        super.onDestroy()
    }
    override fun onStart() {
        super.onStart()
    }

    fun topPop(view: View) {
        TopTestPop(this).register(this).show()
    }

    fun dialogPop(view: View) {
        AlertDialogPop.Builder(this)
            .setTitle("标题")
            .setMessage("是否启动自毁程序")
            .setMeasureButton(true, null, View.OnClickListener { finish() })
            .setCancelButton(true, null, null)
            .register(this)
            .show()
    }

    fun cardPop(view: View) {
        CardTestPop(this).register(this).show()
    }
}