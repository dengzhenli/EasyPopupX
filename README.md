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


![示例图片](https://raw.githubusercontent.com/dengzhenli/EasyPopupX/master/img/pop_example.gif)

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
     implementation 'com.github.dengzhenli:EasyPopupX:1.0.4'
}
```

如果项目没有使用kotlin导致报kotlin错误，需添加依赖  
```
dependencies {
    // XXX为kt版本号，例如：1.4.21
    implementation "org.jetbrains.kotlin:kotlin-stdlib:XXX"
}
```

## 编写布局

编写你的layout文件，android:layout_height等表示弹窗的大小，android:layout_gravity表示弹窗弹出的位置。  
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
## 在需要调用时候创建EasyPop的实现类
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

你也可以使用java接入
```java
public class MainActivity extends AppCompatActivity {

    public void normalPop(View view) {
        new EasyPop(this) {

            @Override
            public void onPopCreated(View view) {
                TextView textView = findViewById(R.id.pop_example_text);
                textView.setText("我是普通弹出窗");

            }

            @Override
            public int getLayoutId() {
                return R.layout.pop_test;
            }

        }.show();
    }
}
```
为节省篇幅，演示只用kotlin，java的使用可参考demo(app module)。  

更多用法，参见[wiki](https://github.com/dengzhenli/EasyPopupX/wiki/%E9%A6%96%E9%A1%B5) 
- [快速接入](https://github.com/dengzhenli/EasyPopupX/wiki/%E5%BF%AB%E9%80%9F%E6%8E%A5%E5%85%A5)
- [方法](https://github.com/dengzhenli/EasyPopupX/wiki/%E6%96%B9%E6%B3%95)
- [属性](https://github.com/dengzhenli/EasyPopupX/wiki/%E5%B1%9E%E6%80%A7)
- [组件](https://github.com/dengzhenli/EasyPopupX/wiki/%E7%BB%84%E4%BB%B6)
- [常见问题](https://github.com/dengzhenli/EasyPopupX/wiki/%E5%B8%B8%E8%A7%81%E9%97%AE%E9%A2%98)

# License

EasyPopupX 使用 Apache 2 licensed, 参见 LICENSE 文件.
