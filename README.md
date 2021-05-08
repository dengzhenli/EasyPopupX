[![](https://jitpack.io/v/dengzhenli/EasyPopupX.svg)](https://jitpack.io/#dengzhenli/EasyPopupX)

---
# 关于EasyPopupX

EasyPopupX是一个可以让你在项目里面轻松使用PopupWindow的工具。
你只需按正常的使用习惯即可，其他的事情都交给EasyPopupX

目前EasyPopupX能带给你的明显变化
* 只需极少的代码
* PopupWindow相关的安全性，兼容性，内存优化都交给EasyPopupX去考虑
* 可以在布局文件里面配置PopupWindow位置了
* 可以在PopupWindow里面直接使用findViewById
* 其他更多属性

你可以通过EasyPopupX轻松实现例如以下的页面
<div>
    <img src="img/pop_top.jpeg" width="150">
    <img src="img/pop_card.jpeg" width="150">
    <img src="img/pop_dialog.jpeg" width="150">
</div>

---
# 更新记录
## 1.0.3
* 删除过多的抽象方法
* 优化接口
* 删除意义不大的Lifecycle

---
# 快速接入

## 添加依赖
根项目build.gradle添加jitpack
```
allprojects {
    repositories {
        .....
        maven { url "https://jitpack.io" }
    }
}
```
添加依赖
```
dependencies {
     implementation 'com.github.dengzhenli:EasyPopupX:1.0.2'
}
```

如果项目没有使用kotlin导致报kotlin错误，需添加依赖  
```
dependencies {
    // XXX为kt版本号，例如：1.4.21
    implementation "org.jetbrains.kotlin:kotlin-stdlib:XXX"
}
```

## 使用

### 在需要调用时候创建EasyPop的实现类
```kotlin
    fun normalPop(view: View) {
         object : EasyPop(this@MainActivity) {   
               // 你自己的布局文件ID
               override fun getLayoutId(): Int {
                   return R.layout.pop_test
               }    
               // 布局创建完成，在这里写你的逻辑代码 
               override fun onPopCreated(view: View?) {
                   pop_example_text.text = "我是普通弹出窗"
               }
           }.show()
    }
```
这里为了方便演示使用匿名内部类，实际开发建议单独创建一个类继承EasyPop。  

你也可以使用java接入，为节省篇幅，演示只用kotlin，java的使用可参考demo。  

***pop_test.xml***
```XML
<?xml version="1.0" encoding="utf-8"?>

<androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:layout_height="400dp"
    android:layout_marginTop="20dp"
    android:layout_gravity="top">

    <include layout="@layout/pop_example"/>

</androidx.constraintlayout.widget.ConstraintLayout>
```



### 在onWindowFocusChanged方法调用EasyPopManager.onWindowFocusChanged  
这一步不是必须的，只是兼容一些不显示的情况
```kotlin
    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        EasyPopManager.onWindowFocusChanged(this, hasFocus)
    }
```


---
# 方法
## 可继承方法
```
    /**
     * 布局文件
     */
    abstract fun getLayoutId(): Int

    /**
     * PopupWindow初始化时候回调
     */
    open fun onPopInit() {}

    /**
     * PopupWindow创建成功时候回调
     */
    abstract fun onPopCreated(view: View?)

    /**
     * PopupWindow重新加载时候回调
     */
    open fun onPopReShow() {}

    /**
     * PopupWindow关闭时候回调
     */
    open fun onPopDismiss() {}
```
## PopupWindow设置

    /**
     * 显示view
     */
    fun show(): EasyPop {
        return showPop()
    }

    fun finish(): EasyPop {
        finishPop()
        return this
    }

    fun dismiss(): EasyPop {
        dismissPop()
        return this
    }

### 设置宽度
* `fun setWidth(width: Int): EasyPop`
```kotlin
    fun topPop(view: View) {
        TestPop(this)
        .setWidth(500)
        .show()
    }
```
### 设置高度
* `fun setHeight(height: Int): EasyPop`
```kotlin
    fun topPop(view: View) {
        TestPop(this)
        .setHeight(500)
        .show()
    }
```

### 背景设置
* `fun setBackGround(value: Drawable): EasyPop`
```kotlin
    fun topPop(view: View) {
        TestPop(this)
        .setBackGround(ColorDrawable(Color.TRANSPARENT))
        .show()
    }
```

    fun setBgAlpha(value: Float): EasyPop {
        popBgAlpha = value
        return this
    }


    fun outClickable(clickable: Boolean): EasyPop {
        popFocusable = clickable
        isOutsideTouchable = clickable
        return this
    }


    fun setGravity(value: Int): EasyPop {
        popGravity = value
        return this
    }

   fun showOnView(view: View): EasyPop {
        showAtView = view
        return this
    }

    fun showOnView(
        view: View,
        vararg gravity: EasyPopGravity
    ): EasyPop {
        showOnView(view)
        easyPopGravity = EasyPopGravity.CENTER.code
        for (i in gravity.indices) {
            easyPopGravity = easyPopGravity  or gravity[i].code
        }
        return this
    }


 fun setMarginWidth(value: Int): EasyPop {
        popMarginWidth = value
        return this
    }

    fun setMarginHeight(value: Int): EasyPop {
        popMarginHeight = value
        return this
    }



 
# 属性

## EasyPop属性

调用示例
```kotlin
val easyPop = TestPop(this);
easyPop.gravity = Gravity.RIGHT
easyPop.setG
easyPop.show()
```

属性名|属性描述|单位
---|---|---
popupWidth|弹窗的宽度|px
popupHeight|弹窗的高度|px
viewWidth| 弹窗内view的宽度|px
viewHeight| 弹窗内view的高度|px
gravity| 弹窗方向，使用android.view.Gravity的值|int
marginWidth| 弹窗方向基础上距离水平距离|px
marginHeight| 弹窗方向基础上距离垂直距离|px
bgAlpha| 弹窗背景透明度 0-1 |float
isShow| 弹窗是否显示状态|boolean


## XML属性

用于自己定制layout的配置能透传到easypop，例如如下的android:layout_height，不使用easypop的话是不会生效的，
但我觉得这种样式与布局的配置就应该在xml里面设置。easypop已对基本的配置都进行兼容，你只要按照正常的习惯配置即可。
```xml
<?xml version="1.0" encoding="utf-8"?>

<LinearLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="400dp" 
    >
    <include layout="@layout/pop_example"/>

</LinearLayout>
```
这里的设置本质上是修改easypop的属性，若被影响属性在代码里面手动设置过，则使用手动设置的属性  

属性名|影响属性|备注
---|---|---
"layout_width" |viewWidth popupWidth |
"layout_height" | viewHeight popupHeight |
"layout_gravity"| gravity |
"layout_marginLeft"|   marginWidth |
"layout_marginRight"|   marginWidth |优先于layout_marginLeft
"layout_marginHorizontal"|   marginWidth |优先于layout_marginRight
"layout_marginStart"|  marginWidth |优先于layout_marginHorizontal
"layout_marginEnd"|  marginWidth |优先于layout_marginEnd
"layout_marginBottom"| marginHeigh|
"layout_marginTop"|   marginHeight 优先于layout_marginBottom
"layout_marginVertical"| marginHeight |优先于layout_marginTop
"layout_margin" |  marginWidth     marginHeight |优先于layout_marginVertical，layout_marginHorizontal
  
## 可用方法

方法|描叙
---|---
getLayoutId(): Int|抽象方法，必须实现，设置布局ID
initView(view: View?)|抽象方法，必须实现，编写页面逻辑
initData(）|抽象方法，必须实现，初始化数据
outClickable(): Boolean|点击外部弹窗是否消失    
show(): EasyPop|显示弹窗
finish()|关闭弹窗
dismiss()|隐藏弹窗 
setWidth(width: Int) |设置弹窗宽度，影响viewWidth popupWidth
setHeight(height: Int) |设置弹窗高度 viewHeight popupHeight
onWindowFocusChanged(hasWindowFocus: Boolean)|activity回调方法

### 回调方法
方法|描叙
 ---|---
onReShowPop()|  pop重新加载时调用
onCreatePop() | pop初次加载时调用
onPopDismiss()| pop隐藏时调用
 
 
### 生命周期
easypop通过lifecycle监听Activity的生命周期，因为弹窗调用时候基本activity已经加载完毕，故不监听onCreate和onStart方法  

方法|描叙
---|---
 onDestroy()|Activity生命周期
 onResume()|Activity生命周期
 onPause()|Activity生命周期
 onStop()|Activity生命周期
 

---
# 组件



## 卡片式弹出窗

### CardPopup 卡片式弹出窗
使用：创建一个继承于CardPopup的弹窗，其中getContentLayoutId指定你的布局
```kotlin
class CardTestPop : CardPopup {
    constructor(activity: Activity) : super(activity) {}

    override fun outClickable(): Boolean { return true   }

    override fun getContentLayoutId(): Int {
        return R.layout.card_pop_test
    }
}
```

```kotlin
fun cardPop(view: View) {
    CardTestPop(this).show()
}
```
```xml
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="wrap_content">

    <include layout="@layout/pop_example"/>
</LinearLayout>

```

![img](img/pop_card.jpeg)
CardPopup有两个构造方法  
* constructor(activity: Activity, gravity: Int, width: Int, height: Int)  

        gravity：弹窗方向   
        
        width：弹窗宽度   
        
        height：弹窗高度   

* constructor(activity: Activity)  

        默认gravity为Gravity.BOTTOM   
        
        默认width为MATCH_PARENT   
        
        默认height为WRAP_CONTENT   



## dialog

### DialogPop 通用dialog
示例：
```kotlin
class DialogTest : DialogPop {
    constructor(activity: Activity) : super(activity) {}

    constructor(activity: Activity, gravity: Int, width: Int, height: Int) : super(
        activity,
        gravity,
        width,
        height
    ) {
    }

    override fun getContentLayoutId(): Int {
        return R.layout.pop_test
    }

    override fun outClickable(): Boolean {
        return true
    }

    // 是否使用DialogPop自带背景，选择否，则使用透明背景
    override fun useBackGround(): Boolean {
        return false
    }

}
```

```kotlin
        DialogTest(this, Gravity.CENTER,
            ScreenUtil.dip2px(this,300F),
            ScreenUtil.dip2px(this,400F))
            .show()
```

![img](img/pop_dialog2.jpg)
DialogPop有两个构造方法  
* constructor(activity: Activity, gravity: Int, width: Int, height: Int)  

        gravity：弹窗方向   
        
        width：弹窗宽度   
        
        height：弹窗高度   

* constructor(activity: Activity)  

        默认gravity为Gravity.BOTTOM   
        
        默认width为MATCH_PARENT   
        
        默认height为WRAP_CONTENT   

### AlertDialogPop 标准dialog
AlertDialogPop的用法可参考安卓AlertDialog  
```kotlin

    fun alertDialogPop(view: View) {
        AlertDialogPop.Builder(this)
            .setTitle("标题")
            .setMessage("是否进行下一步操作")
            .setMeasureButton(true, null, View.OnClickListener { finish() })
            .setCancelButton(true, null, null)
            .show()
    }
```
![img](img/pop_dialog.jpeg)
## 其他组件
* BottomPop 底部弹出窗
* LeftPop 左侧弹出窗
* RightPop 右侧弹出窗
* TopPop 顶部弹出窗

# 常见问题
## 提示创建失败，请等待页面渲染完毕怎么办？
参考[在onWindowFocusChanged方法调用EasyPopManager.onWindowFocusChanged](#在onwindowfocuschanged方法调用easypopmanageronwindowfocuschanged) 

# License

EasyPopupX is Apache 2 licensed, as found in the LICENSE file.
