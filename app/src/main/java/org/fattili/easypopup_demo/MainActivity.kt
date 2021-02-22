package org.fattili.easypopup_demo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import java.lang.Exception
import java.lang.Thread.sleep

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Thread{
            try {
                sleep(3000)
                runOnUiThread{
                    val pop = TestPop(this)
                    pop.show()
                }
            }catch (e:Exception){
                e.printStackTrace()
            }
        }.start()

    }

    override fun onStart() {
        super.onStart()
    }
}