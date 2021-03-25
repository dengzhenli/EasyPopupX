package org.fattili.easypopup_demo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import org.fattili.easypopup.view.dialog.AlertDialogPop

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onStart() {
        super.onStart()
    }

    fun topPop(view: View) {
        val pop = TopTestPop(this,view)
        pop.show()
    }

    fun dialogPop(view: View) {
        AlertDialogPop.Builder(this)
//                        .setTitle("sfsd")
            .setMessage("fsfsdf")
            .setMeasureButton(false, "确定", null)
            .setCancelButton(true, "取消", null)
            .show()
    }

    fun cardPop(view: View) {
        val pop = CardTestPop(this)
        pop.gravity = Gravity.TOP
        pop.show()
    }
}