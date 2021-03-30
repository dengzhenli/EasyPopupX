package org.fattili.easypopup.manager

import android.app.Activity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent
import org.fattili.easypopup.view.EasyPop
import java.util.*

/**
 * 2021/3/30
 *
 * @author dengzhenli
 */
object EasyPopManager {

    private val activityEasyPopMap: MutableMap<Activity, MutableList<EasyPop>> = HashMap()
    private val activityOwnerMap: MutableMap<Activity, LifecycleOwner> = HashMap()


    fun add(activity: Activity, easyPop: EasyPop) {
        var list: MutableList<EasyPop> = ArrayList()

        if (activityEasyPopMap.containsKey(activity)) {
            activityEasyPopMap[activity]?.let { list = it }
        }

        list.add(easyPop)

        activityEasyPopMap[activity] = list
        activityOwnerMap[activity]?.let { easyPop.register(it) }
    }

    fun remove(activity: Activity, easyPop: EasyPop) {

        if (activityEasyPopMap.containsKey(activity)) {
            val list: List<EasyPop>? = activityEasyPopMap[activity]
            list?.let {
                var iterator = it.iterator() as MutableIterator<EasyPop>
                while (iterator.hasNext()) {
                    val pop = iterator.next();
                    if (pop == easyPop) {
                        iterator.remove()
                    }
                }
            }
        }

    }

    fun remove(activity: Activity) {

        if (activityEasyPopMap.containsKey(activity)) {
            activityEasyPopMap.remove(activity)
        }

        if (activityOwnerMap.containsKey(activity)) {
            activityOwnerMap.remove(activity)
        }
    }


    fun register(activity: Activity, owner: LifecycleOwner) {
        activityOwnerMap[activity] = owner
    }

    fun onWindowFocusChanged(activity: Activity?, hasFocus: Boolean) {
        val list: List<EasyPop>? = activityEasyPopMap[activity]
        list?.let {
            it.forEach { pop ->
                if (pop.isShow) {
                    pop.onWindowFocusChanged(hasFocus)
                }
            }
        }
    }

}