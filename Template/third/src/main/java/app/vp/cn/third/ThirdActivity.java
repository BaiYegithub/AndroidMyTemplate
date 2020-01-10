package app.vp.cn.third;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import app.vp.cn.common.base.BaseActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ThirdActivity extends BaseActivity {


    @BindView(R.id.tv)
    TextView tv;

    @Override
    protected int initContentView() {
        return R.layout.activity_third;
    }

    @Override
    protected void initViewAndData() {
        tv.setText("test的值是" + Constant.test);
    }

    @Override
    protected void initHttp() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void destroyResources() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
