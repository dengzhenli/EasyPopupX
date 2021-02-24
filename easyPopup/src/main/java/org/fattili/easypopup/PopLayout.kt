package org.fattili.easypopup

import android.R
import android.content.Context
import android.content.res.Resources
import android.util.AttributeSet
import android.util.Log
import android.util.Xml
import android.widget.LinearLayout
import org.xmlpull.v1.XmlPullParser


/**
 * 2021/2/23
 *
 * @author dengzhenli
 */
class PopLayout : LinearLayout {
    constructor(context: Context?) : super(context) {
        Log.d("fff", ": 3")}
    constructor(context: Context?, attrs: AttributeSet?) : super(
        context,
        attrs
    ) {
        val parser = resources.getLayout(R.layout.)
        var type: Int
        while (parser.eventType
                .also { type = it } != XmlPullParser.START_TAG
            && type != XmlPullParser.END_DOCUMENT
        ) {
            parser.next()
        }
        val count = parser.attributeCount
        Log.d("fff", "count: " + parser.attributeCount)

        val attributeSet = Xml.asAttributeSet(parser)
        Log.d("fff", ": 2")
        for (i in 0 until count) {
            val name: String = attributeSet.getAttributeName(i)
            val value: String = attributeSet.getAttributeValue(i)
            Log.d("fff", "attribute: $name   value: $value")
        }
        val resourceId = attributeSet.getAttributeResourceValue(
            "http://schemas.android.com/apk/res/android",
            "layout_height",
            0
        )
        val dimension = resources.getDimension(resourceId)
        Log.d("fff", "layout_height: $dimension")
    }

    constructor(
        context: Context?,
        attrs: AttributeSet?,
        defStyleAttr: Int
    ) : super(context, attrs, defStyleAttr) {

        Log.d("fff", ": 1")

        val s = Resources.getSystem().getIdentifier("View", "styleable", "android")
        val ss = Resources.getSystem().getIntArray(s)
        val a = context!!.obtainStyledAttributes(attrs, ss, defStyleAttr, 0)
        val n = a.indexCount - 1;
        for (index in 0..n) {
            val attr = a.getIndex(index)
            Log.d("fff", ": $attr")
        }
    }

}