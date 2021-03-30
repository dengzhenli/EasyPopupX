package org.fattili.easypopup.util

import android.content.Context
import android.util.Xml
import android.view.Gravity
import org.fattili.easypopup.entity.XmlEntity
import org.xmlpull.v1.XmlPullParser

/**
 * 2021/2/24
 * xml解析工具
 * @author dengzhenli
 */
object XmlUtil {

    fun parse(context: Context, layoutId: Int): Map<String, XmlEntity> {
        val parser = context.resources.getLayout(layoutId)
        var type: Int
        while (
            parser.eventType.also { type = it } != XmlPullParser.START_TAG
            && type != XmlPullParser.END_DOCUMENT) {
            parser.next()
        }
        val count = parser.attributeCount

        val attributeSet = Xml.asAttributeSet(parser)
        var result = mutableMapOf<String, XmlEntity>()
        for (i in 0 until count) {
            val name: String = attributeSet.getAttributeName(i)
            val value: String = attributeSet.getAttributeValue(i)

            val entity = XmlEntity()
            if ("layout_gravity" == name) {
                entity.intValue = attributeSet.getAttributeIntValue(i, Gravity.CENTER)
            } else {
                entity.strValue = value
            }
            result[name] = entity
        }
        return result
    }

    fun getPx(context: Context, value: String): Int {

        if (value.endsWith("dip")) {
            val dp = value.replace("dip", "").toFloat()
            return ScreenUtil.dip2px(context, dp).toInt()
        }
        if (value.endsWith("px")) {
            val px = value.replace("px", "").toFloat()
            return px.toInt()
        }
        if (value.startsWith('@')) {
            val id = value.substring(1)
            return ResourcesUtil.getDimens(context, id.toInt())
        }
        return value.toInt()
    }

}