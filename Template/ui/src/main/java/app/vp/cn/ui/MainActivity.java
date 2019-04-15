package app.vp.cn.ui;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputFilter;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import app.vp.cn.common.util.UIUtils;
import app.vp.cn.ui.view.EnglishCharFilter;
import butterknife.BindView;

public class MainActivity extends AppCompatActivity {

    @butterknife.BindView(R.id.stub_import)
    ViewStub stubImport;
    @butterknife.BindView(R.id.bt_show)
    Button btShow;
    @butterknife.BindView(R.id.tv_hide)
    Button tvHide;

    @BindView(R.id.et_acMain)
    EditText et_main;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        butterknife.ButterKnife.bind(this);
        //使用ViewStub 控制布局按需加载 显示的方法
    /*    ViewStub viewStub = findViewById(R.id.stub_import);
        viewStub.setVisibility(View.VISIBLE);
        viewStub.setVisibility(View.GONE);*/

        //这里给输入框加入了一个限制，输入内容不能大于4个字节  （意思是汉字不能多于两个，英文字母不能多于4个）
        et_main.setFilters(new InputFilter[]{new EnglishCharFilter(4)});


        //获取键盘是否弹出以及弹出的高度
        et_main.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {

            //当键盘弹出隐藏的时候 会调用此方法
            @Override
            public void onGlobalLayout() {
                Rect r = new Rect();
                //获取当前界面可视部分
                MainActivity.this.getWindow().getDecorView()
                        .getWindowVisibleDisplayFrame(r);
                //获取屏幕的高度
                int screenHeight = MainActivity.this.getWindow().getDecorView().getRootView().getHeight();
                //此处就是用来获取键盘的高度的， 在键盘没有弹出的时候 此高度为0 键盘弹出的时候为一个正数
                int heightDifference = screenHeight - r.bottom;
                //检查是否有底部导航栏，有的话要减去底部导航栏的高度
                if (UIUtils.checkDeviceHasNavigationBar()) {
                    heightDifference -= UIUtils.getVirtualBarHeigh();
                }

                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                layoutParams.setMargins(0, 0, 0, heightDifference);

                // hs_acWrite.setLayoutParams(layoutParams);
            }
        });

    }

    @butterknife.OnClick({R.id.bt_show, R.id.tv_hide})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_show:
                stubImport.setVisibility(View.VISIBLE);
                break;
            case R.id.tv_hide:
                stubImport.setVisibility(View.GONE);
                break;
        }
    }
}
