package app.vp.cn.profession;

import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;

import app.vp.cn.common.base.BaseActivity;
import app.vp.cn.common.presenter.MyPresenter;
import app.vp.cn.common.util.LogUtil;
import butterknife.BindView;
import butterknife.ButterKnife;

public class PresenterActivity extends BaseActivity {


    @BindView(R.id.bt_acPresenter)
    Button btAcPresenter;
    private MyPresenter myPresenter;

    @Override
    protected int initContentView() {
        return R.layout.activity_presenter;
    }

    @Override
    protected void initViewAndData() {
        myPresenter = new MyPresenter(this, this);
        myPresenter.getdelayText();
        btAcPresenter.setText("本来的数据");
    }

    @Override
    protected void initHttp() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void destroyResources() {
        LogUtil.i("生命周期", "onDestroy ");

    }

    @Override
    public void showToast(String text) {
        super.showToast(text);
        btAcPresenter.setText("后来的数据");

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

}
