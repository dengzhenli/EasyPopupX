# 关于EasyPopup
EasyPopup是一个可以让你在项目里面轻松使用PopupWindow的工具。
你只需按正常的使用习惯即可，其他的事情都交给EasyPopup

你可以通过EasyPopup轻松实现例如以下的页面
<figure class="third">    
    <img src="img/pop_top.jpeg" width="300">
    <img src="img/pop_card.jpeg" width="300">
    <img src="img/pop_dialog.jpeg" width="300">
</figure>

# 快速接入
## 添加依赖

## 定制你的PopupWindow
```kotlin
class TestPop(activity: Activity) : EasyPop(activity) {
    // 这里进行初始化视图操作
    override fun initView(view: View?) { pop_example_text.text = "我是普通弹出窗" }
    // 这里进行初始化数据操作
    override fun initData() {}
    // 这里输入你自己编写的布局文件
    override fun getLayoutId(): Int { return R.layout.pop_test }
    // 点击外部弹窗是否消失
    override fun outClickable(): Boolean { return true }
}
```

你也可以使用java接入，为节省篇幅，后面的演示只用kotlin，java的使用可参考demo
```java
public class TestPop extends EasyPop {
    public TestPop(Activity activity) {  super(activity); }
    @Override
    public void initView(View view) {
        TextView textView = findViewById(R.id.pop_example_text);
        textView.setText("我是普通弹出窗");
    }
    @Override
    public void initData() {}
    @Override
    public int getLayoutId() { return R.layout.pop_test;  }

    @Override
    public boolean outClickable() { return true;  }
}

```
## 调用
建议：activity实现 LifecycleOwner 接口，并在调用easypop之前调用register方法，
此方法会进行生命周期注册等步骤。若没有进行此步骤，EasyPop需要生命周期时候需要手动调用
```kotlin
class MainActivity : AppCompatActivity(), LifecycleOwner {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        EasyPopManager.register(this, this)
    }
}
```
建议：在onWindowFocusChanged方法调用EasyPopManager.onWindowFocusChanged
```kotlin
    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        EasyPopManager.onWindowFocusChanged(this, hasFocus)
    }
```
必须：在需要调用时候创建EasyPop
```kotlin
    fun normalPop(view: View) {
        TestPop(this).show()
    }
```


不重要：你也可以直接声明匿名内部类
```kotlin
    fun normalPop(view: View) {
        object : EasyPop(this@MainActivity) {
            override fun outClickable(): Boolean {
                return true
            }

            override fun initData() {}
            override fun initView(view: View?) {
                pop_example_text.text = "我是普通弹出窗"
            }

            override fun getLayoutId(): Int {
                return R.layout.pop_test
            }

        }.show()
    }
```
# 属性

# 组件






