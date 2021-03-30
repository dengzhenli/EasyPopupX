# 关于EasyPopup
EasyPopup是一个可以让你在项目里面轻松使用PopupWindow的工具。
你只需按正常的使用习惯即可，其他的事情都交给PopupWindow

# 快速接入
## 添加依赖

## 定制你的PopupWindow
```kotlin
class TestPop(activity: Activity) : BasePopView(activity) {
    override fun initView(view: View?) { pop_example_text.text = "我是普通弹出窗" }
    override fun initData() {}
    override fun getLayoutId(): Int { return R.layout.pop_test }
    override fun onPopDismiss() {}
    override fun outClickable(): Boolean { return true }
}
```

你也可以使用java接入，为节省篇幅，后面的演示只用kotlin，java的使用可参考demo
```java
public class TestPop extends BasePopView {
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
    public void onPopDismiss() {}
    @Override
    public boolean outClickable() { return true;  }
}

```
## 调用
建议：activity实现 LifecycleOwner 接口，为方便后续popupwindow感知生命周期（）

建议：在调用easypop之前调用register方法，此方法会进行生命周期注册等步骤
```kotlin
    EasyPopManager.register(this, this)
```

# 属性

# 组件
