package app.vp.cn.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewStub;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @butterknife.BindView(R.id.stub_import)
    ViewStub stubImport;
    @butterknife.BindView(R.id.bt_show)
    Button btShow;
    @butterknife.BindView(R.id.tv_hide)
    Button tvHide;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        butterknife.ButterKnife.bind(this);
        //使用ViewStub 控制布局按需加载 显示的方法
    /*    ViewStub viewStub = findViewById(R.id.stub_import);
        viewStub.setVisibility(View.VISIBLE);
        viewStub.setVisibility(View.GONE);*/


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
